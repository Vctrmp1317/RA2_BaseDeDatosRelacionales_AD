package admin;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Ra;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class RasAdd extends JFrame {

	private JPanel contentPane;
	private JButton btnAdd, btnReturn;
	private JTextField txtId;
	private JTextField txtCodSubject;
	private JTextField txtName;
	private JTextField txtDescription;
	private JTextField txtPercentage;

	/**
	 * Create the frame.
	 */
	public RasAdd() {
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

		JLabel lblTitle = new JLabel("Enter the data of the RA to add");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(205, 21, 202, 13);
		contentPane.add(lblTitle);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(39, 88, 109, 13);
		contentPane.add(lblId);

		JLabel lblCodSubject = new JLabel("Subject's Code:");
		lblCodSubject.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblCodSubject.setForeground(Color.LIGHT_GRAY);
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(39, 142, 109, 13);
		contentPane.add(lblCodSubject);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(39, 201, 109, 13);
		contentPane.add(lblName);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDescription.setForeground(Color.LIGHT_GRAY);
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(39, 259, 109, 13);
		contentPane.add(lblDescription);

		JLabel lblPercentage = new JLabel("Percentage:");
		lblPercentage.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblPercentage.setForeground(Color.LIGHT_GRAY);
		lblPercentage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPercentage.setBounds(39, 323, 109, 13);
		contentPane.add(lblPercentage);

		txtId = new JTextField();
		txtId.setBackground(Color.LIGHT_GRAY);
		txtId.setBounds(161, 85, 329, 19);
		contentPane.add(txtId);
		txtId.setColumns(11);

		txtCodSubject = new JTextField();
		txtCodSubject.setBackground(Color.LIGHT_GRAY);
		txtCodSubject.setColumns(11);
		txtCodSubject.setBounds(161, 139, 329, 19);
		contentPane.add(txtCodSubject);

		txtName = new JTextField();
		txtName.setBackground(Color.LIGHT_GRAY);
		txtName.setColumns(30);
		txtName.setBounds(161, 198, 329, 19);
		contentPane.add(txtName);

		txtDescription = new JTextField();
		txtDescription.setBackground(Color.LIGHT_GRAY);
		txtDescription.setColumns(100);
		txtDescription.setBounds(161, 256, 329, 19);
		contentPane.add(txtDescription);

		txtPercentage = new JTextField();
		txtPercentage.setBackground(Color.LIGHT_GRAY);
		txtPercentage.setBounds(161, 320, 329, 19);
		contentPane.add(txtPercentage);

		btnAdd = new JButton();
		btnAdd.setToolTipText("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBounds(224, 396, 33, 33);
		ImageIcon addImage = new ImageIcon("icons/add.png");
		btnAdd.setIcon(addImage);
		contentPane.add(btnAdd);

		btnReturn = new JButton();
		btnReturn.setToolTipText("Return");
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(374, 396, 33, 33);
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
					Ra r = new Ra(Integer.parseInt(txtId.getText()), Integer.parseInt(txtCodSubject.getText()),
							txtName.getText(), txtDescription.getText(), Float.parseFloat(txtPercentage.getText()));
					SlqAndFuctions.insert(r);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"The RA could not be added. The subject's code does not belong to any RA in the database or there are missing data to enter. You will return to the previous tab.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
				Ras frame = new Ras();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnReturn) {
				Ras frame = new Ras();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
