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

import Classes.Student;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Students extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private JButton btnUpdate, btnDelete, btnReturn;
	private Student studentSelected;

	/**
	 * Create the frame.
	 */
	public Students() {
		super("STUDENTS");
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
			String[] columnas = { "DNI", "Name", "Second Name", "E-mail", "Birthdate" };
			model.setColumnIdentifiers(columnas);
			ResultSet rs = SlqAndFuctions.consultDB("student");
			while (rs.next()) {
				String dni = rs.getString("DNI");
				String name = rs.getString("NAME");
				String secondName = rs.getString("SECOND_NAME");
				String email = rs.getString("EMAIL");
				Date birthdate = rs.getDate("BIRTHDATE");

				Object[] fila = new Object[5];
				fila[0] = dni;
				fila[1] = name;
				fila[2] = secondName;
				fila[3] = email;
				fila[4] = birthdate;

				model.addRow(fila);
			}
		} catch (Exception ex) {
		}
		table.setModel(model);
		panel1.add(new JScrollPane(table));
		contentPane.add(panel1);

		JLabel lblTitle = new JLabel("Students");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(264, 23, 58, 28);
		contentPane.add(lblTitle);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(77, 396, 85, 21);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(250, 396, 85, 21);
		contentPane.add(btnDelete);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(422, 396, 85, 21);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnUpdate.addActionListener(mE);
		btnDelete.addActionListener(mE);
		btnReturn.addActionListener(mE);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
				JTable table = (JTable) Mouse_evt.getSource();
				Point point = Mouse_evt.getPoint();
				int row = table.rowAtPoint(point);
				if (Mouse_evt.getClickCount() == 1) {
					String dniSelected = (String) table.getValueAt(table.getSelectedRow(), 0);
					String nameSelected = (String) table.getValueAt(table.getSelectedRow(), 1);
					String secondNameSelected = (String) table.getValueAt(table.getSelectedRow(), 2);
					String emailSelected = (String) table.getValueAt(table.getSelectedRow(), 3);
					Date birthdateSelected = (Date) table.getValueAt(table.getSelectedRow(), 4);

					Object[] filaSelected = new Object[5];
					filaSelected[0] = dniSelected;
					filaSelected[1] = nameSelected;
					filaSelected[2] = secondNameSelected;
					filaSelected[3] = emailSelected;
					filaSelected[4] = birthdateSelected;

					try {
						ResultSet rs = SlqAndFuctions.consultDB("student");
						while (rs.next()) {
							String dni = rs.getString("DNI");
							String name = rs.getString("NAME");
							String secondName = rs.getString("SECOND_NAME");
							String email = rs.getString("EMAIL");
							Date birthdate = rs.getDate("BIRTHDATE");

							Object[] fila = new Object[5];
							fila[0] = dni;
							fila[1] = name;
							fila[2] = secondName;
							fila[3] = email;
							fila[4] = birthdate;

							if (filaSelected[0].equals(fila[0]) && filaSelected[1].equals(fila[1])
									&& filaSelected[2].equals(fila[2]) && filaSelected[3].equals(fila[3])
									&& filaSelected[4].equals(fila[4])) {
								studentSelected.setDni(dni);
								studentSelected.setName(name);
								studentSelected.setSecondName(secondName);
								studentSelected.setEmail(email);
								studentSelected.setRouteImg(rs.getString("ROUTEIMG"));
								studentSelected.setBirthdate(birthdate);
							}
						}
					} catch (Exception ex) {
					}

				} else if (Mouse_evt.getClickCount() == 2) {
					StudentsDetails frame = new StudentsDetails(studentSelected);
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
