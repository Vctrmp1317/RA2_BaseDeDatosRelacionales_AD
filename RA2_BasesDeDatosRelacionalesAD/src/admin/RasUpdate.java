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
import javax.swing.SwingConstants;
import javax.swing.JTextField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		this.raSelected = raSelected;

		JLabel lblTitle = new JLabel("Are you sure to update this RA?");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(182, 21, 222, 13);
		contentPane.add(lblTitle);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(50, 88, 85, 13);
		contentPane.add(lblId);

		JLabel lblCodSubject = new JLabel("Subject's code:");
		lblCodSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodSubject.setBounds(50, 139, 85, 13);
		contentPane.add(lblCodSubject);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(50, 197, 85, 13);
		contentPane.add(lblName);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(50, 252, 85, 13);
		contentPane.add(lblDescription);
		
		JLabel lblPercentage = new JLabel("Percentage:");
		lblPercentage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPercentage.setBounds(50, 309, 85, 13);
		contentPane.add(lblPercentage);

		txtId = new JTextField();
		txtId.setBounds(161, 85, 329, 19);
		txtId.setText(String.valueOf(this.raSelected.getId()));
		txtId.setEditable(false);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtCodSubject = new JTextField();
		txtCodSubject.setColumns(10);
		txtCodSubject.setBounds(161, 136, 329, 19);
		txtCodSubject.setText(String.valueOf(this.raSelected.getCodSubject()));
		txtCodSubject.setEditable(false);
		contentPane.add(txtCodSubject);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(161, 194, 329, 19);
		txtName.setText(this.raSelected.getName());
		contentPane.add(txtName);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(161, 249, 329, 19);
		txtDescription.setText(this.raSelected.getDescription());
		contentPane.add(txtDescription);
		
		txtPercentage = new JTextField();
		txtPercentage.setText(String.valueOf(this.raSelected.getPercentage()));
		txtPercentage.setColumns(10);
		txtPercentage.setBounds(161, 306, 329, 19);
		contentPane.add(txtPercentage);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(98, 390, 85, 21);
		contentPane.add(btnConfirm);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(386, 390, 85, 21);
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
