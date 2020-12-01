package views;
import models.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class BoardContextMenu extends JPopupMenu {
	public JMenuItem rename = new JMenuItem("Sửa");
	public JMenuItem delete = new JMenuItem("Xóa");
	private Board board;
	
	private String temp_res;
	
	BoardContextMenu(Board inBoard) {
		board = inBoard;
		rename.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String res = JOptionPane.showInputDialog("", board.name);
		    }
		});
		
		rename.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int res = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa: "+board.name+"?");
		    }
		});
		
		add(rename);
		add(delete);
	}
	
	
}