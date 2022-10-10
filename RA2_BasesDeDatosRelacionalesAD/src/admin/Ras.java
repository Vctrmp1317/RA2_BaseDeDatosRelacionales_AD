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

import Classes.Ra;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ras extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private JButton btnAdd, btnUpdate, btnDelete, btnReturn;
	private Ra raSelected;

	/**
	 * Create the frame.
	 */
	public Ras() {
		super("RAS");
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
			String[] columnas = { "Id", "Cod Subject", "Name", "Description", "Percentage" };
			model.setColumnIdentifiers(columnas);
			ResultSet rs = SlqAndFuctions.consultDB("ra");
			while (rs.next()) {
				int id = rs.getInt("ID");
				int codSubject = rs.getInt("COD_SUBJECT");
				String name = rs.getString("NAME");
				String description = rs.getString("DESCRIPTION");
				float percentage = rs.getFloat("PERCENTAGE");

				Object[] fila = new Object[5];
				fila[0] = id;
				fila[1] = codSubject;
				fila[2] = name;
				fila[3] = description;
				fila[4] = percentage;

				model.addRow(fila);
			}
		} catch (Exception ex) {
		}
		table.setModel(model);
		panel1.add(new JScrollPane(table));
		contentPane.add(panel1);

		JLabel lblTitle = new JLabel("RA's");
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
					int idSelected = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					int codSelected = (Integer) table.getValueAt(table.getSelectedRow(), 1);
					String nameSelected = (String) table.getValueAt(table.getSelectedRow(), 2);
					String descriptionSelected = (String) table.getValueAt(table.getSelectedRow(), 3);
					float percentageSelected = (Float) table.getValueAt(table.getSelectedRow(), 4);

					Object[] filaSelected = new Object[4];
					filaSelected[0] = idSelected;
					filaSelected[1] = codSelected;
					filaSelected[2] = nameSelected;
					filaSelected[3] = descriptionSelected;
					filaSelected[4] = percentageSelected;

					try {
						ResultSet rs = SlqAndFuctions.consultDB("ra");
						while (rs.next()) {
							int id = rs.getInt("ID");
							int codSubject = rs.getInt("COD_SUBJECT");
							String name = rs.getString("NAME");
							String description = rs.getString("DESCRIPTION");
							float percentage = rs.getFloat("PERCENTAGE");

							Object[] fila = new Object[5];
							fila[0] = id;
							fila[1] = codSubject;
							fila[2] = name;
							fila[3] = description;
							fila[4] = percentage;

							if (filaSelected[0].equals(fila[0]) && filaSelected[1].equals(fila[1])
									&& filaSelected[2].equals(fila[2]) && filaSelected[3].equals(fila[3])
									&& filaSelected[4].equals(fila[4])) {
								raSelected.setId(id);
								raSelected.setCodSubject(codSubject);
								raSelected.setName(name);
								raSelected.setDescription(description);
								raSelected.setPercentage(percentage);
							}
						}
					} catch (Exception ex) {
					}

				} else if (Mouse_evt.getClickCount() == 2) {
					RasDetails frame = new RasDetails(raSelected);
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
