package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class FrmManteProdOriginal extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCódigo;
	JComboBox cboCategorias;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProdOriginal frame = new FrmManteProdOriginal();
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
	public FrmManteProdOriginal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 415);
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
		scrollPane.setBounds(10, 195, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(178, 342, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 30, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id. Producto :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 131, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 135, 102, 14);
		contentPane.add(lblCategora);
		
		lblNewLabel_2 = new JLabel("Descripci\u00F3n :");
		lblNewLabel_2.setBounds(10, 61, 102, 14);
		contentPane.add(lblNewLabel_2);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(122, 58, 200, 20);
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(122, 164, 86, 20);
		contentPane.add(textField_3);
		
		lblNewLabel_4 = new JLabel("Estado :");
		lblNewLabel_4.setBounds(10, 167, 102, 14);
		contentPane.add(lblNewLabel_4);
		
		cargarCombo();
		//listado();
	}
	
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	private JLabel lblNewLabel_2;
	private JTextField txtDesc;
	private JLabel lblNewLabel_1;
	private JTextField txtStock;
	private JLabel lblNewLabel_3;
	private JTextField txtPrecio;
	private JTextField textField_3;
	private JLabel lblNewLabel_4;
	
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

	void listado() {
		
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
