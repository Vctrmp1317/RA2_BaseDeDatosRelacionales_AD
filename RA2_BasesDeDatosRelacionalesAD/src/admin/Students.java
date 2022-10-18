package admin;

import java.awt.Image;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Students extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MyModel model = new MyModel();
	private JButton btnUpdate, btnDelete, btnReturn;
	private Student studentSelected;

	/**
	 * Create the frame.
	 */
	public Students() {
		super("STUDENTS");
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

		JLabel lblTitle = new JLabel("Students");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(257, 23, 72, 28);
		contentPane.add(lblTitle);

		btnUpdate = new JButton();
		btnUpdate.setToolTipText("Update");
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBounds(77, 396, 33, 33);
		ImageIcon updateImage = new ImageIcon("icons/uptadte.png");
		btnUpdate.setIcon(updateImage);
		contentPane.add(btnUpdate);

		btnDelete = new JButton();
		btnDelete.setToolTipText("Delete");
		btnDelete.setFocusPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setBounds(250, 396, 33, 33);
		ImageIcon deleteImage = new ImageIcon("icons/delete.png");
		btnDelete.setIcon(deleteImage);
		contentPane.add(btnDelete);

		btnReturn = new JButton();
		btnReturn.setToolTipText("Return");
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(422, 396, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/disconnect.png");
		btnReturn.setIcon(returnImage);
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

					try {
						ResultSet rs = SlqAndFuctions.consultDB("student");
						while (rs.next()) {
							String dni = rs.getString("DNI");
							String name = rs.getString("NAME");
							String secondName = rs.getString("SECOND_NAME");
							String email = rs.getString("EMAIL");
							String route = rs.getString("ROUTE_IMG");
							Date birthdate = rs.getDate("BIRTHDATE");

							if (dniSelected.equals(dni) && nameSelected.equals(name) && secondNameSelected.equals(secondName) && emailSelected.equals(email) && birthdateSelected.equals(birthdate)) {
								studentSelected = new Student(dni, name, secondName, email, route, birthdate);
							}
						}
					} catch (Exception ex) {
					}
				} else if(Mouse_evt.getClickCount() == 2) {
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
				StudentsUpdate frame = new StudentsUpdate(studentSelected);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnDelete) {
				StudentsDelete frame = new StudentsDelete(studentSelected);
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
			}catch(Exception ex) {}
		}
	}
}
