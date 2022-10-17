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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.Student;
import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;

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
		detailsFrame.getContentPane().setBackground(Color.DARK_GRAY);
		detailsFrame.setTitle("DETAILS");
		detailsFrame.setResizable(false);
		detailsFrame.setBounds(100, 100, 509, 257);
		detailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		detailsFrame.getContentPane().setLayout(null);
		detailsFrame.setLocationRelativeTo(null);
		
		ImageIcon imageIconWindow;
		imageIconWindow = new ImageIcon("icons/icon.png");
		Image imageWindow = imageIconWindow.getImage();
		detailsFrame.setIconImage(imageWindow);

		
		 lblImage = new JLabel();
		lblImage.setBounds(286, 11, 108, 124);
		detailsFrame.getContentPane().add(lblImage);

		ImageIcon imageIcon;
		imageIcon = new ImageIcon(rs.getString("ROUTE_IMG"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(108, 124, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(newimg);

		lblImage.setIcon(imageIcon2);
		JLabel lblName = new JLabel("NAME ");
		lblName.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setBounds(10, 44, 40, 14);
		detailsFrame.getContentPane().add(lblName);

		textName = new JTextField();
		textName.setFont(new Font("Rockwell", Font.PLAIN, 11));
		textName.setForeground(new Color(0, 0, 0));
		textName.setBorder(null);
		textName.setBackground(new Color(105, 105, 105));
		textName.setBounds(119, 41, 140, 20);
		detailsFrame.getContentPane().add(textName);
		textName.setColumns(10);
		textName.setText(rs.getString("NAME"));
		textName.setEditable(false);

		JLabel lblSecondName = new JLabel("SECOND NAME");
		lblSecondName.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblSecondName.setForeground(Color.LIGHT_GRAY);
		lblSecondName.setBounds(10, 78, 99, 14);
		detailsFrame.getContentPane().add(lblSecondName);

		textSecondName = new JTextField();
		textSecondName.setFont(new Font("Rockwell", Font.PLAIN, 11));
		textSecondName.setForeground(new Color(0, 0, 0));
		textSecondName.setBorder(null);
		textSecondName.setBackground(new Color(105, 105, 105));
		textSecondName.setBounds(119, 73, 140, 20);
		detailsFrame.getContentPane().add(textSecondName);
		textSecondName.setColumns(10);
		textSecondName.setText(rs.getString("SECOND_NAME"));
		textSecondName.setEditable(false);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setForeground(Color.LIGHT_GRAY);
		lblDni.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblDni.setBounds(10, 16, 46, 14);
		detailsFrame.getContentPane().add(lblDni);

		textDni = new JTextField();
		textDni.setFont(new Font("Rockwell", Font.PLAIN, 11));
		textDni.setForeground(new Color(0, 0, 0));
		textDni.setBorder(null);
		textDni.setBackground(new Color(105, 105, 105));
		textDni.setBounds(119, 11, 140, 20);
		detailsFrame.getContentPane().add(textDni);
		textDni.setColumns(10);
		textDni.setText(rs.getString("DNI"));
		textDni.setEditable(false);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setBounds(10, 109, 46, 14);
		detailsFrame.getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Rockwell", Font.PLAIN, 11));
		textEmail.setForeground(new Color(0, 0, 0));
		textEmail.setBorder(null);
		textEmail.setBackground(new Color(105, 105, 105));
		textEmail.setBounds(119, 104, 140, 20);
		detailsFrame.getContentPane().add(textEmail);
		textEmail.setColumns(10);
		textEmail.setText(rs.getString("EMAIL"));
		textEmail.setEditable(false);

		JLabel lblBirthDate = new JLabel("BIRTHDATE");
		lblBirthDate.setFont(new Font("Rockwell", Font.BOLD, 11));
		lblBirthDate.setForeground(Color.LIGHT_GRAY);
		lblBirthDate.setBounds(10, 143, 72, 14);
		detailsFrame.getContentPane().add(lblBirthDate);

		textBirthdate = new JTextField();
		textBirthdate.setFont(new Font("Rockwell", Font.PLAIN, 11));
		textBirthdate.setForeground(new Color(0, 0, 0));
		textBirthdate.setBorder(null);
		textBirthdate.setBackground(new Color(105, 105, 105));
		textBirthdate.setBounds(119, 135, 140, 20);
		detailsFrame.getContentPane().add(textBirthdate);
		textBirthdate.setColumns(10);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date=rs.getDate("BIRTHDATE");
		textBirthdate.setText(date.toString());
		textBirthdate.setEditable(false);
		
		ManEvent mm=new ManEvent();

		btnClose = new JButton();
		btnClose.setToolTipText("Close");
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setBounds(450, 174, 33, 33);
		btnClose.addActionListener(mm);
		ImageIcon closeIcon=new ImageIcon("icons/cancel.png");
		btnClose.setIcon(closeIcon);
		btnClose.setBorderPainted(false);
		detailsFrame.getContentPane().add(btnClose);
		
		btnUpdate = new JButton();
		btnUpdate.setToolTipText("Update");
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBounds(301, 174, 25, 25);
		btnUpdate.addActionListener(mm);
		ImageIcon updateIcon=new ImageIcon("icons/uptadte.png");
		btnUpdate.setIcon(updateIcon);
		btnUpdate.setBorderPainted(false);
		detailsFrame.getContentPane().add(btnUpdate);
		
		 btnSave = new JButton();
		 btnSave.setToolTipText("Save");
		 btnSave.setBackground(Color.DARK_GRAY);
		btnSave.setBounds(346, 173, 26, 26);
		btnSave.addActionListener(mm);
		btnSave.setBorderPainted(false);
		ImageIcon saveIcon=new ImageIcon("icons/save.png");
		btnSave.setIcon(saveIcon);
		detailsFrame.getContentPane().add(btnSave);
		
		btnUptadteImage = new JButton();
		btnUptadteImage.setBackground(Color.DARK_GRAY);
		btnUptadteImage.setToolTipText("Update Image");
		btnUptadteImage.setBounds(395, 174, 33, 33);
		ImageIcon updateImageIcon=new ImageIcon("icons/agregar-documento.png");
		btnUptadteImage.setIcon(updateImageIcon);
		btnUptadteImage.addActionListener(mm);
		btnUptadteImage.setBorderPainted(false);
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
					new StudentMenu();
					detailsFrame.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}else if(o == btnUpdate) {
				JOptionPane.showMessageDialog(detailsFrame, "FIELDS EDITABLES NOW");
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
