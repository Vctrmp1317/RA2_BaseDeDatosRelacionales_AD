package Student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Details {

	private JFrame detailsFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public Details() {
		initialize();
	}

	private void initialize() {
		detailsFrame = new JFrame();
		detailsFrame.setTitle("DETAILS");
		detailsFrame.setResizable(false);
		detailsFrame.setBounds(100, 100, 450, 337);
		detailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		detailsFrame.getContentPane().setLayout(null);
		
		JLabel lblImage = new JLabel("New label");
		lblImage.setBounds(10, 11, 88, 102);
		detailsFrame.getContentPane().add(lblImage);
		
		JLabel lblName = new JLabel("NAME ");
		lblName.setBounds(10, 126, 40, 14);
		detailsFrame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(92, 124, 86, 20);
		detailsFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSecondName = new JLabel("SECOND NAME");
		lblSecondName.setBounds(10, 160, 78, 14);
		detailsFrame.getContentPane().add(lblSecondName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(92, 157, 86, 20);
		detailsFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(14, 191, 46, 14);
		detailsFrame.getContentPane().add(lblDni);
		
		textField_2 = new JTextField();
		textField_2.setBounds(92, 188, 86, 20);
		detailsFrame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(10, 224, 46, 14);
		detailsFrame.getContentPane().add(lblEmail);
		
		textField_3 = new JTextField();
		textField_3.setBounds(92, 221, 86, 20);
		detailsFrame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblBirthDate = new JLabel("BIRTHDATE");
		lblBirthDate.setBounds(10, 258, 72, 14);
		detailsFrame.getContentPane().add(lblBirthDate);
		
		textField_4 = new JTextField();
		textField_4.setBounds(92, 255, 86, 20);
		detailsFrame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		detailsFrame.setVisible(true);
	}
}
