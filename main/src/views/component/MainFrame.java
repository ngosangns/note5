package views.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setSize(800, 400);
		setMinimumSize(new Dimension(600, 300));
		setBackground(Color.white);
		setJMenuBar(new MenuBar()); // Add MenuBar
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
}