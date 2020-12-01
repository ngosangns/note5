package views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	public int width = 1000;
	public int height = 500;
	
	public MainFrame() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setSize(width, height);
		setResizable(false); // Chặn thay đổi kích thước frame
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
	
	/**
	 * Lấy chiều dài của frame dựa vào % đưa vào
	 * @param percent
	 * @return
	 */
	public int getPercentOfWidth(double percent) {
		return (int)Math.round(width*percent/100);
	}
	
	/**
	 * Lấy chiều rộng của frame dựa vào % đưa vào
	 * @param percent
	 * @return
	 */
	public int getPercentOfHeight(double percent) {
		return (int)Math.round(height*percent/100);
	}
}