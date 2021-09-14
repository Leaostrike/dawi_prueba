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

public class FrmManteProd2 extends JFrame {

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
					FrmManteProd2 frame = new FrmManteProd2();
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
	public FrmManteProd2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
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
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 30, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id. Producto :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 108, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 112, 102, 14);
		contentPane.add(lblCategora);
		
		tblDatos = new JTable();
		tblDatos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblDatos);
		modelo.addColumn("codigo");
		modelo.addColumn("descripción");
		modelo.addColumn("stock");
		modelo.addColumn("precio");
		modelo.addColumn("IdCat");
		modelo.addColumn("estado");
		tblDatos.setModel(modelo);
		
		cargarCombo();
		mostrarTabla();
		//listado();
	}
	
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	private JTable tblDatos;
	
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

	
	  void listado() {
		  
		  
	/*	
		//EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		//EntityManager em = fabrica.createEntityManager();
		
		String sql = "SELECT p FROM Producto p";
		TypedQuery<Producto> query = em.createQuery(sql, Producto.class);
		List<Producto> lstProductos = query.getResultList();
		
		for (Producto p : lstProductos) {
			txtSalida.append(p.getIdprod() + " "+p.getDescripcion()+ "\n");
			System.out.println(p);
		}
		txtSalida.append("---------------------------------");
	*/	
	}
	
	
	void registrar() {
		String codigo = txtCódigo.getText(); // leerCodigo();
		String descripcion = "ejemplo";
		int stock = 100;
		double precio = 0.99;
		int categoria = cboCategorias.getSelectedIndex(); // leerCategorias();
		int estado = 1;
		
		//proceso
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();
	
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
		
		em.close();
		
		//salida

	}
}
