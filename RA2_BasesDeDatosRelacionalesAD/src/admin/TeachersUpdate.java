package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Teacher;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class TeachersUpdate extends JFrame {

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
	public TeachersUpdate(Teacher teacherSelected) {
		super("UPDATE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
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
		
		this.teacherSelected = teacherSelected;

		JLabel lblTitle = new JLabel("Are you sure to update this teacher?");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(169, 21, 248, 13);
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
		lblName.setBounds(50, 158, 85, 13);
		contentPane.add(lblName);

		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setForeground(Color.LIGHT_GRAY);
		lblSecondName.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecondName.setBounds(26, 230, 109, 13);
		contentPane.add(lblSecondName);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(50, 309, 85, 13);
		contentPane.add(lblEmail);

		txtDni = new JTextField();
		txtDni.setBackground(Color.GRAY);
		txtDni.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtDni.setBounds(161, 85, 329, 19);
		txtDni.setText(this.teacherSelected.getDni());
		txtDni.setEditable(false);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtName = new JTextField();
		txtName.setBackground(Color.GRAY);
		txtName.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtName.setColumns(10);
		txtName.setBounds(161, 155, 329, 19);
		txtName.setText(this.teacherSelected.getName());
		contentPane.add(txtName);

		txtSecondName = new JTextField();
		txtSecondName.setBackground(Color.GRAY);
		txtSecondName.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtSecondName.setColumns(10);
		txtSecondName.setBounds(161, 227, 329, 19);
		txtSecondName.setText(this.teacherSelected.getSecondName());
		contentPane.add(txtSecondName);

		txtEmail = new JTextField();
		txtEmail.setBackground(Color.GRAY);
		txtEmail.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 306, 329, 19);
		txtEmail.setText(this.teacherSelected.getEmail());
		contentPane.add(txtEmail);
		
		btnConfirm = new JButton();
		btnConfirm.setBackground(Color.DARK_GRAY);
		btnConfirm.setToolTipText("Confirm");
		btnConfirm.setBounds(152, 390, 33, 33);
		ImageIcon confirmIcon=new ImageIcon("icons/accept.png");
		btnConfirm.setIcon(confirmIcon);
		btnConfirm.setBorderPainted(false);
		contentPane.add(btnConfirm);
		
		btnReturn = new JButton();
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setToolTipText("Return");
		btnReturn.setBounds(457, 390, 33, 33);
		ImageIcon cancelIcon=new ImageIcon("icons/cancel.png");
		btnReturn.setIcon(cancelIcon);
		btnReturn.setBorderPainted(false);
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
					SlqAndFuctions.Update(new Teacher(txtDni.getText(), txtName.getText(), txtSecondName.getText(), txtEmail.getText()));
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
