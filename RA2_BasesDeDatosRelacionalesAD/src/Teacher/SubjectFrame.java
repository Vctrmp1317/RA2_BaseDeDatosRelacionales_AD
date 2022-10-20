package Teacher;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Subject;
import Sql_FuctionsAndFuctions.SlqAndFuctions;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

@SuppressWarnings("serial")
public class SubjectFrame extends JFrame {
	private int subjectCode;
	private ArrayList<String> studentDnis;
	private Subject subject;
	public SubjectFrame(int subjectCodeInput)
	{
		super("SUBJECT");
		getContentPane().setBackground(Color.DARK_GRAY);
		
		subjectCode=subjectCodeInput;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(507,546);
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
		getContentPane().setLayout(null);
		
		ImageIcon imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		setIconImage(image);
		
		
		JLabel labelName=new JLabel();
		labelName.setBounds(175, 5, 141, 16);
		labelName.setForeground(Color.LIGHT_GRAY);
		labelName.setFont(new Font("Rockwell", Font.BOLD, 13));
		labelName.setText("SUBJECT: "+subject.getName());
		getContentPane().add(labelName);
		
		
		String col[]={"Student"};
		DefaultTableModel tableModel=new DefaultTableModel(col,0);
		
		JTable table=new JTable(tableModel);
		table.setDefaultEditor(Object.class,null);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(19, 26, 452, 427);
		getContentPane().add(scrollPane);
		
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
		buttonSelect.setBounds(194, 469, 122, 23);
		buttonSelect.setFont(new Font("Rockwell", Font.BOLD, 11));
		buttonSelect.setBackground(Color.GRAY);
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
		getContentPane().add(buttonSelect);
		
		
		JButton buttonBack=new JButton();
		buttonBack.setBackground(Color.DARK_GRAY);
		buttonBack.setBounds(438, 464, 33, 33);
		ImageIcon backIcon =new ImageIcon("icons/cancel.png");
		buttonBack.setIcon(backIcon);
		buttonBack.setBorderPainted(false);
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeacherMenu();
				dispose();
			}
		});
		getContentPane().add(buttonBack);
	}
}
