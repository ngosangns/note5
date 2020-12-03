package views;
import models.BoardModel;
import models.api.BoardAPI;
import models.library.SwingLibrary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class BoardContextMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JMenuItem rename = new JMenuItem("Sửa");
	public JMenuItem delete = new JMenuItem("Xóa");
	
	public BoardContextMenu(List<BoardModel> boards, int index, DefaultListModel<BoardModel> boardListModel) {
		// Sua ten board
		rename.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JPanel panel = new JPanel();
		    	JTextField textField = new JTextField(boards.get(index).name);
		    	textField.setPreferredSize(new Dimension(250, 25));
		    	int option = JOptionPane.showConfirmDialog(null, textField, "Đổi tên bảng", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		    	String text = textField.getText();
		    	
		    	// Neu khong chon YES
		    	if(option != JOptionPane.YES_OPTION) return;
		    	
		    	// Kiem tra dau vao
		    	if(!(text.length() >= 0 && !text.equals(boards.get(index).name))) return;
		    	
		    	boards.get(index).name = text;
	    		boardListModel.get(index).name = text;
	    		
	    		// Cap nhat len server
	    		BoardAPI.update(boards.get(index)).thenAccept(res -> {
	    			// Hien popup neu xay ra loi
	    			if(!res.status) {
	    				SwingLibrary.alert(res.message);
	    			}
	    		});
		    }
		});
		
		// Xoa board
		delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int option = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa: "+boards.get(index).name+"?", "Xóa bảng", JOptionPane.YES_NO_OPTION);
		    	// Neu chon xoa
		    	if(option == JOptionPane.YES_OPTION) {
		    		// Cap nhat len server
		    		BoardAPI.delete(boards.get(index)).thenAccept(res -> {
		    			// Hien popup neu xay ra loi
		    			if(!res.status) {
		    				SwingLibrary.alert(res.message);
		    			}
		    		});
		    		boards.remove(index);
		    		boardListModel.remove(index);
		    	}
		    }
		});
		
		add(rename);
		add(delete);
	}
}
