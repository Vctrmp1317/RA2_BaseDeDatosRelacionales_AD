package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Classes.Enrollment;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

public class EnrollmentsDelete extends JFrame {

	private JPanel contentPane;
	private Enrollment enrollmentSelected;
	private JTextField txtDniStudent, txtCodSubject;
	private JButton btnConfirm, btnReturn;

	/**
	 * Create the frame.
	 */
	public EnrollmentsDelete(Enrollment enrollmentSelected) {
		super("DELETE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		this.enrollmentSelected = enrollmentSelected;

		JLabel lblTitle = new JLabel("Are you sure to delete this enrollment?");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(172, 21, 241, 13);
		contentPane.add(lblTitle);

		JLabel lblDniStudent = new JLabel("Student's ID:");
		lblDniStudent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDniStudent.setBounds(50, 144, 85, 13);
		contentPane.add(lblDniStudent);

		JLabel lblCodSubject = new JLabel("Subject's code:");
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(50, 268, 85, 13);
		contentPane.add(lblCodSubject);

		txtDniStudent = new JTextField();
		txtDniStudent.setBounds(161, 141, 329, 19);
		txtDniStudent.setText(this.enrollmentSelected.getDniStudent());
		txtDniStudent.setEditable(false);
		contentPane.add(txtDniStudent);
		txtDniStudent.setColumns(10);

		txtCodSubject = new JTextField();
		txtCodSubject.setColumns(10);
		txtCodSubject.setBounds(161, 265, 329, 19);
		txtCodSubject.setText(String.valueOf(this.enrollmentSelected.getCodSubject()));
		txtCodSubject.setEditable(false);
		contentPane.add(txtCodSubject);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(121, 390, 85, 21);
		contentPane.add(btnConfirm);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(378, 390, 85, 21);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnConfirm.addActionListener(mE);
		btnReturn.addActionListener(mE);

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnConfirm) {
				try {
					PreparedStatement stmt=null;
					stmt=SlqAndFuctions.getConn().prepareStatement("DELETE FROM ENROLLMENT WHERE DNI_STUDENT = ? AND COD_SUBJECT = ?");
					stmt.setString(1, txtDniStudent.getText());
					stmt.setInt(2, Integer.parseInt(txtCodSubject.getText()));
					stmt.executeUpdate();
				} catch (Exception ex) {
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
