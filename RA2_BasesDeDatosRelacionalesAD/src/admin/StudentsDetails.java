package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Student;

import javax.swing.JButton;

public class StudentsDetails extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn;
	private Student studentSelected;


	/**
	 * Create the frame.
	 */
	public StudentsDetails(Student studentSelected) {
		super("DETAILS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		this.studentSelected = studentSelected;
		
	}	
	
	private class ManEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			
			  if(o == btnReturn) {
				  Students frame = new Students();
				  frame.setVisible(true);
				  frame.setLocationRelativeTo(null);
				  dispose();
			  }
			 
		}
		
	}
}
