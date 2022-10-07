package LoginAndRegister;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JTextField textDni;
	private JPasswordField passwordField;
	private JButton btnRegister,btnLogin;

	
	public Login() throws ClassNotFoundException, SQLException {
		initialize();
	}

	private void initialize() throws ClassNotFoundException, SQLException {
		
		SlqAndFuctions.getConn();
		
		setResizable(false);
		setTitle("LOGIN");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(45, 31, 37, 28);
		getContentPane().add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(45, 70, 86, 20);
		getContentPane().add(textDni);
		textDni.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(45, 126, 62, 14);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(45, 178, 86, 20);
		getContentPane().add(passwordField);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(186, 177, 89, 23);
		getContentPane().add(btnLogin);
		
		btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(305, 177, 89, 23);
		getContentPane().add(btnRegister);
		
	}
	private class ManEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o == btnRegister) {
				setVisible(false);
				new Register();
			}
			
		}
		
	}
}
