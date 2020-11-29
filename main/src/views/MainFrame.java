package views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	public int width = 1000;
	public int height = 500;
	
	public MainFrame() {
		setSize(width, height);
		setResizable(false);
		setBackground(Color.white);
		setLayout(null);
		setVisible(true);
	}
	
	@Override
	public void setTitle(String title) {
		super.setTitle("Note5 - " + title);
	}
	
	public void visible() {
		setVisible(true);
	}
	
	public int getPercentOfWidth(int percent) {
		return (int)Math.round(width*percent/100);
	}
	
	public int getPercentOfHeight(int percent) {
		return (int)Math.round(height*percent/100);
	}
}