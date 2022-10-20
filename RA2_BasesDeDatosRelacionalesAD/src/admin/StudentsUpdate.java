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
import java.awt.Font;
import java.awt.Color;

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
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		ImageIcon imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		setIconImage(image);

		this.studentSelected = studentSelected;

		JLabel lblTitle = new JLabel("Are you sure to update this student?");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(168, 21, 250, 13);
		contentPane.add(lblTitle);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(Color.LIGHT_GRAY);
		lblDni.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(26, 70, 109, 13);
		contentPane.add(lblDni);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(26, 112, 109, 13);
		contentPane.add(lblName);

		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setForeground(Color.LIGHT_GRAY);
		lblSecondName.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecondName.setBounds(26, 155, 109, 13);
		contentPane.add(lblSecondName);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(26, 196, 109, 13);
		contentPane.add(lblEmail);

		JLabel lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setForeground(Color.LIGHT_GRAY);
		lblBirthdate.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblBirthdate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthdate.setBounds(26, 239, 109, 13);
		contentPane.add(lblBirthdate);

		txtDni = new JTextField();
		txtDni.setBackground(Color.LIGHT_GRAY);
		txtDni.setBounds(161, 67, 329, 19);
		txtDni.setText(this.studentSelected.getDni());
		txtDni.setEditable(false);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtName = new JTextField();
		txtName.setBackground(Color.LIGHT_GRAY);
		txtName.setColumns(10);
		txtName.setBounds(161, 109, 329, 19);
		txtName.setText(this.studentSelected.getName());
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setBackground(Color.LIGHT_GRAY);
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 152, 329, 19);
		txtSecondName.setText(this.studentSelected.getSecondName());
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setBackground(Color.LIGHT_GRAY);
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 193, 329, 19);
		txtEmail.setText(this.studentSelected.getEmail());
		contentPane.add(txtEmail);

		txtBirthdate = new JTextField();
		txtBirthdate.setBackground(Color.LIGHT_GRAY);
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

		btnConfirm = new JButton();
		btnConfirm.setFocusPainted(false);
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setBorderPainted(false);
		btnConfirm.setBackground(Color.DARK_GRAY);
		btnConfirm.setToolTipText("Confirm");
		btnConfirm.setBounds(242, 401, 33, 33);
		ImageIcon confirmImage = new ImageIcon("icons/accept.png");
		btnConfirm.setIcon(confirmImage);
		contentPane.add(btnConfirm);

		btnReturn = new JButton();
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setToolTipText("Return");
		btnReturn.setBounds(406, 401, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/cancel.png");
		btnReturn.setIcon(returnImage);
		contentPane.add(btnReturn);

		btnAddImage = new JButton();
		btnAddImage.setFocusPainted(false);
		btnAddImage.setContentAreaFilled(false);
		btnAddImage.setBorderPainted(false);
		btnAddImage.setBackground(Color.DARK_GRAY);
		btnAddImage.setToolTipText("Choose");
		btnAddImage.setBounds(324, 317, 33, 33);
		ImageIcon iconAdd = new ImageIcon("icons/agregar-documento.png");
		btnAddImage.setIcon(iconAdd);
		contentPane.add(btnAddImage);

		Fc = new JFileChooser();

		ManEvent mE = new ManEvent();
		btnAddImage.addActionListener(mE);
		btnConfirm.addActionListener(mE);
		btnReturn.addActionListener(mE);

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

					File imagen1 = new File("imgs/" + txtDni.getText() + txtName.getText() + ".jpg");

					try {
						copyFileUsingJava7Files(Fc.getSelectedFile(), imagen1);
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (CANCEL_OPTION == seleccion) {
					Fc.setVisible(false);
				}

			} else if (o == btnConfirm) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					Date birthDate = new Date(format.parse(txtBirthdate.getText()).getTime() + 85400000);
					SlqAndFuctions.Update(new Student(txtDni.getText(), txtName.getText(), txtSecondName.getText(),
							txtEmail.getText(), studentSelected.getRouteImg(), birthDate));
				} catch (Exception ex) {

				}
				Students frame = new Students();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnReturn) {
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
		String[] date = new String[3];
		date = javaDate.split("-");
		return date[2] + "/" + date[1] + "/" + date[0];
	}
}
