package admin;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Teacher;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Teachers extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MyModel model = new MyModel();
	private JButton btnAdd, btnUpdate, btnDelete, btnReturn;
	private Teacher teacherSelected;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Teachers() {
		super("TEACHERS");
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
		model = new MyModel();
		model.Model();
		table.setModel(model);
		panel1.add(new JScrollPane(table));
		contentPane.add(panel1);

		JLabel lblTitle = new JLabel("Teachers");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(264, 23, 58, 28);
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
					String dniSelected = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
					String nameSelected = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
					String secondNameSelected = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
					String emailSelected = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));

					try {
						ResultSet rs = SlqAndFuctions.consultDB("teachers");
						while (rs.next()) {
							String dni = rs.getString("DNI");
							String name = rs.getString("NAME");
							String secondName = rs.getString("SECOND_NAME");
							String email = rs.getString("EMAIL");

							if (dniSelected.equals(dni) && nameSelected.equals(name)
									&& secondNameSelected.equals(secondName) && emailSelected.equals(email)) {
								teacherSelected = new Teacher(dni, name, secondName, email);
							}
						}
					} catch (Exception ex) {
					}
				} else if(Mouse_evt.getClickCount() == 2) {
					TeachersDetails frame = new TeachersDetails(teacherSelected);
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

			if (o == btnAdd) {
				TeachersAdd frame = new TeachersAdd();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnUpdate) {

			} else if (o == btnDelete) {
				TeachersDelete frame = new TeachersDelete(teacherSelected);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnReturn) {
				AdminMenu frame = new AdminMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}

		}

	}

	private class MyModel extends DefaultTableModel {

		public boolean isCellEditable(int filas, int columnas) {
			return false;
		}

		private void Model() {
			try {
				String[] columnas = { "DNI", "Name", "Second Name", "E-mail" };
				model.setColumnIdentifiers(columnas);
				ResultSet rs = SlqAndFuctions.consultDB("teachers");
				while (rs.next()) {
					String dni = rs.getString("DNI");
					String name = rs.getString("NAME");
					String secondName = rs.getString("SECOND_NAME");
					String email = rs.getString("EMAIL");

					Object[] fila = new Object[4];
					fila[0] = dni;
					fila[1] = name;
					fila[2] = secondName;
					fila[3] = email;

					model.addRow(fila);
				}
			}catch(Exception ex) {}
		}
	}
}
