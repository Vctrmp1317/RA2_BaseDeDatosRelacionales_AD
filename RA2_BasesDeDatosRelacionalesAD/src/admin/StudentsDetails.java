package admin;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Classes.Student;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class StudentsDetails extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn;
	private Student studentSelected;
	private JTextField txtDni;
	private JTextField txtName;
	private JTextField txtSecondName;
	private JTextField txtEmail;
	private JTextField txtBirthdate;
	private JLabel lblImagen = new JLabel();


	/**
	 * Create the frame.
	 */
	public StudentsDetails(Student studentSelected) {
		super("DETAILS");
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
		
		JLabel lblTitle = new JLabel("These are the details of the student");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(168, 21, 250, 13);
		contentPane.add(lblTitle);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDni.setForeground(Color.LIGHT_GRAY);
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(25, 70, 110, 13);
		contentPane.add(lblDni);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(25, 112, 110, 13);
		contentPane.add(lblName);

		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblSecondName.setForeground(Color.LIGHT_GRAY);
		lblSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecondName.setBounds(25, 155, 110, 13);
		contentPane.add(lblSecondName);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(25, 196, 110, 13);
		contentPane.add(lblEmail);
		
		JLabel lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblBirthdate.setForeground(Color.LIGHT_GRAY);
		lblBirthdate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthdate.setBounds(25, 239, 110, 13);
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
		txtName.setEditable(false);
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setBackground(Color.LIGHT_GRAY);
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 152, 329, 19);
		txtSecondName.setText(this.studentSelected.getSecondName());
		txtSecondName.setEditable(false);
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setBackground(Color.LIGHT_GRAY);
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 193, 329, 19);
		txtEmail.setText(this.studentSelected.getEmail());
		txtEmail.setEditable(false);
		contentPane.add(txtEmail);
		
		txtBirthdate = new JTextField();
		txtBirthdate.setBackground(Color.LIGHT_GRAY);
		txtBirthdate.setText(String.valueOf(this.studentSelected.getBirthdate()));
		txtBirthdate.setEditable(false);
		txtBirthdate.setColumns(10);
		txtBirthdate.setBounds(161, 236, 329, 19);
		contentPane.add(txtBirthdate);
		
		try {
			ImageIcon img = createImage();
			lblImagen.setFont(new Font("Rockwell", Font.BOLD, 13));
			lblImagen.setForeground(Color.LIGHT_GRAY);
			lblImagen.setIcon(img);
			lblImagen.setBounds(50, 277, 103, 139);
		} catch (InstantiationException e) {
		}
		contentPane.add(lblImagen);

		btnReturn = new JButton();
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setToolTipText("Return");
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(276, 419, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/cancel.png");
		btnReturn.setIcon(returnImage);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnReturn.addActionListener(mE);
		
	}	
	
	private class ManEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			
			  if(o == btnReturn) {
				  Students frame = new Students();
				  frame.setVisible(true);
				  frame.setLocationRelativeTo(null);
				  dispose();
			  }
			 
		}
		
	}
	
	public ImageIcon createImage() throws InstantiationException {
		ImageIcon imageIcon;
		imageIcon = new ImageIcon(studentSelected.getRouteImg());
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(320, 195, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(newimg);
		return imageIcon2;

	}
}
