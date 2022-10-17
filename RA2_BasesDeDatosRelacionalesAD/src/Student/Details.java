package Student;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Classes.Student;
import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Details {

	private JFrame detailsFrame;
	private JTextField textName, textSecondName, textDni, textEmail, textBirthdate;
	private JButton btnClose, btnUpdate, btnSave;
	private String dni;
	private JFileChooser Fc;
	private JLabel lblImage;
	private JButton btnUptadteImage;
	private java.sql.Date date;

	public Details() throws ClassNotFoundException, SQLException, ParseException {
		initialize();
	}

	private void initialize() throws ClassNotFoundException, SQLException, ParseException {
		dni = Login.dni;

		PreparedStatement stmt = SlqAndFuctions.getConn().prepareStatement("SELECT * FROM student");
		ResultSet rs = stmt.executeQuery();
		boolean search = rs.next();
		boolean incorrect = false;
		while (search) {
			if (dni.equals(rs.getString("DNI")))
				search = false;
			else {
				rs.next();
				incorrect = true;
			}
		}
		detailsFrame = new JFrame();
		detailsFrame.setTitle("DETAILS");
		detailsFrame.setResizable(false);
		detailsFrame.setBounds(100, 100, 509, 257);
		detailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		detailsFrame.getContentPane().setLayout(null);
		detailsFrame.setLocationRelativeTo(null);
		
		 lblImage = new JLabel("New label");
		lblImage.setBounds(269, 11, 108, 124);
		detailsFrame.getContentPane().add(lblImage);

		ImageIcon imageIcon;
		imageIcon = new ImageIcon(rs.getString("ROUTE_IMG"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(108, 124, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(newimg);

		lblImage.setIcon(imageIcon2);
		JLabel lblName = new JLabel("NAME ");
		lblName.setBounds(10, 44, 40, 14);
		detailsFrame.getContentPane().add(lblName);

		textName = new JTextField();
		textName.setBounds(92, 42, 140, 20);
		detailsFrame.getContentPane().add(textName);
		textName.setColumns(10);
		textName.setText(rs.getString("NAME"));
		textName.setEditable(false);

		JLabel lblSecondName = new JLabel("SECOND NAME");
		lblSecondName.setBounds(10, 78, 78, 14);
		detailsFrame.getContentPane().add(lblSecondName);

		textSecondName = new JTextField();
		textSecondName.setBounds(92, 75, 140, 20);
		detailsFrame.getContentPane().add(textSecondName);
		textSecondName.setColumns(10);
		textSecondName.setText(rs.getString("SECOND_NAME"));
		textSecondName.setEditable(false);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 16, 46, 14);
		detailsFrame.getContentPane().add(lblDni);

		textDni = new JTextField();
		textDni.setBounds(92, 11, 140, 20);
		detailsFrame.getContentPane().add(textDni);
		textDni.setColumns(10);
		textDni.setText(rs.getString("DNI"));
		textDni.setEditable(false);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(10, 109, 46, 14);
		detailsFrame.getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(92, 106, 140, 20);
		detailsFrame.getContentPane().add(textEmail);
		textEmail.setColumns(10);
		textEmail.setText(rs.getString("EMAIL"));
		textEmail.setEditable(false);

		JLabel lblBirthDate = new JLabel("BIRTHDATE");
		lblBirthDate.setBounds(10, 143, 72, 14);
		detailsFrame.getContentPane().add(lblBirthDate);

		textBirthdate = new JTextField();
		textBirthdate.setBounds(92, 140, 140, 20);
		detailsFrame.getContentPane().add(textBirthdate);
		textBirthdate.setColumns(10);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date=rs.getDate("BIRTHDATE");
		textBirthdate.setText(date.toString());
		textBirthdate.setEditable(false);
		
		ManEvent mm=new ManEvent();

		btnClose = new JButton("Close");
		btnClose.setBounds(394, 182, 89, 23);
		btnClose.addActionListener(mm);
		detailsFrame.getContentPane().add(btnClose);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(10, 182, 89, 23);
		btnUpdate.addActionListener(mm);
		detailsFrame.getContentPane().add(btnUpdate);
		
		 btnSave = new JButton("Save");
		btnSave.setBounds(109, 182, 89, 23);
		btnSave.addActionListener(mm);
		detailsFrame.getContentPane().add(btnSave);
		
		btnUptadteImage = new JButton("Update Image");
		btnUptadteImage.setBounds(269, 182, 108, 23);
		btnUptadteImage.addActionListener(mm);
		detailsFrame.getContentPane().add(btnUptadteImage);
		
		Fc = new JFileChooser();
		
		ImageIcon imageIconUpdate;
		imageIconUpdate = new ImageIcon("imgs/updateIcon.png");
		Image imageUpdate = imageIconUpdate.getImage();
		Image newimgUpdate = imageUpdate.getScaledInstance(108, 124, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIconUpdate1 = new ImageIcon(newimgUpdate);
		

		detailsFrame.setVisible(true);
	}
	private void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());

	}
	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnClose) {
				try {
					new Login();
					detailsFrame.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}else if(o == btnUpdate) {
				
				textName.setEditable(true);
				textSecondName.setEditable(true);
				textEmail.setEditable(true);
				
				
			}
			else if(o == btnUptadteImage) {
				/*
				detailsFrame.add(Fc);
				Fc.setVisible(true);
				SlqAndFuctions saf=new SlqAndFuctions();
				int seleccion = Fc.showOpenDialog(null);
				try {
				if (APPROVE_OPTION == seleccion) {
					ResultSet res=	saf.consultDBSpec("student", "DNI", textDni.getText());
					res.next();
					File imgn=new File(res.getString("ROUTE_IMG"));
					imgn.delete();
				
					File imagen1 = new File(res.getString("ROUTE_IMG"));
					try {
						copyFileUsingJava7Files(Fc.getSelectedFile(), imagen1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
						ImageIcon imageIcon;
						imageIcon = new ImageIcon(res.getString("ROUTE_IMG"));
						Image image = imageIcon.getImage();
						Image newimg = image.getScaledInstance(108, 124, java.awt.Image.SCALE_SMOOTH);
						ImageIcon imageIcon2 = new ImageIcon(newimg);

						lblImage.setIcon(imageIcon2);
					
					
					
				} else if (CANCEL_OPTION == seleccion) {
					Fc.setVisible(false);
				}
				
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			*/
			}
			else if(o == btnSave) {
				SlqAndFuctions saf=new SlqAndFuctions();
			
			
				
				try {
					ResultSet res=	saf.consultDBSpec("student", "DNI", textDni.getText());
					res.next();
					Student s = new Student(textDni.getText(), textName.getText(), textSecondName.getText(),
							textEmail.getText(), res.getString("ROUTE_IMG"), res.getDate("BIRTHDATE"));
				saf.Update(s);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textName.setEditable(false);
				textSecondName.setEditable(false);
				textEmail.setEditable(false);
				
			
			
		}
	}}
}
