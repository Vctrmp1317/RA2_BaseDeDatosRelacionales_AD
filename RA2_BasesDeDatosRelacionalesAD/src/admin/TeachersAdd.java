package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Teacher;
import Classes.Users;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class TeachersAdd extends JFrame {

	private JPanel contentPane;
	private JButton btnAdd, btnReturn;
	private JTextField txtDni;
	private JTextField txtName;
	private JTextField txtSecondName;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public TeachersAdd() {
		super("ADD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Enter the data of the teacher to add");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(192, 21, 202, 13);
		contentPane.add(lblTitle);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(50, 88, 85, 13);
		contentPane.add(lblDni);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 139, 85, 13);
		contentPane.add(lblName);

		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecondName.setBounds(50, 194, 85, 13);
		contentPane.add(lblSecondName);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(50, 249, 85, 13);
		contentPane.add(lblEmail);

		txtDni = new JTextField();
		txtDni.setBounds(161, 85, 329, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(161, 136, 329, 19);
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 191, 329, 19);
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 246, 329, 19);
		contentPane.add(txtEmail);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(161, 396, 85, 21);
		contentPane.add(btnAdd);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(322, 396, 85, 21);
		contentPane.add(btnReturn);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(50, 310, 85, 13);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(161, 307, 329, 19);
		contentPane.add(txtPassword);

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
					Teacher t = new Teacher(txtDni.getText(), txtName.getText(), txtSecondName.getText(), txtEmail.getText());
					Users u = new Users(txtDni.getText(), txtPassword.getText(), "Teacher");
					SlqAndFuctions.insert(t);
					SlqAndFuctions.inserUser(u);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "The teacher could not be added. Missing data to enter. You will return to the previous tab.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				AdminMenu frame = new AdminMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnReturn) {
				AdminMenu frame = new AdminMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
