package LoginAndRegister;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login {

	private JFrame loginFrame;
	private JTextField textDni;
	private JPasswordField passwordField;

	
	public Login() throws ClassNotFoundException, SQLException {
		initialize();
	}

	private void initialize() throws ClassNotFoundException, SQLException {
		
		SlqAndFuctions saf= new SlqAndFuctions();
		saf.getConn(null, null, null, null);
		loginFrame = new JFrame();
		loginFrame.setResizable(false);
		loginFrame.setTitle("LOGIN");
		loginFrame.setBounds(100, 100, 450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(45, 31, 37, 28);
		loginFrame.getContentPane().add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(45, 70, 86, 20);
		loginFrame.getContentPane().add(textDni);
		textDni.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(45, 126, 62, 14);
		loginFrame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(45, 178, 86, 20);
		loginFrame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(186, 177, 89, 23);
		loginFrame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(305, 177, 89, 23);
		loginFrame.getContentPane().add(btnRegister);
		
	}
}
