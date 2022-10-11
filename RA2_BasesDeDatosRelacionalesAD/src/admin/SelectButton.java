package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LoginAndRegister.Login;

import javax.swing.JButton;

public class SelectButton extends JFrame {

	private JPanel contentPane;
	private JButton btnStudents, btnTeachers, btnSubjects, btnRAs, btnExit, btnMatriculate;

	/**
	 * Create the frame.
	 */
	public SelectButton() {
		super("ADMIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		btnStudents = new JButton("Students");
		btnStudents.setBounds(81, 72, 120, 50);
		contentPane.add(btnStudents);
		
		btnTeachers = new JButton("Teachers");
		btnTeachers.setBounds(369, 72, 120, 50);
		contentPane.add(btnTeachers);
		
		btnSubjects = new JButton("Subjects");
		btnSubjects.setBounds(81, 201, 120, 50);
		contentPane.add(btnSubjects);
		
		btnRAs = new JButton("RA's");
		btnRAs.setBounds(369, 201, 120, 50);
		contentPane.add(btnRAs);
		
		btnMatriculate = new JButton("Enrollments");
		btnMatriculate.setBounds(81, 331, 120, 50);
		contentPane.add(btnMatriculate);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(369, 331, 120, 50);
		contentPane.add(btnExit);
		
		ManEvent mE = new ManEvent();
		btnStudents.addActionListener(mE);
		btnTeachers.addActionListener(mE);
		btnSubjects.addActionListener(mE);
		btnRAs.addActionListener(mE);
		btnMatriculate.addActionListener(mE);
		btnExit.addActionListener(mE);
		
		setVisible(true);
		
	}
	
	private class ManEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o == btnStudents) {
				Students frame = new Students();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if(o == btnTeachers) {
				Teachers frame = new Teachers();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if(o == btnSubjects) {
				Subjects frame = new Subjects();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if(o == btnRAs) {
				Ras frame = new Ras();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if(o == btnMatriculate) {
				Enrollments frame = new Enrollments();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			} else if(o == btnExit) {
				Login frame = null;
				try {
					frame = new Login();
					dispose();
				} catch (Exception ex) {}
			}
		}
		
	}
}
