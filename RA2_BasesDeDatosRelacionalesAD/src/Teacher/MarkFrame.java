package Teacher;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.Calification;
import Sql_FuctionsAndFuctions.SlqAndFuctions;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

@SuppressWarnings("serial")
public class MarkFrame extends JFrame{
	private int subjectCode;
	private String studentDni;
	private int raId;
	private String subjectName;
	private JTextField textMark;
	public MarkFrame(int subjectCodeInput,String studentDniInput,int raIdInput,String subjectNameInput)
	{
		super("MARK");
		getContentPane().setBackground(Color.DARK_GRAY);
		
		subjectCode=subjectCodeInput;
		studentDni=studentDniInput;
		raId=raIdInput;
		subjectName=subjectNameInput;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(476,209);
		setLocationRelativeTo(null);
		
		
		String raName="";
		{
			SlqAndFuctions saf=new SlqAndFuctions();
			ResultSet res;
			try {
				res = saf.consultDBSpec("ra","ID",raId);
				if(res.next())
				{
					raName=res.getString("NAME");
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
		
		JLabel labelLocation=new JLabel();
		labelLocation.setForeground(Color.LIGHT_GRAY);
		labelLocation.setFont(new Font("Rockwell", Font.BOLD, 13));
		labelLocation.setBounds(42, 26, 396, 14);
		labelLocation.setText("SUBJECT: "+subjectName+"  STUDENT: "+studentDni+"  RA: "+raName);
		getContentPane().add(labelLocation);
		
		
		JLabel labelMark=new JLabel();
		labelMark.setForeground(Color.LIGHT_GRAY);
		labelMark.setFont(new Font("Rockwell", Font.BOLD, 13));
		labelMark.setBounds(42, 72, 50, 14);
		labelMark.setText("Mark:");
		getContentPane().add(labelMark);
		
		textMark=new JTextField();
		textMark.setFont(new Font("Rockwell", Font.BOLD, 11));
		textMark.setBackground(Color.GRAY);
		textMark.setBounds(102, 70, 86, 20);
		textMark.setEditable(true);
		textMark.setColumns(10);
		getContentPane().add(textMark);
		
		
		JButton buttonCancel=new JButton();
		buttonCancel.setBackground(Color.DARK_GRAY);
		buttonCancel.setBounds(405, 120, 33, 33);
		ImageIcon cancelIcon=new ImageIcon("icons/cancel.png");
		buttonCancel.setIcon(cancelIcon);
		buttonCancel.setBorderPainted(false);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentFrame(subjectCode,studentDni,subjectName).setVisible(true);
				dispose();
			}
		});
		getContentPane().add(buttonCancel);
		
		JButton buttonAccept=new JButton();
		buttonAccept.setBackground(Color.DARK_GRAY);
		buttonAccept.setBounds(42, 120, 33, 33);
		ImageIcon acceptIcon=new ImageIcon("icons/accept.png");
		buttonAccept.setIcon(acceptIcon);
		buttonAccept.setBorderPainted(false);
		buttonAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=textMark.getText();
				
				float markValue;
				try
				{
					markValue=Float.valueOf(text);
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(null,"Invalid mark");
					return;
				}
				
				Calification calification=new Calification(studentDni,raId,markValue);
				boolean insert=true;
				{
					ResultSet res;
					try {
						res = SlqAndFuctions.consultDB("calification");
						while(res.next())
						{
							String dni=res.getString("DNI_STUDENT");
							int id=res.getInt("ID_RA");
							if(dni.equals(studentDni) && id==raId)
							{
								insert=false;
								break;
							}
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if(insert)
				{
					try {
						SlqAndFuctions.insert(calification);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					try {
						SlqAndFuctions.Update(calification);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				new StudentFrame(subjectCode,studentDni,subjectName).setVisible(true);
				dispose();
			}
		});
		getContentPane().add(buttonAccept);
		setVisible(true);
	}
}
