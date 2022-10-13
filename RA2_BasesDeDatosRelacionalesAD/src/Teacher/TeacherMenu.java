package Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TeacherMenu {
	private JFrame menuFrame;
	
	public TeacherMenu()
	{
		initialize();
	}
	
	
	private void initialize() {
		menuFrame = new JFrame();
		menuFrame.setResizable(false);
		menuFrame.setBounds(100, 100, 328, 300);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		{
			JButton button=new JButton();
			button.setText("Button");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					{
						
						menuFrame.dispose();
					}
				}
			});
			menuFrame.add(button);
		}
		
		menuFrame.setVisible(true);
	}
	
}
