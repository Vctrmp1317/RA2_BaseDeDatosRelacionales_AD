package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Subject;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class SubjectsDelete extends JFrame {

	private JPanel contentPane;
	private JButton btnConfirm, btnReturn;
	private JTextField txtCode;
	private JTextField txtHours;
	private JTextField txtName;
	private JTextField txtDniTeacher;
	private Subject subjectSelected;

	/**
	 * Create the frame.
	 */
	public SubjectsDelete(Subject subjectSelected) {
		super("DELETE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		this.subjectSelected = subjectSelected;

		ImageIcon imageIcon;
		imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		setIconImage(image);

		JLabel lblTitle = new JLabel("Are you sure to delete this subject?");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(152, 21, 281, 13);
		contentPane.add(lblTitle);

		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblCode.setForeground(Color.LIGHT_GRAY);
		lblCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCode.setBounds(50, 88, 85, 13);
		contentPane.add(lblCode);

		JLabel lblHours = new JLabel("Hours:");
		lblHours.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblHours.setForeground(Color.LIGHT_GRAY);
		lblHours.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHours.setBounds(50, 158, 85, 13);
		contentPane.add(lblHours);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 230, 85, 13);
		contentPane.add(lblName);

		JLabel lblDniTeacher = new JLabel("Teacher's ID:");
		lblDniTeacher.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblDniTeacher.setForeground(Color.LIGHT_GRAY);
		lblDniTeacher.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDniTeacher.setBounds(50, 309, 85, 13);
		contentPane.add(lblDniTeacher);

		txtCode = new JTextField();
		txtCode.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtCode.setBackground(Color.GRAY);
		txtCode.setBounds(161, 85, 329, 19);
		txtCode.setText(String.valueOf(this.subjectSelected.getCod()));
		txtCode.setEditable(false);
		contentPane.add(txtCode);
		txtCode.setColumns(10);

		txtHours = new JTextField();
		txtHours.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtHours.setBackground(Color.GRAY);
		txtHours.setColumns(10);
		txtHours.setBounds(161, 155, 329, 19);
		txtHours.setText(String.valueOf(this.subjectSelected.getHours()));
		txtHours.setEditable(false);
		contentPane.add(txtHours);

		txtName = new JTextField();
		txtName.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtName.setBackground(Color.GRAY);
		txtName.setColumns(10);
		txtName.setBounds(161, 227, 329, 19);
		txtName.setText(this.subjectSelected.getName());
		txtName.setEditable(false);
		contentPane.add(txtName);

		txtDniTeacher = new JTextField();
		txtDniTeacher.setFont(new Font("Rockwell", Font.BOLD, 11));
		txtDniTeacher.setBackground(Color.GRAY);
		txtDniTeacher.setColumns(10);
		txtDniTeacher.setBounds(161, 306, 329, 19);
		txtDniTeacher.setText(this.subjectSelected.getDniTeacher());
		txtDniTeacher.setEditable(false);
		contentPane.add(txtDniTeacher);

		btnConfirm = new JButton();
		btnConfirm.setBackground(Color.DARK_GRAY);
		btnConfirm.setToolTipText("Confirm");
		btnConfirm.setBounds(152, 390, 33, 33);
		ImageIcon confirmIcon = new ImageIcon("icons/accept.png");
		btnConfirm.setIcon(confirmIcon);
		btnConfirm.setBorderPainted(false);
		contentPane.add(btnConfirm);

		btnReturn = new JButton();
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setToolTipText("Return");
		btnReturn.setBounds(457, 390, 33, 33);
		ImageIcon cancelIcon = new ImageIcon("icons/cancel.png");
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
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnConfirm) {
				try {
					SlqAndFuctions.delete("SUBJECTS", "COD", txtCode.getText());
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Subjects frame = new Subjects();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnReturn) {
				Subjects frame = new Subjects();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
