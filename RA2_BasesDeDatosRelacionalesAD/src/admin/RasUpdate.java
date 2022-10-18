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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class RasUpdate extends JFrame {

	private JPanel contentPane;
	private JButton btnConfirm, btnReturn;
	private JTextField txtId;
	private JTextField txtCodSubject;
	private JTextField txtName;
	private JTextField txtDescription;
	private Ra raSelected;
	private JTextField txtPercentage;

	/**
	 * Create the frame.
	 */
	public RasUpdate(Ra raSelected) {
		super("UPDATE");
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
		
		this.raSelected = raSelected;

		JLabel lblTitle = new JLabel("Are you sure to update this RA?");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(182, 21, 222, 13);
		contentPane.add(lblTitle);

		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(26, 88, 109, 13);
		contentPane.add(lblId);

		JLabel lblCodSubject = new JLabel("Subject's code:");
		lblCodSubject.setForeground(Color.LIGHT_GRAY);
		lblCodSubject.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(26, 139, 109, 13);
		contentPane.add(lblCodSubject);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(26, 197, 109, 13);
		contentPane.add(lblName);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setForeground(Color.LIGHT_GRAY);
		lblDescription.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(26, 252, 109, 13);
		contentPane.add(lblDescription);
		
		JLabel lblPercentage = new JLabel("Percentage:");
		lblPercentage.setForeground(Color.LIGHT_GRAY);
		lblPercentage.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblPercentage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPercentage.setBounds(26, 309, 109, 13);
		contentPane.add(lblPercentage);

		txtId = new JTextField();
		txtId.setBackground(Color.LIGHT_GRAY);
		txtId.setBounds(161, 85, 329, 19);
		txtId.setText(String.valueOf(this.raSelected.getId()));
		txtId.setEditable(false);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtCodSubject = new JTextField();
		txtCodSubject.setBackground(Color.LIGHT_GRAY);
		txtCodSubject.setColumns(10);
		txtCodSubject.setBounds(161, 136, 329, 19);
		txtCodSubject.setText(String.valueOf(this.raSelected.getCodSubject()));
		txtCodSubject.setEditable(false);
		contentPane.add(txtCodSubject);

		txtName = new JTextField();
		txtName.setBackground(Color.LIGHT_GRAY);
		txtName.setColumns(10);
		txtName.setBounds(161, 194, 329, 19);
		txtName.setText(this.raSelected.getName());
		contentPane.add(txtName);

		txtDescription = new JTextField();
		txtDescription.setBackground(Color.LIGHT_GRAY);
		txtDescription.setColumns(10);
		txtDescription.setBounds(161, 249, 329, 19);
		txtDescription.setText(this.raSelected.getDescription());
		contentPane.add(txtDescription);
		
		txtPercentage = new JTextField();
		txtPercentage.setBackground(Color.LIGHT_GRAY);
		txtPercentage.setText(String.valueOf(this.raSelected.getPercentage()));
		txtPercentage.setColumns(10);
		txtPercentage.setBounds(161, 306, 329, 19);
		contentPane.add(txtPercentage);
		
		btnConfirm = new JButton();
		btnConfirm.setToolTipText("Confirm");
		btnConfirm.setFocusPainted(false);
		btnConfirm.setBorderPainted(false);
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setBackground(Color.DARK_GRAY);
		btnConfirm.setBounds(229, 390, 33, 33);
		ImageIcon confirmImage = new ImageIcon("icons/accept.png");
		btnConfirm.setIcon(confirmImage);
		contentPane.add(btnConfirm);

		btnReturn = new JButton();
		btnReturn.setToolTipText("Return");
		btnReturn.setFocusPainted(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(359, 390, 33, 33);
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
					SlqAndFuctions.Update(new Ra(Integer.parseInt(txtId.getText()), Integer.parseInt(txtCodSubject.getText()), txtName.getText(), txtDescription.getText(), Float.parseFloat(txtPercentage.getText())));
				} catch (Exception ex) {
					System.out.println(ex);
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
