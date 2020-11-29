package controllers;
import views.*;
import main.Main;
public class MainController {
	public static void invoke(String pageName) {
		if(Main.cPage != null) {
			Main.cPage.destroy();
		}
		switch(pageName) {
			case "LoginPage": {
				Main.cPage = new LoginPage();
				Main.cPage.visible();
				break;
			}
			case "RegisterPage": {
				Main.cPage = new RegisterPage();
				Main.cPage.visible();
				break;
			}
		}
	}
}
