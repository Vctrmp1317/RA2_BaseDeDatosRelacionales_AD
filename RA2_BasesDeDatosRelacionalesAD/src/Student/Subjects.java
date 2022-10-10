package Student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
		scrollPane.setViewportView(table);
		
		MyModel m = new MyModel();
		m.Model();

		subjectsFrame.setVisible(true);
	}

	private class MyModel extends DefaultTableModel {
		private void Model() throws ClassNotFoundException, SQLException {
			String[] columns = { "NAME", "COD" };
			MyModel m = new MyModel();
			m.setColumnIdentifiers(columns);
			SlqAndFuctions saf = new SlqAndFuctions();

			String dni = Login.dni;
			ResultSet rs = saf.consultDB("student");
			boolean search = rs.next();
			boolean incorrect = false;
			while (search) {
				if (dni.equals(rs.getString("DNI")))
					search = false;
				else {
					rs.next();
					incorrect = true;
				}
			}

			ResultSet res = saf.consultDB("enrollment");
			int codSub=0;
			while (res.next()) {
				if (res.getString("DNI_STUDENT").equals(dni)) {
					 codSub = res.getInt("COD_SUBJECT");

				}
				}
				res = saf.consultDB("subjects");
				while (res.next()) {
					if(res.getInt("COD")==codSub) {
						m.addRow(new Object[] { res.getString("NAME"), res.getInt("COD") });
				}
			
			table.setModel(m);
	}
		}

	}

}
