package controllers;
import views.*;
import main.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JPanel;

public class MainController {
	// Current panel
	private static JPanel cPanel;
	
	public static void invoke(String pageName) {
		// Tạo folder data nếu không tồn tại
		File dataDir = new File("data");
	    if (!dataDir.exists()){
	    	dataDir.mkdir();
	    }
	    
	    // Tạo file chứa user token nếu chưa tồn tại
	    File tokenFile = new File("user_token");
	    try {
			tokenFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		} // If file already exists will do nothing
//	    FileOutputStream oFile = new FileOutputStream(tokenFile, false);
	    
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
