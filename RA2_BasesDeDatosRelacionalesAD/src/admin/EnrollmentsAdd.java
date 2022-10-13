package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Enrollment;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Enter the data of the enrollment to add");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(176, 21, 233, 13);
		contentPane.add(lblTitle);

		JLabel lblDniStudent = new JLabel("Student's ID:");
		lblDniStudent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDniStudent.setBounds(50, 141, 85, 13);
		contentPane.add(lblDniStudent);

		JLabel lblCodSubject = new JLabel("Subject's Code:");
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(50, 258, 85, 13);
		contentPane.add(lblCodSubject);

		txtDniStudent = new JTextField();
		txtDniStudent.setBounds(161, 138, 329, 19);
		contentPane.add(txtDniStudent);
		txtDniStudent.setColumns(11);

		txtCodSubject = new JTextField();
		txtCodSubject.setColumns(11);
		txtCodSubject.setBounds(161, 255, 329, 19);
		contentPane.add(txtCodSubject);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(161, 396, 85, 21);
		contentPane.add(btnAdd);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(322, 396, 85, 21);
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
