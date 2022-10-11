package Student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import java.awt.FlowLayout;

public class Subjects {

	private JFrame subjectsFrame;
	private JTable table;
	public static String codSub;

	public Subjects() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void initialize() throws ClassNotFoundException, SQLException {

		subjectsFrame = new JFrame();
		subjectsFrame.setResizable(false);
		subjectsFrame.setBounds(100, 100, 450, 300);
		subjectsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		subjectsFrame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 126, 225);
		subjectsFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(Color.LIGHT_GRAY);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				if (e.getClickCount() == 2) {
					try {
						JTable source = (JTable) e.getSource();
						 source.getSelectedRow();
						 SlqAndFuctions saf = new SlqAndFuctions();
						 codSub = String.valueOf( table.getValueAt(table.getSelectedRow(), 1));
						new Marks();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		scrollPane.setViewportView(table);

		MyModel m = new MyModel();
		m.Model();

		subjectsFrame.setVisible(true);
	}

	private class MyModel extends DefaultTableModel {

		public boolean isCellEditable(int filas, int columnas) {
			return false;
		}

		private void Model() throws ClassNotFoundException, SQLException {
			String[] columns = { "NAME", "COD" };
			MyModel m = new MyModel();
			m.setColumnIdentifiers(columns);
			SlqAndFuctions saf = new SlqAndFuctions();

			String dni = Login.dni;

			ResultSet rs = saf.consultDBSpec("enrollment", "DNI_STUDENT", dni);
			ResultSet res = null;
			codSub = "";
			while (rs.next()) {
				codSub = String.valueOf(rs.getInt("COD_SUBJECT"));
				res = saf.consultDBSpec("subjects", "COD", codSub);
				res.next();
				m.addRow(new Object[] { res.getString("NAME"), res.getInt("COD") });

			}

			table.setModel(m);
		}
	}

}
