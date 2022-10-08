package LoginAndRegister;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login {

	private JTextField textDni;
	private JPasswordField passwordField;
	private JButton btnRegister, btnLogin;
	private JFrame loginFrame;

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
				SlqAndFuctions sql = new SlqAndFuctions();
				try {
					String pass = passwordField.getText();
					ResultSet res = sql.consultDB("users", "rol");
					while (res.next()) {
						String pass2 = res.getString("passsword");
						if (pass.equals(pass2)) {
						String rol = res.getString("rol");
						
						if (rol.equals("student")) {

						} else if (rol.equals("teacher")) {

						} else if (rol.equals("admin")) {

						}
					}
					}

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}
}
