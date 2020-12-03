package controllers;
import views.*;
import main.Main;
import models.api.UserAPI;
import models.library.MainLibrary;

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
				// Nếu đã đăng nhập thì direct về board
				// Nếu chưa thì tiếp tục
				if(UserAPI.checkLogin()) {
					directToBoard(); break;
				}
				cPanel = new LoginView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
			case "RegisterView": {
				// Nếu đã đăng nhập thì direct về board
				// Nếu chưa thì tiếp tục
				if(UserAPI.checkLogin()) {
					directToBoard(); break;
				}
				cPanel = new RegisterView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
			case "ChangeInfoView": {
				// Nếu đã đăng nhập thì tiếp tục
				// Nếu chưa thì direct về login
				if(!UserAPI.checkLogin()) {
					directToLogin(); break;
				}
				cPanel = new ChangeInfoView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
			case "BoardView": {
				// Nếu đã đăng nhập thì tiếp tục
				// Nếu chưa thì direct về login
				if(!UserAPI.checkLogin()) {
					directToLogin(); break;
				}
				cPanel = new BoardView();
				Main.frame.getContentPane().add(cPanel);
				break;
			}
		}
		
		// Reset
		Main.frame.revalidate();
		Main.frame.repaint();
	}
	
	private static void directToLogin() {
		cPanel = new LoginView();
		Main.frame.getContentPane().add(cPanel);
	}
	
	private static void directToBoard() {
		cPanel = new BoardView();
		Main.frame.getContentPane().add(cPanel);
	}
}
