package LoginAndRegister;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Login {

	private JFrame loginFrame;

	
	public Login() {
		initialize();
	}

	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.setResizable(false);
		loginFrame.setBounds(100, 100, 450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(45, 31, 100, 28);
		loginFrame.getContentPane().add(lblNewLabel);
		
	}
}
