package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Classes.Enrollment;
import Sql_FuctionsAndFuctions.SlqAndFuctions;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

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

		JLabel lblTitle = new JLabel("Are you sure to delete this enrollment?");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(172, 32, 271, 19);
		contentPane.add(lblTitle);

		JLabel lblDniStudent = new JLabel("Student's ID:");
		lblDniStudent.setForeground(Color.LIGHT_GRAY);
		lblDniStudent.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDniStudent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDniStudent.setBounds(38, 147, 113, 13);
		contentPane.add(lblDniStudent);

		JLabel lblCodSubject = new JLabel("Subject's code:");
		lblCodSubject.setForeground(Color.LIGHT_GRAY);
		lblCodSubject.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(38, 271, 113, 13);
		contentPane.add(lblCodSubject);

		txtDniStudent = new JTextField();
		txtDniStudent.setBackground(Color.LIGHT_GRAY);
		txtDniStudent.setBounds(161, 145, 329, 19);
		txtDniStudent.setText(this.enrollmentSelected.getDniStudent());
		txtDniStudent.setEditable(false);
		contentPane.add(txtDniStudent);
		txtDniStudent.setColumns(10);

		txtCodSubject = new JTextField();
		txtCodSubject.setBackground(Color.LIGHT_GRAY);
		txtCodSubject.setColumns(10);
		txtCodSubject.setBounds(161, 269, 329, 19);
		txtCodSubject.setText(String.valueOf(this.enrollmentSelected.getCodSubject()));
		txtCodSubject.setEditable(false);
		contentPane.add(txtCodSubject);
		
		btnConfirm = new JButton();
		btnConfirm.setFocusPainted(false);
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setBorderPainted(false);
		btnConfirm.setToolTipText("Confirm");
		btnConfirm.setBackground(Color.DARK_GRAY);
		btnConfirm.setBounds(216, 390, 33, 33);
		ImageIcon confirmImage = new ImageIcon("icons/accept.png");
		btnConfirm.setIcon(confirmImage);
		contentPane.add(btnConfirm);

		btnReturn = new JButton();
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setToolTipText("Return");
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(369, 390, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/cancel.png");
		btnReturn.setIcon(returnImage);
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
