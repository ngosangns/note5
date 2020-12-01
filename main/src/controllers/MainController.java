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
			case "LoginView": {
				cPanel = new LoginView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
			case "RegisterView": {
				// Kiểm tra tồn tại token
				// Nếu có thì lấy thông tin user
				if(Main.logging_user.token.length() > 0) {
					
				}
				cPanel = new RegisterView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
			case "ChangeInfoView": {
				cPanel = new ChangeInfoView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
			case "BoardView": {
				cPanel = new BoardView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
		}
		
		// Reset
		Main.frame.revalidate();
		Main.frame.repaint();
	}
}
