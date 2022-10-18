package admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Teacher;
import Classes.Users;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

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
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		ImageIcon imageIcon;
		imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		setIconImage(image);

		JLabel lblTitle = new JLabel("Enter the data of the teacher to add");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(192, 21, 245, 13);
		contentPane.add(lblTitle);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(Color.LIGHT_GRAY);
		lblDni.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(50, 88, 85, 13);
		contentPane.add(lblDni);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 139, 85, 13);
		contentPane.add(lblName);

		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setForeground(Color.LIGHT_GRAY);
		lblSecondName.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecondName.setBounds(32, 194, 103, 13);
		contentPane.add(lblSecondName);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(50, 249, 85, 13);
		contentPane.add(lblEmail);

		txtDni = new JTextField();
		txtDni.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtDni.setBackground(Color.GRAY);
		txtDni.setBounds(161, 85, 329, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtName = new JTextField();
		txtName.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtName.setBackground(Color.GRAY);
		txtName.setColumns(10);
		txtName.setBounds(161, 136, 329, 19);
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtSecondName.setBackground(Color.GRAY);
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 191, 329, 19);
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtEmail.setBackground(Color.GRAY);
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 246, 329, 19);
		contentPane.add(txtEmail);

		btnAdd = new JButton();
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBounds(161, 396, 33, 33);
		ImageIcon addIcon =new ImageIcon("icons/add.png");
		btnAdd.setIcon(addIcon);
		contentPane.add(btnAdd);

		btnReturn = new JButton();
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(457, 396, 33, 33);
		ImageIcon returnIcon =new ImageIcon("icons/disconnect.png");
		btnReturn.setIcon(returnIcon);
		contentPane.add(btnReturn);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(50, 310, 85, 13);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtPassword.setBackground(Color.GRAY);
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
