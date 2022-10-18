package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Enrollment;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class EnrollmentsAdd extends JFrame {

	private JPanel contentPane;
	private JButton btnAdd, btnReturn;
	private JTextField txtDniStudent;
	private JTextField txtCodSubject;

	/**
	 * Create the frame.
	 */
	public EnrollmentsAdd() {
		super("ADD");
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

		JLabel lblTitle = new JLabel("Enter the data of the enrollment to add");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(162, 39, 262, 19);
		contentPane.add(lblTitle);

		JLabel lblDniStudent = new JLabel("Student's ID:");
		lblDniStudent.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDniStudent.setForeground(Color.LIGHT_GRAY);
		lblDniStudent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDniStudent.setBounds(29, 141, 122, 13);
		contentPane.add(lblDniStudent);

		JLabel lblCodSubject = new JLabel("Subject's Code:");
		lblCodSubject.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblCodSubject.setForeground(Color.LIGHT_GRAY);
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(29, 258, 122, 13);
		contentPane.add(lblCodSubject);

		txtDniStudent = new JTextField();
		txtDniStudent.setBackground(Color.LIGHT_GRAY);
		txtDniStudent.setBounds(161, 138, 329, 19);
		contentPane.add(txtDniStudent);
		txtDniStudent.setColumns(11);

		txtCodSubject = new JTextField();
		txtCodSubject.setBackground(Color.LIGHT_GRAY);
		txtCodSubject.setColumns(11);
		txtCodSubject.setBounds(161, 255, 329, 19);
		contentPane.add(txtCodSubject);

		btnAdd = new JButton();
		btnAdd.setFocusPainted(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBorderPainted(false);
		btnAdd.setToolTipText("Add");
		btnAdd.setBounds(202, 396, 33, 33);
		ImageIcon addImage = new ImageIcon("icons/agregar-documento.png");
		btnAdd.setIcon(addImage);
		contentPane.add(btnAdd);

		btnReturn = new JButton();
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBorderPainted(false);
		btnReturn.setToolTipText("Return");
		btnReturn.setBounds(376, 396, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/cancel.png");
		btnReturn.setIcon(returnImage);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnAdd.addActionListener(mE);
		btnReturn.addActionListener(mE);

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			
			if (o == btnAdd) {
				try {
					Enrollment e2 = new Enrollment(txtDniStudent.getText(), Integer.parseInt(txtCodSubject.getText()));
					SlqAndFuctions.insert(e2);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "The enrollment could not be added. The student's ID or the subject's code does not belong to any enrollment in the database or there are missing data to enter. You will return to the previous tab.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				Enrollments frame = new Enrollments();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnReturn) {
				Enrollments frame = new Enrollments();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
