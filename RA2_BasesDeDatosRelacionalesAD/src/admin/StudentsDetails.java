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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		this.studentSelected = studentSelected;
		
		JLabel lblTitle = new JLabel("These are the details of the student");
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
		txtDni.setText(String.valueOf(this.studentSelected.getDni()));
		txtDni.setEditable(false);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(161, 109, 329, 19);
		txtName.setText(String.valueOf(this.studentSelected.getName()));
		txtName.setEditable(false);
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 152, 329, 19);
		txtSecondName.setText(this.studentSelected.getSecondName());
		txtSecondName.setEditable(false);
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 193, 329, 19);
		txtEmail.setText(this.studentSelected.getEmail());
		txtEmail.setEditable(false);
		contentPane.add(txtEmail);
		
		txtBirthdate = new JTextField();
		txtBirthdate.setText(String.valueOf(this.studentSelected.getBirthdate()));
		txtBirthdate.setEditable(false);
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

		btnReturn = new JButton("Return");
		btnReturn.setBounds(250, 419, 85, 21);
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
