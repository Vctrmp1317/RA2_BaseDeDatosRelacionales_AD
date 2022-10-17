package admin;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.CANCEL_OPTION;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Classes.Student;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class StudentsUpdate extends JFrame {

	private JPanel contentPane;
	private JButton btnAddImage, btnConfirm, btnReturn;
	private Student studentSelected;
	private JTextField txtDni;
	private JTextField txtName;
	private JTextField txtSecondName;
	private JTextField txtEmail;
	private JTextField txtBirthdate;
	private JLabel lblImagen = new JLabel();
	private JFileChooser Fc;


	/**
	 * Create the frame.
	 */
	public StudentsUpdate(Student studentSelected) {
		super("UPDATE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		this.studentSelected = studentSelected;
		
		JLabel lblTitle = new JLabel("Are you sure to update this student?");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(192, 21, 202, 13);
		contentPane.add(lblTitle);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(50, 70, 85, 13);
		contentPane.add(lblDni);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 112, 85, 13);
		contentPane.add(lblName);

		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecondName.setBounds(50, 155, 85, 13);
		contentPane.add(lblSecondName);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(50, 196, 85, 13);
		contentPane.add(lblEmail);
		
		JLabel lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthdate.setBounds(50, 239, 85, 13);
		contentPane.add(lblBirthdate);

		txtDni = new JTextField();
		txtDni.setBounds(161, 67, 329, 19);
		txtDni.setText(this.studentSelected.getDni());
		txtDni.setEditable(false);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(161, 109, 329, 19);
		txtName.setText(this.studentSelected.getName());
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 152, 329, 19);
		txtSecondName.setText(this.studentSelected.getSecondName());
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 193, 329, 19);
		txtEmail.setText(this.studentSelected.getEmail());
		contentPane.add(txtEmail);
		
		txtBirthdate = new JTextField();
		String parseDate = sqlDate(String.valueOf(this.studentSelected.getBirthdate()));
		txtBirthdate.setText(parseDate);
		txtBirthdate.setColumns(10);
		txtBirthdate.setBounds(161, 236, 329, 19);
		contentPane.add(txtBirthdate);
		
		try {
			ImageIcon img = createImage();
			lblImagen.setIcon(img);
			lblImagen.setBounds(50, 277, 103, 139);
		} catch (InstantiationException e) {
		}
		contentPane.add(lblImagen);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(244, 419, 85, 21);
		contentPane.add(btnConfirm);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(405, 419, 85, 21);
		contentPane.add(btnReturn);
		
		btnAddImage = new JButton("Choose");
		btnAddImage.setBounds(324, 318, 85, 21);
		contentPane.add(btnAddImage);
		
		Fc = new JFileChooser();

		ManEvent mE = new ManEvent();
		btnAddImage.addActionListener(mE);
		btnConfirm.addActionListener(mE);
		btnReturn.addActionListener(mE);
		
	}	
	
	private class ManEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnAddImage) {

				add(Fc);
				Fc.setVisible(true);

				int seleccion = Fc.showOpenDialog(null);
				if (APPROVE_OPTION == seleccion) {

					File imagen1 = new File("imgs/" + txtDni.getText() + txtName.getText() + ".jpg");

					try {
						copyFileUsingJava7Files(Fc.getSelectedFile(), imagen1);
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (CANCEL_OPTION == seleccion) {
					Fc.setVisible(false);
				}

			} else if(o == btnConfirm) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					Date birthDate = new Date(format.parse(txtBirthdate.getText()).getTime() + 85400000);
					SlqAndFuctions.Update(new Student(txtDni.getText(), txtName.getText(), txtSecondName.getText(), txtEmail.getText(), studentSelected.getRouteImg(), birthDate));
				} catch (Exception ex) {
					
				}
				Students frame = new Students();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if(o == btnReturn) {
				Students frame = new Students();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
			 
		}
		
	}
	
	private void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());

	}
	
	private ImageIcon createImage() throws InstantiationException {
		ImageIcon imageIcon;
		imageIcon = new ImageIcon(studentSelected.getRouteImg());
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(320, 195, Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(newimg);
		return imageIcon2;

	}
	
	private String sqlDate(String javaDate) {
		   String[] date= new String[3];
		   date = javaDate.split("-");
		   return date[2] + "/" + date[1] + "/" + date[0];
		}
}
