package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmManteProd_table extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtCódigo;
	JComboBox cboCategorias;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd_table frame = new FrmManteProd_table();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd_table() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(397, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 195, 414, 143);
		contentPane.add(scrollPane);
		
		tblDatos = new JTable();
		tblDatos.addKeyListener(this);
		tblDatos.addMouseListener(this);
		tblDatos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblDatos);
		modelo.addColumn("codigo");
		modelo.addColumn("descripción");
		modelo.addColumn("stock");
		modelo.addColumn("precio");
		modelo.addColumn("IdCat");
		modelo.addColumn("estado");
		tblDatos.setModel(modelo);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(397, 113, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 30, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id. Producto :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 131, 141, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 135, 102, 14);
		contentPane.add(lblCategora);
		
		lblNewLabel_2 = new JLabel("Descripci\u00F3n :");
		lblNewLabel_2.setBounds(10, 61, 102, 14);
		contentPane.add(lblNewLabel_2);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(122, 58, 253, 20);
		contentPane.add(txtDesc);
		
		lblNewLabel_1 = new JLabel("Stock :");
		lblNewLabel_1.setBounds(10, 85, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 82, 86, 20);
		contentPane.add(txtStock);
		
		lblNewLabel_3 = new JLabel("Precio:");
		lblNewLabel_3.setBounds(10, 108, 102, 14);
		contentPane.add(lblNewLabel_3);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 105, 86, 20);
		contentPane.add(txtPrecio);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(122, 164, 86, 20);
		contentPane.add(txtEstado);
		
		lblNewLabel_4 = new JLabel("Estado :");
		lblNewLabel_4.setBounds(10, 167, 102, 14);
		contentPane.add(lblNewLabel_4);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(397, 57, 89, 23);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(397, 85, 89, 23);
		contentPane.add(btnEliminar);
		
		cargarCombo();
		//listado();
		mostrarTabla();
		//mostrarData(0);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarProducto();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminarProducto();
	}
	
	private JLabel lblNewLabel_2;
	private JTextField txtDesc;
	private JLabel lblNewLabel_1;
	private JTextField txtStock;
	private JLabel lblNewLabel_3;
	private JTextField txtPrecio;
	private JTextField txtEstado;
	private JLabel lblNewLabel_4;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTable tblDatos;
	
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	
	void cargarCombo() {
		//Listado de los Tipos de Categorias
		String sql = "SELECT c FROM Categoria c";
		TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
		List<Categoria> lstCategorias = query.getResultList();
		
		cboCategorias.addItem("Seleccionar Categoria");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
	}
	
	void mostrarData( int fila) {
		
		String codpro, descP,stockP,precioP,categoriaP,estadoP;
		
		codpro = modelo.getValueAt(fila, 0).toString();
		descP = modelo.getValueAt(fila, 1)+ "";
		stockP = modelo.getValueAt(fila, 2) +"";
		precioP = modelo.getValueAt(fila, 3) +"";
		categoriaP = modelo.getValueAt(fila, 4)+"";
		estadoP = modelo.getValueAt(fila, 5) +"";
		
		txtCódigo.setText(codpro);
		txtDesc.setText(descP);
		txtStock.setText(stockP);
		txtPrecio.setText(precioP);
		cboCategorias.setSelectedIndex(Integer.parseInt(categoriaP));
		txtEstado.setText(estadoP);
		
	}
	
	void mostrarTabla() {
		
		String sql = "SELECT p FROM Producto p";
		TypedQuery<Producto> query = em.createQuery(sql, Producto.class);
		List<Producto> lstProductos = query.getResultList();
		
		modelo.setRowCount(0);
		
		for (Producto p : lstProductos) {
			Object fila[]= {
					p.getIdprod(),
					p.getDescripcion(),
					p.getStock(),
					p.getPrecio(),
					p.getIdcategoria(),
					p.getEstado()
			};
			modelo.addRow(fila);
		}
		
	}
	
	private String getCodigo() {
		return txtCódigo.getText();
	}
	private String getDescripcion() {
		return txtDesc.getText();
	}
	private int getStock() {
		return Integer.parseInt(txtStock.getText());
	}
	private double getPrecio() {
		return Double.parseDouble(txtPrecio.getText());
	}
	private int getCategoria() {
		return cboCategorias.getSelectedIndex();
	}
	private int getEstado() {
		return Integer.parseInt(txtEstado.getText());
	}

	void listado() {
		String sql = "SELECT p FROM Producto p";
		TypedQuery<Producto> query = em.createQuery(sql, Producto.class);
		List<Producto> lstProductos = query.getResultList();
		
		for (Producto p : lstProductos) {
			//txtSalida.append(p.getIdprod() + " "+p.getDescripcion()+ "\n");
			System.out.println(p);
		}
		
	}
	
	void registrar() {
		
		String codigo = getCodigo(); // leerCodigo();
		String descripcion = getDescripcion();
		int stock = getStock();
		double precio = getPrecio();
		int categoria = getCategoria(); // leerCategorias();
		int estado = getEstado();
		
		//proceso
		Producto p = new Producto();
		p.setIdprod(codigo);
		p.setDescripcion(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(categoria);
		p.setEstado(estado); 
		
		em.getTransaction().begin();
		em.persist(p); //<--Registrar
		em.getTransaction().commit();
		
		listado();

	}
	
	void actualizarProducto() {
		String codigo = getCodigo(); // leerCodigo();
		String descripcion = getDescripcion();
		int stock = getStock();
		double precio = getPrecio();
		int categoria = getCategoria(); // leerCategorias();
		int estado = getEstado();
		
		//proceso
		Producto p = new Producto();
		p.setIdprod(codigo);
		p.setDescripcion(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(categoria);
		p.setEstado(estado); 
		
		em.getTransaction().begin();
		em.merge(p); //<--Actualizar Producto
		em.getTransaction().commit();
		
		listado();
	}

	void eliminarProducto() {
		String codigo = getCodigo();
		Producto p = em.find(Producto.class, codigo );
		
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		listado();
		
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblDatos) {
			mouseClickedTblDatos(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblDatos(MouseEvent arg0) {
		int fila;
		fila = tblDatos.getSelectedRow();
		mostrarData(fila);
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == tblDatos) {
			keyReleasedTblDatos(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTblDatos(KeyEvent arg0) {
		int fila;
		fila = tblDatos.getSelectedRow();
		mostrarData(fila);
	}
}
