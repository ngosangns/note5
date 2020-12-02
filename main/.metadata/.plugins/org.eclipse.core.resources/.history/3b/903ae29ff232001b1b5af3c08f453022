package controllers;
import views.*;
import main.Main;

import javax.swing.JPanel;

public class MainController {
	// Current panel
	private static JPanel cPanel;
	
	public static void invoke(String pageName) {
		// Remove old panel
		if(cPanel != null)
			Main.frame.remove(cPanel);
		
		// Set new panel
		switch(pageName) {
			case "LoginPage": {
				cPanel = new LoginPanel();
				Main.frame.add(cPanel);
				break;
			}
			case "RegisterPage": {
				cPanel = new RegisterPanel();
				Main.frame.add(cPanel);
				break;
			}
		}
		
		// Reset
		Main.frame.revalidate();
		Main.frame.repaint();
	}
}
