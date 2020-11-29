package main;
import views.Page;
import controllers.MainController;

public class Main {
	public static Page cPage;
	public static void main(String[] args) {
		MainController.invoke("LoginPage");
	}
}