package Test;

import java.awt.EventQueue;

import LoginAndRegister.Login;

public class test {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
