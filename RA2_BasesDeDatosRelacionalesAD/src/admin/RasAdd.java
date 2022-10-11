package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Ra;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Enter the data of the RA to add");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(192, 21, 202, 13);
		contentPane.add(lblTitle);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(50, 88, 85, 13);
		contentPane.add(lblId);

		JLabel lblCodSubject = new JLabel("Subject's Code:");
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(50, 142, 85, 13);
		contentPane.add(lblCodSubject);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 201, 85, 13);
		contentPane.add(lblName);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(50, 259, 85, 13);
		contentPane.add(lblDescription);
		
		JLabel lblPercentage = new JLabel("Percentage:");
		lblPercentage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPercentage.setBounds(50, 323, 85, 13);
		contentPane.add(lblPercentage);

		txtId = new JTextField();
		txtId.setBounds(161, 85, 329, 19);
		contentPane.add(txtId);
		txtId.setColumns(11);

		txtCodSubject = new JTextField();
		txtCodSubject.setColumns(11);
		txtCodSubject.setBounds(161, 139, 329, 19);
		contentPane.add(txtCodSubject);

		txtName = new JTextField();
		txtName.setColumns(30);
		txtName.setBounds(161, 198, 329, 19);
		contentPane.add(txtName);

		txtDescription = new JTextField();
		txtDescription.setColumns(100);
		txtDescription.setBounds(161, 256, 329, 19);
		contentPane.add(txtDescription);
		
		txtPercentage = new JTextField();
		txtPercentage.setBounds(161, 320, 329, 19);
		contentPane.add(txtPercentage);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(161, 396, 85, 21);
		contentPane.add(btnAdd);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(322, 396, 85, 21);
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
					Ra r = new Ra(Integer.parseInt(txtId.getText()), Integer.parseInt(txtCodSubject.getText()), txtName.getText(), txtDescription.getText(), Float.parseFloat(txtPercentage.getText()));
					SlqAndFuctions.insert(r);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "The RA could not be added. The subject's code does not belong to any RA in the database or there are missing data to enter. You will return to the previous tab.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
