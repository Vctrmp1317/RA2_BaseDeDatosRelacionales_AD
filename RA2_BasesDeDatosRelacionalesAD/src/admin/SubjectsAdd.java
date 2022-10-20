package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Subject;
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

public class SubjectsAdd extends JFrame {

	private JPanel contentPane;
	private JButton btnAdd, btnReturn;
	private JTextField txtCode;
	private JTextField txtHours;
	private JTextField txtName;
	private JTextField txtDniTeacher;

	/**
	 * Create the frame.
	 */
	public SubjectsAdd() {
		super("ADD");
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

		JLabel lblTitle = new JLabel("Enter the data of the subject to add");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(192, 21, 256, 13);
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
		lblHours.setBounds(50, 157, 85, 13);
		contentPane.add(lblHours);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 229, 85, 13);
		contentPane.add(lblName);

		JLabel lblDniTeacher = new JLabel("Teacher's ID:");
		lblDniTeacher.setFont(new Font("Rockwell", Font.BOLD, 12));
		lblDniTeacher.setForeground(Color.LIGHT_GRAY);
		lblDniTeacher.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDniTeacher.setBounds(50, 308, 85, 13);
		contentPane.add(lblDniTeacher);

		txtCode = new JTextField();
		txtCode.setBorder(null);
		txtCode.setBackground(Color.LIGHT_GRAY);
		txtCode.setBounds(161, 85, 329, 19);
		contentPane.add(txtCode);
		txtCode.setColumns(10);

		txtHours = new JTextField();
		txtHours.setBorder(null);
		txtHours.setBackground(Color.LIGHT_GRAY);
		txtHours.setColumns(10);
		txtHours.setBounds(161, 154, 329, 19);
		contentPane.add(txtHours);

		txtName = new JTextField();
		txtName.setBorder(null);
		txtName.setBackground(Color.LIGHT_GRAY);
		txtName.setColumns(10);
		txtName.setBounds(161, 226, 329, 19);
		contentPane.add(txtName);

		txtDniTeacher = new JTextField();
		txtDniTeacher.setBorder(null);
		txtDniTeacher.setBackground(Color.LIGHT_GRAY);
		txtDniTeacher.setColumns(10);
		txtDniTeacher.setBounds(161, 305, 329, 19);
		contentPane.add(txtDniTeacher);

		btnAdd = new JButton();
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBounds(161, 396, 33, 33);
		ImageIcon addIcon = new ImageIcon("icons/add.png");
		btnAdd.setIcon(addIcon);
		contentPane.add(btnAdd);

		btnReturn = new JButton();
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(457, 396, 33, 33);
		ImageIcon returnIcon = new ImageIcon("icons/disconnect.png");
		btnReturn.setIcon(returnIcon);
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
					Subject s = new Subject(Integer.parseInt(txtCode.getText()), Integer.parseInt(txtHours.getText()),
							txtName.getText(), txtDniTeacher.getText());
					SlqAndFuctions.insert(s);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"The subject could not be added. The teacher's ID does not belong to any teacher in the database or there are missing data to enter. You will return to the previous tab.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
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
