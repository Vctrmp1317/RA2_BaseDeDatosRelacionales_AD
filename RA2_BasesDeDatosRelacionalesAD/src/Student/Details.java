package Student;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Details {

	private JFrame detailsFrame;
	private JTextField textName;
	private JTextField textSecondName;
	private JTextField textDni;
	private JTextField textEmail;
	private JTextField textBirthdate;
	private String dni;

	public Details() throws ClassNotFoundException, SQLException {
		initialize();
	}

	private void initialize() throws ClassNotFoundException, SQLException {
		dni= Login.dni;
		
		PreparedStatement stmt=SlqAndFuctions.getConn().prepareStatement("SELECT * FROM student");
		ResultSet rs=stmt.executeQuery();
		boolean search =rs.next();
		boolean incorrect=false;
		while(search) {
			if(dni.equals(rs.getString("DNI")))
				search=false;
			else {
				rs.next();
				incorrect = true;
			}
		}
		detailsFrame = new JFrame();
		detailsFrame.setTitle("DETAILS");
		detailsFrame.setResizable(false);
		detailsFrame.setBounds(100, 100, 450, 296);
		detailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		detailsFrame.getContentPane().setLayout(null);
		
		JLabel lblImage = new JLabel("New label");
		lblImage.setBounds(280, 52, 108, 124);
		detailsFrame.getContentPane().add(lblImage);
		
		ImageIcon imageIcon;
		imageIcon = new ImageIcon(rs.getString("ROUTE_IMG"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(108, 124, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(newimg);
		
		lblImage.setIcon(imageIcon2);
		JLabel lblName = new JLabel("NAME ");
		lblName.setBounds(10, 62, 40, 14);
		detailsFrame.getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.setBounds(92, 60, 140, 20);
		detailsFrame.getContentPane().add(textName);
		textName.setColumns(10);
		textName.setText(rs.getString("NAME"));
		textName.setEditable(false);
		
		JLabel lblSecondName = new JLabel("SECOND NAME");
		lblSecondName.setBounds(10, 96, 78, 14);
		detailsFrame.getContentPane().add(lblSecondName);
		
		textSecondName = new JTextField();
		textSecondName.setBounds(92, 93, 140, 20);
		detailsFrame.getContentPane().add(textSecondName);
		textSecondName.setColumns(10);
		textSecondName.setText(rs.getString("SECOND_NAME"));
		textSecondName.setEditable(false);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(14, 127, 46, 14);
		detailsFrame.getContentPane().add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(92, 124, 140, 20);
		detailsFrame.getContentPane().add(textDni);
		textDni.setColumns(10);
		textDni.setText(rs.getString("DNI"));
		textDni.setEditable(false);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(10, 160, 46, 14);
		detailsFrame.getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(92, 157, 140, 20);
		detailsFrame.getContentPane().add(textEmail);
		textEmail.setColumns(10);
		textEmail.setText(rs.getString("EMAIL"));
		textEmail.setEditable(false);
		
		JLabel lblBirthDate = new JLabel("BIRTHDATE");
		lblBirthDate.setBounds(10, 194, 72, 14);
		detailsFrame.getContentPane().add(lblBirthDate);
		
		textBirthdate = new JTextField();
		textBirthdate.setBounds(92, 191, 140, 20);
		detailsFrame.getContentPane().add(textBirthdate);
		textBirthdate.setColumns(10);
		textBirthdate.setText(rs.getDate("BIRTHDATE").toString());
		textBirthdate.setEditable(false);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(335, 223, 89, 23);
		detailsFrame.getContentPane().add(btnClose);
		
		detailsFrame.setVisible(true);
	}
}
