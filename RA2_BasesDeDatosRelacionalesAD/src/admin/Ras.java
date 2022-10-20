package admin;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Ra;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Ras extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MyModel model = new MyModel();
	private JButton btnAdd, btnUpdate, btnDelete, btnReturn;
	private Ra raSelected;

	/**
	 * Create the frame.
	 */
	public Ras() {
		super("RAS");
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

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(63, 61, 456, 278);
		table = new JTable();
		model = new MyModel();
		model.Model();
		table.setModel(model);
		panel1.add(new JScrollPane(table));
		contentPane.add(panel1);

		JLabel lblTitle = new JLabel("RA's");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(264, 23, 58, 28);
		contentPane.add(lblTitle);

		btnAdd = new JButton();
		btnAdd.setContentAreaFilled(false);
		btnAdd.setToolTipText("Add");
		btnAdd.setBorderPainted(false);
		btnAdd.setFocusPainted(false);
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBounds(63, 396, 33, 33);
		ImageIcon addImage = new ImageIcon("icons/agregar-documento.png");
		btnAdd.setIcon(addImage);
		contentPane.add(btnAdd);

		btnUpdate = new JButton();
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setToolTipText("Update");
		btnUpdate.setBorderPainted(false);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBounds(202, 396, 33, 33);
		ImageIcon updateImage = new ImageIcon("icons/uptadte.png");
		btnUpdate.setIcon(updateImage);
		contentPane.add(btnUpdate);

		btnDelete = new JButton();
		btnDelete.setContentAreaFilled(false);
		btnDelete.setToolTipText("Delete");
		btnDelete.setBorderPainted(false);
		btnDelete.setFocusPainted(false);
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setBounds(341, 396, 33, 33);
		ImageIcon deleteImage = new ImageIcon("icons/delete.png");
		btnDelete.setIcon(deleteImage);
		contentPane.add(btnDelete);

		btnReturn = new JButton();
		btnReturn.setContentAreaFilled(false);
		btnReturn.setToolTipText("Return");
		btnReturn.setBorderPainted(false);
		btnReturn.setFocusPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(486, 396, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/disconnect.png");
		btnReturn.setIcon(returnImage);
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
					int codSubjectSelected = (Integer) table.getValueAt(table.getSelectedRow(), 1);
					String nameSelected = (String) table.getValueAt(table.getSelectedRow(), 2);
					String descriptionSelected = (String) table.getValueAt(table.getSelectedRow(), 3);
					float percentageSelected = (Float) table.getValueAt(table.getSelectedRow(), 4);

					try {
						ResultSet rs = SlqAndFuctions.consultDB("ra");
						while (rs.next()) {
							int id = rs.getInt("ID");
							int codSubject = rs.getInt("COD_SUBJECT");
							String name = rs.getString("NAME");
							String description = rs.getString("DESCRIPTION");
							float percentage = rs.getFloat("PERCENTAGE");

							if (idSelected == id && codSubjectSelected == codSubject && nameSelected.equals(name)
									&& descriptionSelected.equals(description) && percentageSelected == percentage) {
								raSelected = new Ra(id, codSubject, name, description, percentage);
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

			if (o == btnAdd) {
				RasAdd frame = new RasAdd();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnUpdate) {
				RasUpdate frame = new RasUpdate(raSelected);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnDelete) {
				RasDelete frame = new RasDelete(raSelected);
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
				String[] columnas = { "Id", "Subject's code", "Name", "Description", "Percentage" };
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
		}
	}
}
