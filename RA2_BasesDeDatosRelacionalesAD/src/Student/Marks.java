package Student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Marks {

	private JFrame markFrame;
	private JTable table;
	private JTextField textRaMark;
	private JLabel lblPercentage;
	private JTextField textPercentage;
	private JTextField textField;
	private JPanel panelMarks;
	private JTextArea textArea;

	public Marks() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		markFrame = new JFrame();
		markFrame.setBounds(100, 100, 450, 300);
		markFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		markFrame.setResizable(false);
		markFrame.setLocationRelativeTo(null);
		markFrame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 158, 239);
		markFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(Color.LIGHT_GRAY);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JTable source = (JTable) e.getSource();
				 source.getSelectedRow();
				 SlqAndFuctions saf = new SlqAndFuctions();

					String dni = Login.dni;
					
				 try {
					ResultSet rs=saf.consultDBSpec("enrollment","COD_SUB", Subjects.codSub);
					
					ResultSet res = null;
					String nameRa = (String) table.getValueAt(table.getSelectedRow(), 0);
				
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 markFrame.getContentPane().add(panelMarks);
				 
			}
		});

		scrollPane.setViewportView(table);
		
		 panelMarks = new JPanel();
		panelMarks.setBounds(206, 11, 218, 173);
		markFrame.getContentPane().add(panelMarks);
		panelMarks.setLayout(null);
		
		JLabel lblMark = new JLabel("MARK");
		lblMark.setBounds(10, 11, 46, 14);
		panelMarks.add(lblMark);
		
		textRaMark = new JTextField();
		textRaMark.setBounds(89, 8, 86, 20);
		panelMarks.add(textRaMark);
		textRaMark.setColumns(10);
		
		lblPercentage = new JLabel("PERCENTAGE");
		lblPercentage.setBounds(10, 45, 65, 14);
		panelMarks.add(lblPercentage);
		
		textPercentage = new JTextField();
		textPercentage.setBounds(89, 42, 86, 20);
		panelMarks.add(textPercentage);
		textPercentage.setColumns(10);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setBounds(10, 79, 75, 14);
		panelMarks.add(lblDescription);
		
		textArea = new JTextArea();
		textArea.setBounds(89, 73, 119, 89);
		panelMarks.add(textArea);
		
		JLabel lblFinalMark = new JLabel("FINAL MARK");
		lblFinalMark.setBounds(206, 211, 68, 14);
		
		
		textField = new JTextField();
		textField.setBounds(294, 208, 86, 20);
		markFrame.getContentPane().add(textField);
		textField.setColumns(10);

		MyModel m = new MyModel();
		m.Model();

		markFrame.setVisible(true);
	}

	private class MyModel extends DefaultTableModel {

		public boolean isCellEditable(int filas, int columnas) {
			return false;
		}

		private void Model() throws ClassNotFoundException, SQLException {
			String[] columns = { "NAME", "PERCENTAGE" };
			MyModel m = new MyModel();
			m.setColumnIdentifiers(columns);
			SlqAndFuctions saf = new SlqAndFuctions();

			String dni = Login.dni;
			
			ResultSet rs = saf.consultDBSpec("enrollment", "DNI_STUDENT", dni);
			ResultSet res = null;
			String codRa = "";
			while (rs.next()) {
				codRa = String.valueOf(rs.getInt("COD_SUBJECT"));
				
				res = saf.consultDBSpec("ra", "COD_SUBJECT", codRa);
				res.next();
				
				m.addRow(new Object[] { res.getString("NAME"), res.getFloat("PERCENTAGE") });

			}

			table.setModel(m);
		}
	}
}
