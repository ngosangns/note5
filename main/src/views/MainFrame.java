package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	public MainFrame() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setSize(500, 300);
		setMinimumSize(new Dimension(500, 300));
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