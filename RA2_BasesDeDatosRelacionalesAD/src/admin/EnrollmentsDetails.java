package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Enrollment;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class EnrollmentsDetails extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn;
	private JTextField txtDniStudent;
	private JTextField txtCodSubject;
	private Enrollment enrollmentSelected;

	/**
	 * Create the frame.
	 */
	public EnrollmentsDetails(Enrollment enrollmentSelected) {
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
		
		this.enrollmentSelected = enrollmentSelected;

		JLabel lblTitle = new JLabel("These are the details of the enrollment");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(153, 51, 279, 19);
		contentPane.add(lblTitle);

		JLabel lblDniStudent = new JLabel("Student's ID:");
		lblDniStudent.setForeground(Color.LIGHT_GRAY);
		lblDniStudent.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDniStudent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDniStudent.setBounds(41, 147, 110, 13);
		contentPane.add(lblDniStudent);

		JLabel lblCodSubject = new JLabel("Subject's code:");
		lblCodSubject.setForeground(Color.LIGHT_GRAY);
		lblCodSubject.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(41, 271, 110, 13);
		contentPane.add(lblCodSubject);

		txtDniStudent = new JTextField();
		txtDniStudent.setBounds(161, 145, 329, 19);
		txtDniStudent.setText(this.enrollmentSelected.getDniStudent());
		txtDniStudent.setEditable(false);
		contentPane.add(txtDniStudent);
		txtDniStudent.setColumns(10);

		txtCodSubject = new JTextField();
		txtCodSubject.setColumns(10);
		txtCodSubject.setBounds(161, 269, 329, 19);
		txtCodSubject.setText(String.valueOf(this.enrollmentSelected.getCodSubject()));
		txtCodSubject.setEditable(false);
		contentPane.add(txtCodSubject);

		btnReturn = new JButton();
		btnReturn.setContentAreaFilled(false);
		btnReturn.setFocusPainted(false);
		btnReturn.setToolTipText("Return");
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(276, 390, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/cancel.png");
		btnReturn.setIcon(returnImage);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnReturn.addActionListener(mE);

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnReturn) {
				Enrollments frame = new Enrollments();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
