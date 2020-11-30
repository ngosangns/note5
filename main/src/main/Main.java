package main;
import views.MainFrame;
public class Main {
	public static MainFrame frame;
	
	public static void main(String[] args) {
		frame = new MainFrame(); // Create main frame
		controllers.MainController.invoke("ChangeInfoPanel");
	}
}