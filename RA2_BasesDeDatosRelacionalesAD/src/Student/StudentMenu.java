package Student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

import javax.swing.JButton;

public class StudentMenu {

	private JFrame menuFrame;
	private JMenuBar mb;
	private JMenu menu1, menu2, menu3;
	private JMenuItem mi1, mi2, mi3;
	public static JTable table;
	private JButton btnClose, btnViewMarks;

	public StudentMenu() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		menuFrame = new JFrame();
		menuFrame.setResizable(false);
		menuFrame.setBounds(100, 100, 328, 300);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLocationRelativeTo(null);
		
		ManMenu mm = new ManMenu();
		mb = new JMenuBar();
		menuFrame.setJMenuBar(mb);
		menu1 = new JMenu("Profile");
		mb.add(menu1);
		menu2 = new JMenu("Details");
		menu1.add(menu2);
		menu3 = new JMenu("Connection Options");
		menu1.add(menu3);

		mi1 = new JMenuItem("All Details");
		menu2.add(mi1);
		mi1.addActionListener(mm);

		mi2 = new JMenuItem("Subjects");
		menu2.add(mi2);
		mi2.addActionListener(mm);


		mi3 = new JMenuItem("Disconnect");
		menu3.add(mi3);
		menuFrame.getContentPane().setLayout(null);
		mi3.addActionListener(mm);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 134, 202);
		menuFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table);

		btnClose = new JButton("Close");
		btnClose.setBounds(194, 205, 108, 23);
		btnClose.addActionListener(mm);
		menuFrame.getContentPane().add(btnClose);
		
			
		MyModel m=new MyModel();
		m.Model();
		menuFrame.setVisible(true);
	}

	private class MyModel extends DefaultTableModel {

		public boolean isCellEditable(int filas, int columnas) {
			return false;
		}

		private void Model() throws ClassNotFoundException, SQLException {
			String[] columns = { "SUBJECT", "MARK" };
			float mark = 0;
			int count=0;
			MyModel m = new MyModel();
			m.setColumnIdentifiers(columns);
			SlqAndFuctions saf = new SlqAndFuctions();

			String dni = Login.dni;
			ResultSet rs = saf.consultDB("subjects");
			
			try {
				
			
				 rs = saf.consultDB("subjects");
			while (rs.next()) {
				mark = saf.getMark(dni, rs.getInt("COD"));
				String name = rs.getString("NAME");
				m.addRow(new Object[] { name, mark });

			}
			}catch(SQLException e1) {
				JOptionPane.showMessageDialog(menuFrame, "THIS USER HAS NOT SUBJECTS");
			}

			table.setModel(m);
		}
	}

	private class ManMenu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == mi1) {
				// Class to details
				try {
					Details d = new Details();
				} catch (ClassNotFoundException | SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (o == mi2) {
				// Class to subjects
				try {
					Subjects sub = new Subjects();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			} else if (o == mi3) {
				try {
					new Login();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				menuFrame.dispose();
			} else if (o == btnClose) {
				try {
					new Login();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				menuFrame.dispose();
			}

		}

	}
}
