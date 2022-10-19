package Teacher;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.Calification;
import Sql_FuctionsAndFuctions.SlqAndFuctions;

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
		
		subjectCode=subjectCodeInput;
		studentDni=studentDniInput;
		raId=raIdInput;
		subjectName=subjectNameInput;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(500,600);
		setLayout(new FlowLayout());
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
		
		JLabel labelLocation=new JLabel();
		labelLocation.setText("SUBJECT: "+subjectName+"  STUDENT: "+studentDni+"  RA: "+raName);
		add(labelLocation);
		
		
		JLabel labelMark=new JLabel();
		labelMark.setText("Mark:");
		add(labelMark);
		
		textMark=new JTextField();
		textMark.setEditable(true);
		textMark.setColumns(10);
		add(textMark);
		
		
		JButton buttonCancel=new JButton();
		buttonCancel.setText("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentFrame(subjectCode,studentDni,subjectName).setVisible(true);
				dispose();
			}
		});
		add(buttonCancel);
		
		JButton buttonAccept=new JButton();
		buttonAccept.setText("Accept");
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
		add(buttonAccept);
	}
}
