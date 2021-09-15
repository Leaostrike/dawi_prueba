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

public class FrmLogin extends JFrame implements ActionListener {

	private JPanel contentPane;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(65, 32, 102, 14);
		contentPane.add(lblUsuario);
		
		lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setBounds(48, 84, 102, 14);
		contentPane.add(lblNewLabel_3);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(177, 29, 157, 20);
		contentPane.add(txtUsuario);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(177, 81, 157, 20);
		contentPane.add(txtPassword);
		
		btnNewButton_1 = new JButton("Ingresar");
		btnNewButton_1.setBounds(368, 28, 89, 23);
		contentPane.add(btnNewButton_1);
		
	}
	
	public void actionPerformed(ActionEvent e) {
	}
	
	
	private JLabel lblUsuario;
	private JLabel lblNewLabel_3;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JButton btnNewButton_1;
	
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	
	private String getUsuario() {
		return txtUsuario.getText();
	}
	private String getPassword() {
		return txtPassword.getText();
	}
	

	
	
	

	
}
