package views;
import javax.swing.JFrame;
import controllers.MainController;

public class Page {
	JFrame f;
	
	public Page() {
		f = new JFrame(); // Tạo thể hiện của JFrame
		f.setSize(600, 400);
	}
	
	public void visible() {
		f.setVisible(true);
	}
	
	public void invoke(String pageName) {
		MainController.invoke(pageName);
	}
	
	public void destroy() {
		f.dispose();
	}
}