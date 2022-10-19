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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Classes.Teacher;
import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

public class TeacherMenu {
	private JFrame menuFrame;
	private ArrayList<Integer> subjectCodes;
	private ArrayList<JMenuItem> subjectItems;
	
	public TeacherMenu()
	{
		initialize();
	}
	
	
	private void initialize() {
		menuFrame = new JFrame("TEACHER");
		menuFrame.setResizable(false);
		menuFrame.setSize(500,500);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLayout(new FlowLayout());
		
		
		Teacher teacher=null;
		{
			SlqAndFuctions saf=new SlqAndFuctions();
			ResultSet res;
			try {
				res = saf.consultDBSpec("teachers","DNI",Login.dni);
				if(res.next())
				{
					teacher=new Teacher
					(
						res.getString("DNI"),
						res.getString("NAME"),
						res.getString("SECOND_NAME"),
						res.getString("EMAIL")
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
		
		subjectCodes=new ArrayList<Integer>();
		subjectItems=new ArrayList<JMenuItem>();
		
		JMenuBar menuBar=new JMenuBar();
		JMenu menu=new JMenu("Subjects");
		
		ResultSet res;
		try {
			res = SlqAndFuctions.consultDB("subjects");
			while(res.next())
			{
				String dniTeacher = res.getString("DNI_TEACHER");
				if(dniTeacher.equals(teacher.getDni()))
				{
					subjectCodes.add(res.getInt("COD"));
					JMenuItem menuItem=new JMenuItem(res.getString("NAME"));
					subjectItems.add(menuItem);
					menuItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int cod=0;
							for(int i=0;i<subjectItems.size();i++)
							{
								if(e.getSource()==subjectItems.get(i))
								{
									cod=subjectCodes.get(i);
								}
							}
							new SubjectFrame(cod).setVisible(true);
							menuFrame.dispose();
						}});
					menu.add(menuItem);
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		menuBar.add(menu);
		menuFrame.setJMenuBar(menuBar);
		
		
		JLabel labelDni=new JLabel();
		labelDni.setText("DNI:");
		menuFrame.add(labelDni);
		
		JTextField textDni=new JTextField();
		textDni.setEditable(false);
		textDni.setColumns(10);
		textDni.setText(teacher.getDni());
		menuFrame.add(textDni);
		
		
		JLabel labelName=new JLabel();
		labelName.setText("Name:");
		menuFrame.add(labelName);
		
		JTextField textName=new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setText(teacher.getName());
		menuFrame.add(textName);
		
		
		JLabel labelSecondName=new JLabel();
		labelSecondName.setText("Second name:");
		menuFrame.add(labelSecondName);
		
		JTextField textSecondName=new JTextField();
		textSecondName.setEditable(false);
		textSecondName.setColumns(10);
		textSecondName.setText(teacher.getSecondName());
		menuFrame.add(textSecondName);
		
		
		JLabel labelEmail=new JLabel();
		labelEmail.setText("Email:");
		menuFrame.add(labelEmail);
		
		JTextField textEmail=new JTextField();
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setText(teacher.getEmail());
		menuFrame.add(textEmail);
		
		
		JButton buttonBack=new JButton();
		buttonBack.setText("Exit");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Login();
					menuFrame.dispose();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuFrame.add(buttonBack);
		
		
		menuFrame.setVisible(true);
	}
	
}
