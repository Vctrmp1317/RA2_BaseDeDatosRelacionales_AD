package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LoginAndRegister.Login;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;

public class AdminMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnStudents, btnTeachers, btnSubjects, btnRAs, btnExit, btnMatriculate;

	/**
	 * Create the frame.
	 */
	public AdminMenu() {
		super("ADMIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		ImageIcon imageIcon = new ImageIcon("icons/icon.png");
		Image image = imageIcon.getImage();
		setIconImage(image);
		
		btnStudents = new JButton();
		btnStudents.setFocusPainted(false);
		btnStudents.setContentAreaFilled(false);
		btnStudents.setBorderPainted(false);
		btnStudents.setToolTipText("Students");
		btnStudents.setBackground(Color.DARK_GRAY);
		btnStudents.setBounds(81, 72, 65, 65);
		ImageIcon studentIcon=new ImageIcon("icons/student.png");
		btnStudents.setIcon(studentIcon);
		contentPane.add(btnStudents);
		
		btnTeachers = new JButton();
		btnTeachers.setFocusPainted(false);
		btnTeachers.setContentAreaFilled(false);
		btnTeachers.setBorderPainted(false);
		btnTeachers.setToolTipText("Teachers");
		btnTeachers.setBackground(Color.DARK_GRAY);
		btnTeachers.setBounds(369, 72, 65, 65);
		ImageIcon teacherIcon=new ImageIcon("icons/teacher.png");
		btnTeachers.setIcon(teacherIcon);
		contentPane.add(btnTeachers);
		
		btnSubjects = new JButton();
		btnSubjects.setFocusPainted(false);
		btnSubjects.setContentAreaFilled(false);
		btnSubjects.setBorderPainted(false);
		btnSubjects.setToolTipText("Subjects");
		btnSubjects.setBackground(Color.DARK_GRAY);
		btnSubjects.setBounds(81, 201, 65, 65);
		ImageIcon subjectIcon=new ImageIcon("icons/subjects.png");
		btnSubjects.setIcon(subjectIcon);
		contentPane.add(btnSubjects);
		
		btnRAs = new JButton();
		btnRAs.setFocusPainted(false);
		btnRAs.setContentAreaFilled(false);
		btnRAs.setBorderPainted(false);
		btnRAs.setToolTipText("RAs");
		btnRAs.setBackground(Color.DARK_GRAY);
		btnRAs.setBounds(369, 201, 65, 65);
		ImageIcon raIcon=new ImageIcon("icons/ras.png");
		btnRAs.setIcon(raIcon);
		contentPane.add(btnRAs);
		
		btnMatriculate = new JButton();
		btnMatriculate.setFocusPainted(false);
		btnMatriculate.setContentAreaFilled(false);
		btnMatriculate.setBorderPainted(false);
		btnMatriculate.setToolTipText("Enrollment");
		btnMatriculate.setBackground(Color.DARK_GRAY);
		btnMatriculate.setBounds(81, 331, 65, 65);
		ImageIcon enrollmentIcon=new ImageIcon("icons/enrollment.png");
		btnMatriculate.setIcon(enrollmentIcon);
		contentPane.add(btnMatriculate);
		
		btnExit = new JButton();
		btnExit.setFocusPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setBorderPainted(false);
		btnExit.setToolTipText("Exit");
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBounds(369, 331, 65, 65);
		ImageIcon exitIcon=new ImageIcon("icons/exitx64.png");
		btnExit.setIcon(exitIcon);
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
