package views.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuBar menuBar;
	public MainFrame() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setSize(800, 400);
		setMinimumSize(new Dimension(800, 400));
		setBackground(Color.white);
		menuBar = new MenuBar();
		getContentPane().setBackground(Color.white); // Set background color
		setVisible(true); // Hiển thị frame
	}
	
	/**
	 * Set tiêu đề cho frame
	 */
	@Override
	public void setTitle(String title) {
		super.setTitle("Note5 - " + title);
	}
	
	/**
	 * Hiển thị frame
	 */
	public void visible() {
		setVisible(true);
	}
	
	public void disableMenuBar() {
		setJMenuBar(null);
	}
	
	public void enableMenuBar() {
		setJMenuBar(menuBar);
	}
	
}