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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Classes.Teacher;
import LoginAndRegister.Login;
import Sql_FuctionsAndFuctions.SlqAndFuctions;
import java.awt.Color;
import java.awt.Font;

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
		menuFrame.getContentPane().setBackground(Color.DARK_GRAY);
		menuFrame.setResizable(false);
		menuFrame.setSize(500,278);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
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
		menuBar.setForeground(Color.LIGHT_GRAY);
		menuBar.setBackground(Color.GRAY);
		JMenu menu=new JMenu("Subjects");
		menu.setForeground(new Color(0, 0, 0));
		ImageIcon menuIcon = new ImageIcon("icons/menu.png");
		menu.setIcon(menuIcon);
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
		menuFrame.getContentPane().setLayout(null);
		
		
		JLabel labelDni=new JLabel();
		labelDni.setFont(new Font("Rockwell", Font.BOLD, 13));
		labelDni.setForeground(Color.LIGHT_GRAY);
		labelDni.setBounds(10, 36, 43, 14);
		labelDni.setText("DNI:");
		menuFrame.getContentPane().add(labelDni);
		
		JTextField textDni=new JTextField();
		textDni.setBackground(Color.GRAY);
		textDni.setFont(new Font("Rockwell", Font.BOLD, 11));
		textDni.setBounds(90, 34, 168, 20);
		textDni.setEditable(false);
		textDni.setColumns(10);
		textDni.setText(teacher.getDni());
		menuFrame.getContentPane().add(textDni);
		
		
		JLabel labelName=new JLabel();
		labelName.setFont(new Font("Rockwell", Font.BOLD, 13));
		labelName.setForeground(Color.LIGHT_GRAY);
		labelName.setBounds(10, 77, 43, 14);
		labelName.setText("Name:");
		menuFrame.getContentPane().add(labelName);
		
		JTextField textName=new JTextField();
		textName.setBackground(Color.GRAY);
		textName.setFont(new Font("Rockwell", Font.BOLD, 11));
		textName.setBounds(99, 75, 159, 20);
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setText(teacher.getName());
		menuFrame.getContentPane().add(textName);
		
		
		JLabel labelSecondName=new JLabel();
		labelSecondName.setFont(new Font("Rockwell", Font.BOLD, 13));
		labelSecondName.setForeground(Color.LIGHT_GRAY);
		labelSecondName.setBounds(10, 118, 93, 14);
		labelSecondName.setText("Second name:");
		menuFrame.getContentPane().add(labelSecondName);
		
		JTextField textSecondName=new JTextField();
		textSecondName.setBackground(Color.GRAY);
		textSecondName.setFont(new Font("Rockwell", Font.BOLD, 11));
		textSecondName.setBounds(109, 116, 149, 20);
		textSecondName.setEditable(false);
		textSecondName.setColumns(10);
		textSecondName.setText(teacher.getSecondName());
		menuFrame.getContentPane().add(textSecondName);
		
		
		JLabel labelEmail=new JLabel();
		labelEmail.setFont(new Font("Rockwell", Font.BOLD, 13));
		labelEmail.setForeground(Color.LIGHT_GRAY);
		labelEmail.setBounds(10, 156, 59, 14);
		labelEmail.setText("Email:");
		menuFrame.getContentPane().add(labelEmail);
		
		JTextField textEmail=new JTextField();
		textEmail.setBackground(Color.GRAY);
		textEmail.setFont(new Font("Rockwell", Font.BOLD, 11));
		textEmail.setBounds(96, 154, 162, 20);
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setText(teacher.getEmail());
		menuFrame.getContentPane().add(textEmail);
		
		
		JButton buttonBack=new JButton();
		buttonBack.setBackground(Color.DARK_GRAY);
		buttonBack.setBounds(411, 152, 33, 33);
		ImageIcon backIcon =new ImageIcon("icons/cancel.png");
		buttonBack.setIcon(backIcon);
		buttonBack.setBorderPainted(false);
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
		menuFrame.getContentPane().add(buttonBack);
		
		
		menuFrame.setVisible(true);
	}
	
}
