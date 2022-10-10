package admin;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Enrollment;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Enrollments extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private JButton btnAdd, btnUpdate, btnDelete, btnReturn;
	private Enrollment enrollmentSelected;

	/**
	 * Create the frame.
	 */
	public Enrollments() {
		super("ENROLLMENTS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBounds(63, 61, 456, 278);
		table = new JTable();

		try {
			model = new DefaultTableModel();
			String[] columnas = { "Dni Student", "Cod Subject" };
			model.setColumnIdentifiers(columnas);
			ResultSet rs = SlqAndFuctions.consultDB("enrollment");
			while (rs.next()) {
				String dniStudent = rs.getString("DNI_STUDENT");
				int codSubject = rs.getInt("COD_SUBJECT");

				Object[] fila = new Object[2];
				fila[0] = dniStudent;
				fila[1] = codSubject;

				model.addRow(fila);
			}
		} catch (Exception ex) {
		}
		table.setModel(model);
		panel1.add(new JScrollPane(table));
		contentPane.add(panel1);

		JLabel lblTitle = new JLabel("Enrollments");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(254, 23, 78, 28);
		contentPane.add(lblTitle);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(44, 396, 85, 21);
		contentPane.add(btnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(180, 396, 85, 21);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(321, 396, 85, 21);
		contentPane.add(btnDelete);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(466, 396, 85, 21);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnAdd.addActionListener(mE);
		btnUpdate.addActionListener(mE);
		btnDelete.addActionListener(mE);
		btnReturn.addActionListener(mE);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
				JTable table = (JTable) Mouse_evt.getSource();
				Point point = Mouse_evt.getPoint();
				int row = table.rowAtPoint(point);
				if (Mouse_evt.getClickCount() == 1) {
					String dniStudentSelected = (String) table.getValueAt(table.getSelectedRow(), 0);
					int codSubjectSelected = (Integer) table.getValueAt(table.getSelectedRow(), 1);

					Object[] filaSelected = new Object[2];
					filaSelected[0] = dniStudentSelected;
					filaSelected[1] = codSubjectSelected;

					try {
						ResultSet rs = SlqAndFuctions.consultDB("enrollment");
						while (rs.next()) {
							String dniStudent = rs.getString("DNI_STUDENT");
							int codSubject = rs.getInt("COD_SUBJECT");

							Object[] fila = new Object[2];
							fila[0] = dniStudent;
							fila[1] = codSubject;

							if (filaSelected[0].equals(fila[0]) && filaSelected[1].equals(fila[1])) {
								enrollmentSelected.setDniStudent(dniStudent);
								enrollmentSelected.setCodSubject(codSubject);
							}
						}
					} catch (Exception ex) {
					}

				} else if (Mouse_evt.getClickCount() == 2) {
					EnrollmentsDetails frame = new EnrollmentsDetails(enrollmentSelected);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				}
			}
		});

	}

	private class ManEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();

			if (o == btnUpdate) {

			} else if (o == btnUpdate) {

			} else if (o == btnDelete) {

			} else if (o == btnReturn) {
				SelectButton frame = new SelectButton();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}
}
