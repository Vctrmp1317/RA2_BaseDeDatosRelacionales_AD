package LoginAndRegister;

import java.awt.EventQueue;
import java.awt.Image;
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
import admin.SelectButton;
import admin.StudentsDetails;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;

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
		loginFrame.setResizable(false);
		loginFrame.setTitle("LOGIN");
		loginFrame.setBounds(100, 100, 450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.setLocationRelativeTo(null);

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

		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(186, 177, 89, 23);
		loginFrame.getContentPane().add(btnLogin);

		btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(305, 177, 89, 23);
		loginFrame.getContentPane().add(btnRegister);
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
						SelectButton frame = new SelectButton();
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