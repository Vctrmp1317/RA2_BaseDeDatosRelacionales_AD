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

import Classes.Subject;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Subjects extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MyModel model = new MyModel();
	private JButton btnAdd, btnUpdate, btnDelete, btnReturn;
	private Subject subjectSelected;

	/**
	 * Create the frame.
	 */
	public Subjects() {
		super("SUBJECTS");
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

		ImageIcon imageIcon;
		imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		setIconImage(image);

		JPanel panel1 = new JPanel();
		panel1.setBounds(63, 61, 456, 278);
		table = new JTable();
		model = new MyModel();
		model.Model();
		table.setModel(model);
		panel1.add(new JScrollPane(table));
		contentPane.add(panel1);

		JLabel lblTitle = new JLabel("Subjects");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(264, 23, 58, 28);
		contentPane.add(lblTitle);

		btnAdd = new JButton();
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBorderPainted(false);
		btnAdd.setBounds(63, 396, 33, 33);
		ImageIcon addIcon = new ImageIcon("icons/add.png");
		btnAdd.setIcon(addIcon);
		contentPane.add(btnAdd);

		btnUpdate = new JButton();
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBounds(187, 396, 33, 33);
		ImageIcon updateIcon = new ImageIcon("icons/uptadte.png");
		btnUpdate.setIcon(updateIcon);
		contentPane.add(btnUpdate);

		btnDelete = new JButton();
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(329, 396, 33, 33);
		ImageIcon deleteIcon = new ImageIcon("icons/delete.png");
		btnDelete.setIcon(deleteIcon);
		contentPane.add(btnDelete);

		btnReturn = new JButton();
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBorderPainted(false);
		btnReturn.setBounds(486, 396, 33, 33);
		ImageIcon returnIcon = new ImageIcon("icons/disconnect.png");
		btnReturn.setIcon(returnIcon);
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
					int codSelected = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					int hoursSelected = (Integer) table.getValueAt(table.getSelectedRow(), 1);
					String nameSelected = (String) table.getValueAt(table.getSelectedRow(), 2);
					String dniTeacherSelected = (String) table.getValueAt(table.getSelectedRow(), 3);

					try {
						ResultSet rs = SlqAndFuctions.consultDB("subjects");
						while (rs.next()) {
							int cod = rs.getInt("COD");
							int hours = rs.getInt("HOURS");
							String name = rs.getString("NAME");
							String dniTeacher = rs.getString("DNI_TEACHER");

							if (codSelected == cod && hoursSelected == hours && nameSelected.equals(name)
									&& dniTeacherSelected.equals(dniTeacher)) {
								subjectSelected = new Subject(cod, hours, name, dniTeacher);
							}
						}
					} catch (Exception ex) {
					}

				} else if (Mouse_evt.getClickCount() == 2) {
					SubjectsDetails frame = new SubjectsDetails(subjectSelected);
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
				SubjectsAdd frame = new SubjectsAdd();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnUpdate) {
				SubjectsUpdate frame = new SubjectsUpdate(subjectSelected);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnDelete) {
				SubjectsDelete frame = new SubjectsDelete(subjectSelected);
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
				String[] columnas = { "Code", "Hours", "Name", "DNI Teacher" };
				model.setColumnIdentifiers(columnas);
				ResultSet rs = SlqAndFuctions.consultDB("subjects");
				while (rs.next()) {
					int cod = rs.getInt("COD");
					int hours = rs.getInt("HOURS");
					String name = rs.getString("NAME");
					String dniTeacher = rs.getString("DNI_TEACHER");

					Object[] fila = new Object[4];
					fila[0] = cod;
					fila[1] = hours;
					fila[2] = name;
					fila[3] = dniTeacher;

					model.addRow(fila);
				}
			} catch (Exception ex) {
			}
		}
	}
}
