package Teacher;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Subject;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

@SuppressWarnings("serial")
public class SubjectFrame extends JFrame {
	private int subjectCode;
	private ArrayList<String> studentDnis;
	private Subject subject;
	public SubjectFrame(int subjectCodeInput)
	{
		super("SUBJECT");
		
		subjectCode=subjectCodeInput;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(500,600);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		
		subject=null;
		{
			SlqAndFuctions saf=new SlqAndFuctions();
			ResultSet res;
			try {
				res = saf.consultDBSpec("subjects","COD",subjectCode);
				if(res.next())
				{
					subject=new Subject
					(
						res.getInt("COD"),
						res.getInt("HOURS"),
						res.getString("NAME"),
						res.getString("DNI_TEACHER")
					);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		JLabel labelName=new JLabel();
		labelName.setText("SUBJECT: "+subject.getName());
		add(labelName);
		
		
		String col[]={"Student"};
		DefaultTableModel tableModel=new DefaultTableModel(col,0);
		
		JTable table=new JTable(tableModel);
		table.setDefaultEditor(Object.class,null);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		studentDnis=new ArrayList<String>();
		ResultSet res;
		try {
			res = SlqAndFuctions.consultDB("enrollment");
			while(res.next())
			{
				int code=res.getInt("COD_SUBJECT");
				if(code==subjectCode)
				{
					studentDnis.add(res.getString("DNI_STUDENT"));
					tableModel.addRow(new Object[]{res.getString("DNI_STUDENT")});
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton buttonSelect=new JButton();
		buttonSelect.setText("Select student");
		buttonSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row!=-1)
				{
					String studentDni=studentDnis.get(row);
					new StudentFrame(subjectCode,studentDni,subject.getName()).setVisible(true);
					dispose();
				}
			}
		});
		add(buttonSelect);
		
		
		JButton buttonBack=new JButton();
		buttonBack.setText("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeacherMenu();
				dispose();
			}
		});
		add(buttonBack);
	}
}
