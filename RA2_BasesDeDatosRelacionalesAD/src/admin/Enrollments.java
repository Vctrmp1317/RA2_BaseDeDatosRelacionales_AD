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

import Classes.Enrollment;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Enrollments extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MyModel model = new MyModel();
	private JButton btnAdd, btnDelete, btnReturn;
	private Enrollment enrollmentSelected;

	/**
	 * Create the frame.
	 */
	public Enrollments() {
		super("ENROLLMENTS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.DARK_GRAY);
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
		JScrollPane scrollPane = new JScrollPane(table);
		panel1.add(scrollPane);
		contentPane.add(panel1);

		JLabel lblTitle = new JLabel("Enrollments");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 13));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(238, 23, 109, 28);
		contentPane.add(lblTitle);

		btnAdd = new JButton();
		btnAdd.setContentAreaFilled(false);
		btnAdd.setFocusPainted(false);
		btnAdd.setToolTipText("Add");
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBounds(63, 396, 33, 33);
		ImageIcon addImage = new ImageIcon("icons/add.png");
		btnAdd.setIcon(addImage);
		contentPane.add(btnAdd);

		btnDelete = new JButton();
		btnDelete.setContentAreaFilled(false);
		btnDelete.setFocusPainted(false);
		btnDelete.setToolTipText("Delete");
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setBounds(276, 396, 33, 33);
		ImageIcon deleteImage = new ImageIcon("icons/delete.png");
		btnDelete.setIcon(deleteImage);
		contentPane.add(btnDelete);

		btnReturn = new JButton();
		btnReturn.setContentAreaFilled(false);
		btnReturn.setFocusPainted(false);
		btnReturn.setToolTipText("Return");
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(Color.DARK_GRAY);
		btnReturn.setBounds(486, 396, 33, 33);
		ImageIcon returnImage = new ImageIcon("icons/disconnect.png");
		btnReturn.setIcon(returnImage);
		contentPane.add(btnReturn);

		ManEvent mE = new ManEvent();
		btnAdd.addActionListener(mE);
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

					try {
						ResultSet rs = SlqAndFuctions.consultDB("enrollment");
						while (rs.next()) {
							String dniStudent = rs.getString("DNI_STUDENT");
							int codSubject = rs.getInt("COD_SUBJECT");

							if (dniStudentSelected.equals(dniStudent) && codSubjectSelected == codSubject) {
								enrollmentSelected = new Enrollment(dniStudent, codSubject);
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

			if (o == btnAdd) {
				EnrollmentsAdd frame = new EnrollmentsAdd();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if (o == btnDelete) {
				EnrollmentsDelete frame = new EnrollmentsDelete(enrollmentSelected);
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
				String[] columnas = { "Student's ID", "Subject's code" };
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
		}
	}
}
