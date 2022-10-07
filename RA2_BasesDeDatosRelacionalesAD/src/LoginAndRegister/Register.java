package LoginAndRegister;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.CANCEL_OPTION;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Classes.Student;
import Classes.Users;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Register extends JFrame{

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
	private JButton btnCancel;
	private JButton btnAccept;

	public Register() {
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(37, 48, 73, 14);
		getContentPane().add(lblDni);

		textDni = new JTextField();
		textDni.setBounds(37, 73, 86, 20);
		getContentPane().add(textDni);
		textDni.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(37, 104, 86, 14);
		getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(37, 129, 86, 20);
		getContentPane().add(passwordField);

		lblName = new JLabel("NAME");
		lblName.setBounds(37, 160, 46, 14);
		getContentPane().add(lblName);

		textName = new JTextField();
		textName.setBounds(37, 185, 86, 20);
		getContentPane().add(textName);
		textName.setColumns(10);

		lblSecondName = new JLabel("SECOND NAME");
		lblSecondName.setBounds(174, 48, 73, 14);
		getContentPane().add(lblSecondName);

		textSecondName = new JTextField();
		textSecondName.setBounds(174, 73, 86, 20);
		getContentPane().add(textSecondName);
		textSecondName.setColumns(10);

		lblImage = new JLabel("IMAGE ");
		lblImage.setBounds(178, 160, 46, 14);
		getContentPane().add(lblImage);

		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(174, 104, 46, 14);
		getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(174, 129, 86, 20);
		getContentPane().add(textEmail);
		textEmail.setColumns(10);

		btnAddImage = new JButton("ADD IMAGE");
		btnAddImage.setBounds(171, 184, 89, 23);
		getContentPane().add(btnAddImage);

		lblBirthdate = new JLabel("BIRTHDATE");
		lblBirthdate.setBounds(310, 48, 73, 14);
		getContentPane().add(lblBirthdate);

		textBirthdate = new JTextField();
		textBirthdate.setBounds(310, 73, 86, 20);
		getContentPane().add(textBirthdate);
		textBirthdate.setColumns(10);

		btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(37, 216, 89, 23);
		getContentPane().add(btnCancel);

		btnAccept = new JButton("ACCEPT");
		btnAccept.setBounds(310, 216, 89, 23);
		getContentPane().add(btnAccept);

		Fc = new JFileChooser();
	}

	private void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnAddImage) {

				add(Fc);
				Fc.setVisible(true);

				int seleccion = Fc.showOpenDialog(null);
				if (APPROVE_OPTION == seleccion) {

					File imagen1 = new File("imgs/" + textDni.getText() + textName.getText() + ".jpg");

					try {
						copyFileUsingJava7Files(Fc.getSelectedFile(), imagen1);
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (CANCEL_OPTION == seleccion) {
					Fc.setVisible(false);
				}

			} else if (o == btnAccept) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String route = "imgs/" + textDni.getText() + textName.getText() + ".jpg";
				Date birthDate;
				try {
					birthDate = (Date) format.parse(textBirthdate.getText());
					Student s = new Student(textDni.getText(), textName.getText(), textSecondName.getText(),
							textEmail.getText(), route, birthDate);
					Users u=new Users(textDni.getText(),passwordField.getSelectedText(),"Student");
						SlqAndFuctions.insert(s);
						SlqAndFuctions.inserUser(u);
				} catch (ParseException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else if(o == btnCancel) {
				Login frame = null;
				try {
					frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}

		}

	}

}
