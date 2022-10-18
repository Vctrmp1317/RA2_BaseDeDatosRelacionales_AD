package LoginAndRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Sql_FuctionsAndFuctions.SlqAndFuctions;
import Student.StudentMenu;

import Teacher.TeacherMenu;
import admin.AdminMenu;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;


public class Login {

	public static JTextField textDni;
	private JPasswordField passwordField;
	private JButton btnRegister, btnLogin;
	private JFrame loginFrame;
	public static String dni;

	public Login() throws ClassNotFoundException, SQLException {
		initialize();
	}

	private void initialize() throws ClassNotFoundException, SQLException {

		SlqAndFuctions.getConn();
		loginFrame = new JFrame();
		loginFrame.getContentPane().setBackground(new Color(51, 51, 51));
		loginFrame.setResizable(false);
		loginFrame.setTitle("LOGIN");
		loginFrame.setBounds(100, 100, 450, 257);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.setLocationRelativeTo(null);

		
		ImageIcon imageIcon;
		imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		loginFrame.setIconImage(image);
		
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setForeground(new Color(153, 153, 153));
		lblDni.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblDni.setBounds(45, 31, 37, 28);
		loginFrame.getContentPane().add(lblDni);

		textDni = new JTextField();
		textDni.setBorder(null);
		textDni.setBackground(new Color(102, 102, 102));
		textDni.setBounds(45, 70, 86, 20);
		loginFrame.getContentPane().add(textDni);
		textDni.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(153, 153, 153));
		lblPassword.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblPassword.setBounds(45, 126, 72, 14);
		loginFrame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBackground(new Color(102, 102, 102));
		passwordField.setBounds(45, 178, 86, 20);
		loginFrame.getContentPane().add(passwordField);

		btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(102, 102, 102));
		btnLogin.setFont(new Font("Rockwell", Font.BOLD, 11));
		btnLogin.setBounds(163, 177, 119, 23);
		btnLogin.setBorderPainted(false);
		loginFrame.getContentPane().add(btnLogin);

		btnRegister = new JButton("REGISTER");
		btnRegister.setForeground(new Color(0, 0, 0));
		btnRegister.setBackground(new Color(102, 102, 102));
		btnRegister.setFont(new Font("Rockwell", Font.BOLD, 11));
		btnRegister.setBounds(305, 177, 119, 23);
		loginFrame.getContentPane().add(btnRegister);
		btnRegister.setBorderPainted(false);
		loginFrame.setVisible(true);

		ManEvent man = new ManEvent();
		btnRegister.addActionListener(man);
		btnLogin.addActionListener(man);

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnRegister) {
				Register frame = new Register();

				loginFrame.setVisible(false);
			} else if (o == btnLogin) {
				if (textDni.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(loginFrame, "THERE ARE EMPTY FIELDS");
				} else {

					if ((textDni.getText().equals("admin") && passwordField.getText().equals("admin"))
							|| (textDni.getText().equals("ADMIN") && passwordField.getText().equals("ADMIN"))) {
						AdminMenu frame = new AdminMenu();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						loginFrame.setVisible(false);

					}else {
					try {

						String pass = passwordField.getText();
						ResultSet res = SlqAndFuctions.consultDB("users");

						boolean bol = res.next();
						boolean incorrect = false;
						while (bol) {
							String pass2 = res.getString("PASSWORD");
							String dniQuery = res.getString("DNI");
							if (pass.equals(pass2) && textDni.getText().equals(dniQuery)) {
								bol = false;
							} else if ((textDni.getText().equalsIgnoreCase("admin")
									&& passwordField.getText().equalsIgnoreCase("admin"))) {
								bol = false;
							} else {
								res.next();
								incorrect = true;
							}
						}

						String rol = res.getString("ROL");
						dni = textDni.getText();

						if (rol.equals("Student")) {
							StudentMenu sm = new StudentMenu();
							loginFrame.setVisible(false);

						} else if (rol.equals("Teacher")) {
							TeacherMenu tm=new TeacherMenu();
							
							loginFrame.setVisible(false);

						}

					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(loginFrame, "DNI OR PASSWORD INCORRECT");
					}
				}

			}

		}

	}
}
}