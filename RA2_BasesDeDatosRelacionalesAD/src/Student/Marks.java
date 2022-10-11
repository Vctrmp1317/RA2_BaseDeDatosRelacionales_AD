package Student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
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
	private JLabel lblPercentage, lblMark, lblDescription;
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

		scrollPane.setViewportView(table);

		panelMarks = new JPanel();
		panelMarks.setBounds(206, 11, 218, 173);
		markFrame.getContentPane().add(panelMarks);
		panelMarks.setLayout(null);

		lblMark = new JLabel("MARK");
		lblMark.setBounds(10, 11, 46, 14);

		textRaMark = new JTextField();
		textRaMark.setBounds(89, 8, 86, 20);
		textRaMark.setColumns(10);
		textRaMark.setEditable(false);

		lblPercentage = new JLabel("PERCENTAGE");
		lblPercentage.setBounds(10, 45, 65, 14);

		textPercentage = new JTextField();
		textPercentage.setBounds(89, 42, 86, 20);
		textPercentage.setColumns(10);
		textPercentage.setEditable(false);

		lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setBounds(10, 79, 75, 14);

		textArea = new JTextArea();
		textArea.setBounds(89, 73, 119, 89);
		textArea.setEditable(false);

		panelMarks.add(lblDescription);
		panelMarks.add(lblPercentage);
		panelMarks.add(lblMark);
		lblDescription.setVisible(false);
		lblPercentage.setVisible(false);
		lblMark.setVisible(false);

		JLabel lblFinalMark = new JLabel("FINAL MARK");
		lblFinalMark.setBounds(206, 211, 78, 14);
		markFrame.getContentPane().add(lblFinalMark);

		textField = new JTextField();
		textField.setBounds(294, 208, 86, 20);
		markFrame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

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

					ResultSet res = null;
					String nameRa = (String) table.getValueAt(table.getSelectedRow(), 0);
					PreparedStatement stmt = saf.getConn()
							.prepareStatement("SELECT * FROM ra WHERE NAME =? AND COD_SUBJECT=?");
					stmt.setString(1, nameRa);
					stmt.setInt(2, Subjects.codSub);
					res = stmt.executeQuery();
					res.next();
					ResultSet rs = null;

					stmt = saf.getConn()
							.prepareStatement("SELECT * FROM calification WHERE DNI_STUDENT =? AND ID_RA=?");
					stmt.setString(1, Login.dni);
					stmt.setInt(2, res.getInt("ID"));
					rs = stmt.executeQuery();
					rs.next();
					textRaMark.setText(String.valueOf(rs.getFloat("MARK")));
					textPercentage.setText(String.valueOf(res.getFloat("PERCENTAGE")));
					textArea.setText(res.getString("DESCRIPTION"));
					textArea.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblDescription.setVisible(true);
				lblPercentage.setVisible(true);
				lblMark.setVisible(true);
				panelMarks.add(textRaMark);
				panelMarks.add(textPercentage);
				panelMarks.add(textArea);
				markFrame.getContentPane().add(panelMarks);

			}
		});
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

			// ResultSet rs = saf.consultDBSpec("enrollment", "DNI_STUDENT", dni);
			ResultSet res = null;
			int codRa = 0;

			codRa = Subjects.codSub;

			res = saf.consultDBSpec("ra", "COD_SUBJECT", codRa);

			while (res.next()) {

				m.addRow(new Object[] { res.getString("NAME"), res.getFloat("PERCENTAGE") });

			}

			table.setModel(m);
		}
	}
}
