package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Subject;
import Classes.Teacher;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class TeachersDelete extends JFrame {

	private JPanel contentPane;
	private JButton btnConfirm, btnReturn;
	private JTextField txtDni;
	private JTextField txtName;
	private JTextField txtSecondName;
	private JTextField txtEmail;
	private Teacher teacherSelected;

	/**
	 * Create the frame.
	 */
	public TeachersDelete(Teacher teacherSelected) {
		super("DETAILS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		this.teacherSelected = teacherSelected;

		JLabel lblTitle = new JLabel("Are you sure to delete this teacher?");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(169, 21, 248, 13);
		contentPane.add(lblTitle);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(50, 88, 85, 13);
		contentPane.add(lblDni);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 158, 85, 13);
		contentPane.add(lblName);

		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecondName.setBounds(50, 230, 85, 13);
		contentPane.add(lblSecondName);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(50, 309, 85, 13);
		contentPane.add(lblEmail);

		txtDni = new JTextField();
		txtDni.setBounds(161, 85, 329, 19);
		txtDni.setText(this.teacherSelected.getDni());
		txtDni.setEditable(false);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(161, 155, 329, 19);
		txtName.setText(this.teacherSelected.getName());
		txtName.setEditable(false);
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 227, 329, 19);
		txtSecondName.setText(this.teacherSelected.getSecondName());
		txtSecondName.setEditable(false);
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 306, 329, 19);
		txtEmail.setText(this.teacherSelected.getEmail());
		txtEmail.setEditable(false);
		contentPane.add(txtEmail);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(100, 390, 85, 21);
		contentPane.add(btnConfirm);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(400, 390, 85, 21);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnConfirm.addActionListener(mE);
		btnReturn.addActionListener(mE);

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stubz
			Object o = e.getSource();
			if (o == btnConfirm) {
				try {
					PreparedStatement stmt=null;
					stmt=SlqAndFuctions.getConn().prepareStatement("UPDATE subjects SET DNI_TEACHER=?");
					stmt.setString(1, "null");
					stmt.executeUpdate();
					SlqAndFuctions.delete("TEACHERS", "DNI", txtDni.getText());
					SlqAndFuctions.delete("USERS", "DNI", txtDni.getText());
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Teachers frame = new Teachers();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnReturn) {
				Teachers frame = new Teachers();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
