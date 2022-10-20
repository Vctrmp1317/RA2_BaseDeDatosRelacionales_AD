package admin;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Ra;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class RasDetails extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn;
	private JTextField txtId;
	private JTextField txtCodSubject;
	private JTextField txtName;
	private JTextField txtDescription;
	private Ra raSelected;
	private JTextField txtPercentage;

	/**
	 * Create the frame.
	 */
	public RasDetails(Ra raSelected) {
		super("DETAILS");
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

		JLabel lblTitle = new JLabel("These are the details of the RA");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(192, 21, 202, 13);
		contentPane.add(lblTitle);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(27, 88, 108, 13);
		contentPane.add(lblId);

		JLabel lblCodSubject = new JLabel("Subject's code:");
		lblCodSubject.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblCodSubject.setForeground(Color.LIGHT_GRAY);
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(27, 139, 108, 13);
		contentPane.add(lblCodSubject);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(27, 197, 108, 13);
		contentPane.add(lblName);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblDescription.setForeground(Color.LIGHT_GRAY);
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(27, 252, 108, 13);
		contentPane.add(lblDescription);

		JLabel lblPercentage = new JLabel("Percentage:");
		lblPercentage.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblPercentage.setForeground(Color.LIGHT_GRAY);
		lblPercentage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPercentage.setBounds(27, 309, 108, 13);
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
		txtName.setEditable(false);
		contentPane.add(txtName);

		txtDescription = new JTextField();
		txtDescription.setBackground(Color.LIGHT_GRAY);
		txtDescription.setColumns(10);
		txtDescription.setBounds(161, 249, 329, 19);
		txtDescription.setText(this.raSelected.getDescription());
		txtDescription.setEditable(false);
		contentPane.add(txtDescription);

		txtPercentage = new JTextField();
		txtPercentage.setBackground(Color.LIGHT_GRAY);
		txtPercentage.setText(String.valueOf(this.raSelected.getPercentage()));
		txtPercentage.setEditable(false);
		txtPercentage.setColumns(10);
		txtPercentage.setBounds(161, 306, 329, 19);
		contentPane.add(txtPercentage);

		btnReturn = new JButton();
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setToolTipText("Return");
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(276, 390, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/cancel.png");
		btnReturn.setIcon(returnImage);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnReturn.addActionListener(mE);

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == btnReturn) {
				Ras frame = new Ras();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
