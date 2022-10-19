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

import Classes.Calification;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

@SuppressWarnings("serial")
public class StudentFrame extends JFrame {
	private int subjectCode;
	private String studentDni;
	private String subjectName;
	private ArrayList<Calification> califications;
	private ArrayList<Integer> raIds;
	public StudentFrame(int subjectCodeInput,String studentDniInput,String subjectNameInput)
	{
		super("STUDENT");
		
		subjectCode=subjectCodeInput;
		studentDni=studentDniInput;
		subjectName=subjectNameInput;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(500,600);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		
		JLabel labelLocation=new JLabel();
		labelLocation.setText("SUBJECT: "+subjectName+"  STUDENT: "+studentDni);
		add(labelLocation);
		
		
		String col[]={"RA","Calification"};
		DefaultTableModel tableModel=new DefaultTableModel(col,0);
		
		JTable table=new JTable(tableModel);
		table.setDefaultEditor(Object.class,null);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		califications=new ArrayList<Calification>();
		{
			ResultSet res;
			try {
				res = SlqAndFuctions.consultDB("calification");
				while(res.next())
				{
					String dni=res.getString("DNI_STUDENT");
					if(dni.equals(studentDni))
					{
						califications.add
						(
							new Calification(
									res.getString("DNI_STUDENT"),
									res.getInt("ID_RA"),
									res.getFloat("MARK")
								)
							);
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		float finalMark=0;
		
		raIds=new ArrayList<Integer>();
		{
			ResultSet res;
			try {
				res = SlqAndFuctions.consultDB("ra");
				while(res.next())
				{
					int code=res.getInt("COD_SUBJECT");
					if(code==subjectCode)
					{
						int raId=res.getInt("ID");
						raIds.add(raId);
						String calificationStr="";
						for(int i=0;i<califications.size();i++)
						{
							if(califications.get(i).getIdRa()==raId)
							{
								float mark=califications.get(i).getMark();
								calificationStr=String.valueOf(mark);
								
								finalMark+=mark*res.getFloat("PERCENTAGE");
								
								break;
							}
						}
						tableModel.addRow(new Object[]{res.getString("NAME"),calificationStr});
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		JLabel labelFinalMark=new JLabel();
		labelFinalMark.setText("FINAL MARK: "+String.valueOf(finalMark));
		add(labelFinalMark);
		
		JButton buttonMark=new JButton();
		buttonMark.setText("Mark/Modify mark");
		buttonMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row!=-1)
				{
					new MarkFrame(subjectCode,studentDni,raIds.get(row),subjectName).setVisible(true);
					dispose();
				}
			}
		});
		add(buttonMark);
		
		JButton buttonBack=new JButton();
		buttonBack.setText("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SubjectFrame(subjectCode).setVisible(true);
				dispose();
			}
		});
		add(buttonBack);
	}
}
