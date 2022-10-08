package Student;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

public class StudentMenu {

	private JFrame menuFrame;
	private JMenuBar mb;
	private JMenu menu1, menu2, menu3;
	private JMenuItem mi1, mi2, mi3, mi4;

	public StudentMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		menuFrame = new JFrame();
		menuFrame.setBounds(100, 100, 328, 300);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManMenu mm = new ManMenu();
		mb = new JMenuBar();
		menuFrame.setJMenuBar(mb);
		menu1 = new JMenu("Profile");
		mb.add(menu1);
		menu2 = new JMenu("Details");
		menu1.add(menu2);
		menu3 = new JMenu("Connection Options");
		menu1.add(menu3);

		mi1 = new JMenuItem("All Details");
		menu2.add(mi1);
		mi1.addActionListener(mm);

		mi2 = new JMenuItem("Subjects");
		menu2.add(mi2);
		mi2.addActionListener(mm);

		mi3 = new JMenuItem("Marks");
		menu2.add(mi3);
		mi3.addActionListener(mm);

		mi4 = new JMenuItem("Disconnect");
		menu3.add(mi4);
		menuFrame.getContentPane().setLayout(null);
		mi4.addActionListener(mm);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(213, 205, 89, 23);
		menuFrame.getContentPane().add(btnClose);
		

		menuFrame.setVisible(true);
	}

	private class ManMenu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o == mi1) {
				//Class to details
			} else if (o == mi2) {
				//Class to subjects
			} else if (o == mi3) {
				//Class to marks
			}

		}

	}
}
