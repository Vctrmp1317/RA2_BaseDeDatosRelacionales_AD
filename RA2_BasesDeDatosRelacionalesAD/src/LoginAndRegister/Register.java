package LoginAndRegister;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Register {

	private JFrame registerFrame;
	private JTextField textDni;
	private JPasswordField passwordField;
	private JLabel lblName;
	private JTextField textName;
	private JLabel lblSecondName;
	private JTextField textSecondName;
	private JLabel lblImage;
	private JLabel lblEmail;
	private JTextField textEmail;
	private JFileChooser Fc;
	private JButton btnAddImage;
	private JLabel lblBirthdate;
	private JTextField textBirthdate;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	public Register() {
		initialize();
	}

	
	private void initialize() {
		registerFrame = new JFrame();
		registerFrame.setResizable(false);
		registerFrame.setBounds(100, 100, 450, 300);
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.getContentPane().setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(37, 48, 73, 14);
		registerFrame.getContentPane().add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(37, 73, 86, 20);
		registerFrame.getContentPane().add(textDni);
		textDni.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(37, 104, 86, 14);
		registerFrame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(37, 129, 86, 20);
		registerFrame.getContentPane().add(passwordField);
		
		lblName = new JLabel("NAME");
		lblName.setBounds(37, 160, 46, 14);
		registerFrame.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setBounds(37, 185, 86, 20);
		registerFrame.getContentPane().add(textName);
		textName.setColumns(10);
		
		lblSecondName = new JLabel("SECOND NAME");
		lblSecondName.setBounds(174, 48, 73, 14);
		registerFrame.getContentPane().add(lblSecondName);
		
		textSecondName = new JTextField();
		textSecondName.setBounds(174, 73, 86, 20);
		registerFrame.getContentPane().add(textSecondName);
		textSecondName.setColumns(10);
		
		lblImage = new JLabel("IMAGE ");
		lblImage.setBounds(178, 160, 46, 14);
		registerFrame.getContentPane().add(lblImage);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(174, 104, 46, 14);
		registerFrame.getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(174, 129, 86, 20);
		registerFrame.getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		btnAddImage = new JButton("ADD IMAGE");
		btnAddImage.setBounds(171, 184, 89, 23);
		registerFrame.getContentPane().add(btnAddImage);
		
		lblBirthdate = new JLabel("BIRTHDATE");
		lblBirthdate.setBounds(310, 48, 73, 14);
		registerFrame.getContentPane().add(lblBirthdate);
		
		textBirthdate = new JTextField();
		textBirthdate.setBounds(310, 73, 86, 20);
		registerFrame.getContentPane().add(textBirthdate);
		textBirthdate.setColumns(10);
		
		btnNewButton = new JButton("CANCEL");
		btnNewButton.setBounds(37, 216, 89, 23);
		registerFrame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("ACCEPT");
		btnNewButton_1.setBounds(310, 216, 89, 23);
		registerFrame.getContentPane().add(btnNewButton_1);
		
		Fc = new JFileChooser();
	}

}
