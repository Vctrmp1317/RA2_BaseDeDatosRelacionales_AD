package LoginAndRegister;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.CANCEL_OPTION;

import java.awt.EventQueue;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.ResultSet;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

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
	private JButton btnCancel;
	private JButton btnAccept;

	public Register() {
		initialize();
	}

	private void initialize() {
		registerFrame = new JFrame();
		registerFrame.getContentPane().setBackground(new Color(51, 51, 51));
		registerFrame.setTitle("REGISTER");
		registerFrame.setResizable(false);
		registerFrame.setBounds(100, 100, 450, 300);
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.getContentPane().setLayout(null);
		registerFrame.setLocationRelativeTo(null);

		ImageIcon imageIcon;
		imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		registerFrame.setIconImage(image);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblDni.setForeground(new Color(153, 153, 153));
		lblDni.setBounds(37, 48, 73, 14);
		registerFrame.getContentPane().add(lblDni);

		textDni = new JTextField();
		textDni.setBorder(null);
		textDni.setBackground(new Color(105, 105, 105));
		textDni.setBounds(37, 73, 86, 20);
		registerFrame.getContentPane().add(textDni);
		textDni.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(153, 153, 153));
		lblPassword.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblPassword.setBounds(37, 104, 86, 14);
		registerFrame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBackground(new Color(102, 102, 102));
		passwordField.setBounds(37, 129, 86, 20);
		registerFrame.getContentPane().add(passwordField);

		lblName = new JLabel("NAME");
		lblName.setForeground(new Color(153, 153, 153));
		lblName.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblName.setBounds(37, 160, 46, 14);
		registerFrame.getContentPane().add(lblName);

		textName = new JTextField();
		textName.setBorder(null);
		textName.setBackground(new Color(102, 102, 102));
		textName.setBounds(37, 185, 86, 20);
		registerFrame.getContentPane().add(textName);
		textName.setColumns(10);

		lblSecondName = new JLabel("SECOND NAME");
		lblSecondName.setForeground(new Color(153, 153, 153));
		lblSecondName.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblSecondName.setBounds(174, 48, 101, 14);
		registerFrame.getContentPane().add(lblSecondName);

		textSecondName = new JTextField();
		textSecondName.setBorder(null);
		textSecondName.setBackground(new Color(102, 102, 102));
		textSecondName.setBounds(174, 73, 86, 20);
		registerFrame.getContentPane().add(textSecondName);
		textSecondName.setColumns(10);

		lblImage = new JLabel("IMAGE ");
		lblImage.setForeground(new Color(153, 153, 153));
		lblImage.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblImage.setBounds(178, 160, 46, 14);
		registerFrame.getContentPane().add(lblImage);

		lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblEmail.setForeground(new Color(153, 153, 153));
		lblEmail.setBounds(174, 104, 46, 14);
		registerFrame.getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBorder(null);
		textEmail.setBackground(new Color(102, 102, 102));
		textEmail.setBounds(174, 129, 86, 20);
		registerFrame.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		btnAddImage = new JButton();
		btnAddImage.setToolTipText("Add Image");
		btnAddImage.setBackground(new Color(51, 51, 51));
		btnAddImage.setBounds(174, 185, 33, 33);
		ImageIcon iconAdd=new ImageIcon("icons/agregar-documento.png");
		btnAddImage.setBorderPainted(false);
		btnAddImage.setIcon(iconAdd);
		
		registerFrame.getContentPane().add(btnAddImage);

		
		lblBirthdate = new JLabel("BIRTHDATE");
		lblBirthdate.setForeground(new Color(153, 153, 153));
		lblBirthdate.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblBirthdate.setBounds(310, 48, 73, 14);
		registerFrame.getContentPane().add(lblBirthdate);

		textBirthdate = new JTextField();
		textBirthdate.setBorder(null);
		textBirthdate.setBackground(new Color(102, 102, 102));
		textBirthdate.setBounds(310, 73, 86, 20);
		registerFrame.getContentPane().add(textBirthdate);
		textBirthdate.setColumns(10);

		btnCancel = new JButton();
		btnCancel.setToolTipText("Cancel");
		btnCancel.setBackground(new Color(51, 51, 51));
		btnCancel.setBounds(310, 186, 33, 33);
		ImageIcon iconCancel=new ImageIcon("icons/cancel.png");
		btnCancel.setIcon(iconCancel);
		btnCancel.setBorderPainted(false);
		registerFrame.getContentPane().add(btnCancel);

		btnAccept = new JButton();
		btnAccept.setToolTipText("Accept");
		btnAccept.setBackground(new Color(51, 51, 51));
		btnAccept.setBounds(363, 186, 33, 33);
		ImageIcon iconAccept=new ImageIcon("icons/accept.png");
		btnAccept.setIcon(iconAccept);
		btnAccept.setBorderPainted(false);
		registerFrame.getContentPane().add(btnAccept);

		Fc = new JFileChooser();
		registerFrame.setVisible(true);
		
		ManEven man = new ManEven();
		btnAddImage.addActionListener(man);
		btnCancel.addActionListener(man);
		btnAccept.addActionListener(man);

	}

	private void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());

	}

	private class ManEven implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnAddImage) {/*

				registerFrame.add(Fc);
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
				}*/

			} else if (o == btnAccept) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String route = "imgs/" + textDni.getText() + textName.getText() + ".jpg";
				Date birthDate;
				try {
					SlqAndFuctions saf=new SlqAndFuctions();
					ResultSet rs=saf.consultDB("users");
					
					while(rs.next()) {
						rs.getString("DNI").equals(textDni.getText());
					}
					birthDate = new Date(format.parse(textBirthdate.getText()).getTime());
					Student s = new Student(textDni.getText(), textName.getText(), textSecondName.getText(),
							textEmail.getText(), route, birthDate);

					Users u = new Users(textDni.getText(), passwordField.getText(), "Student");

					SlqAndFuctions.inserUser(u);
					SlqAndFuctions.insert(s);
					Login frame = new Login();
					registerFrame.dispose();
				} catch (ParseException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (o == btnCancel) {
				File imagen1 = new File("imgs/" + textDni.getText() + textName.getText() + ".jpg");
				if(imagen1.exists())
					imagen1.delete();
				Login frame = null;
				try {
					new Login();

					registerFrame.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}

}
