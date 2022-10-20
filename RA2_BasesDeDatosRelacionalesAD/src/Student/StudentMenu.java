package Student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

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
		menuFrame.setTitle("MENU");
		menuFrame.getContentPane().setBackground(new Color(51, 51, 51));
		menuFrame.setResizable(false);
		menuFrame.setBounds(100, 100, 328, 313);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLocationRelativeTo(null);
		
		ImageIcon imageIcon;
		imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		menuFrame.setIconImage(image);

		
		ManMenu mm = new ManMenu();
		mb = new JMenuBar();
		mb.setBorderPainted(false);
		mb.setBackground(new Color(102, 102, 102));
		mb.setForeground(new Color(102, 102, 102));
		menuFrame.setJMenuBar(mb);
		
		menu1 = new JMenu();
		menu1.setBackground(new Color(102, 102, 102));
		ImageIcon menuIcon = new ImageIcon("icons/menu.png");
		menu1.setIcon(menuIcon);
		mb.add(menu1);
		
		menu2 = new JMenu("Profile");
		menu2.setForeground(new Color(0, 0, 0));
		menu2.setFont(new Font("Rockwell", Font.BOLD, 12));
		menu2.setBackground(new Color(255, 255, 255));
		ImageIcon profileIcon = new ImageIcon("icons/perfil.png");
		menu2.setIcon(profileIcon);
		menu1.add(menu2);
		
		menu3 = new JMenu("Connection Options");
		menu3.setForeground(new Color(0, 0, 0));
		menu3.setFont(new Font("Rockwell", Font.BOLD, 12));
		menu3.setBackground(new Color(255, 255, 255));
		ImageIcon optionsIcon=new ImageIcon("icons/options.png");
		menu3.setIcon(optionsIcon);
		menu1.add(menu3);

		mi1 = new JMenuItem("All Details");
		mi1.setForeground(new Color(0, 0, 0));
		mi1.setFont(new Font("Rockwell", Font.BOLD, 12));
		mi1.setBackground(new Color(255, 255, 255));
		menu2.add(mi1);
		mi1.addActionListener(mm);

		mi2 = new JMenuItem("Subjects");
		mi2.setForeground(new Color(0, 0, 0));
		mi2.setFont(new Font("Rockwell", Font.BOLD, 12));
		mi2.setBackground(new Color(255, 255, 255));
		menu2.add(mi2);
		mi2.addActionListener(mm);


		mi3 = new JMenuItem("Disconnect");
		mi3.setForeground(new Color(0, 0, 0));
		mi3.setBackground(new Color(255, 255, 255));
		mi3.setFont(new Font("Rockwell", Font.BOLD, 12));
		ImageIcon disconnectIcon=new ImageIcon("icons/disconnect.png");
		mi3.setIcon(disconnectIcon);
		menu3.add(mi3);
		menuFrame.getContentPane().setLayout(null);
		mi3.addActionListener(mm);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(10, 26, 134, 202);
		menuFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(Color.LIGHT_GRAY);
		MyModel m=new MyModel();
		m.Model();
		scrollPane.setViewportView(table);

		btnClose = new JButton("");
		btnClose.setToolTipText("Close");
		btnClose.setBackground(new Color(51, 51, 51));
		btnClose.setBounds(269, 196, 25, 25);
		btnClose.addActionListener(mm);
		btnClose.setBorderPainted(false);
		btnClose.setIcon(disconnectIcon);
		
		menuFrame.getContentPane().add(btnClose);
		
	
		menuFrame.setVisible(true);
	}

	private class MyModel extends DefaultTableModel {

		public boolean isCellEditable(int filas, int columnas) {
			return false;
		}

		private void Model() throws ClassNotFoundException, SQLException {
			String[] columns = { "SUBJECT", "MARK" };
			float mark = 0;
			int codSub=0;
			MyModel m = new MyModel();
			m.setColumnIdentifiers(columns);
			SlqAndFuctions saf = new SlqAndFuctions();
			
			String dni = Login.dni;
			try {
			ResultSet rs = saf.consultDBSpec("enrollment", "DNI_STUDENT", dni);
			ResultSet res = null;
			codSub = 0;
			while (rs.next()) {
				codSub = rs.getInt("COD_SUBJECT");
				res = saf.consultDBSpec("subjects", "COD", codSub);
				if(res.next()) {
					if(saf.getMark(dni, codSub)==0) {
						m.addRow(new Object[] { res.getString("NAME"),0 });
					}
					else
						m.addRow(new Object[] { res.getString("NAME"), saf.getMark(dni, codSub) });
				}
			}
			}catch(SQLException e1) {
				
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
					menuFrame.dispose();
				} catch (ClassNotFoundException | SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (o == mi2) {
				// Class to subjects
				try {
					Subjects sub = new Subjects();
					menuFrame.dispose();
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
