package views;
import models.BoardModel;
import models.NoteModel;
import models.api.BoardAPI;
import models.library.SwingLibrary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class BoardContextMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem rename = new JMenuItem("Đổi tên bảng");
	private JMenuItem delete = new JMenuItem("Xóa");
	private JMenuItem bounder = new JMenuItem();
	private JMenuItem setRed = new JMenuItem("Đặt màu đỏ", new ImageIcon("color/red.png"));
	private JMenuItem setOrange = new JMenuItem("Đặt màu cam", new ImageIcon("color/orange.png"));
	private JMenuItem setYellow = new JMenuItem("Đặt màu vàng", new ImageIcon("color/yellow.png"));
	private JMenuItem setGreen = new JMenuItem("Đặt màu xanh lá", new ImageIcon("color/green.png"));
	private JMenuItem setBlue = new JMenuItem("Đặt màu xanh dương", new ImageIcon("color/blue.png"));
	private JMenuItem setTransparent = new JMenuItem("Đặt lại màu");
	
	public BoardContextMenu(
			List<BoardModel> boards,
			int index,
			BoardModel cboard,
			DefaultListModel<NoteModel> noteListModel,
			DefaultListModel<BoardModel> boardListModel
		) {
		// Sua ten board
		rename.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
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
		    		// Neu xoa bang hien tai thi clear note dang hien thi
		    		if(cboard == boards.get(index))
		    			noteListModel.clear();
		    		// Xoa bang trong list
		    		boards.remove(index);
		    		boardListModel.remove(index);
		    	}
		    }
		});
		
		setRed.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(boards, index, boardListModel, "red");
		    }
		});
		
		setOrange.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(boards, index, boardListModel, "orange");
		    }
		});
		
		setYellow.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(boards, index, boardListModel, "yellow");
		    }
		});
		
		setGreen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(boards, index, boardListModel, "green");
		    }
		});
		
		setBlue.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(boards, index, boardListModel, "blue");
		    }
		});
		
		setTransparent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(boards, index, boardListModel, null);
		    }
		});
		
		add(rename);
		add(delete);
		bounder.setEnabled(false);
		add(bounder);
		add(setRed);
		add(setOrange);
		add(setYellow);
		add(setGreen);
		add(setBlue);
		add(setTransparent);
	}
	
	// Thay doi mau cho board
	private void updateColor(
			List<BoardModel> boards,
			int index,
			DefaultListModel<BoardModel> boardListModel,
			String color
		) {
		boards.get(index).color = color;
		boardListModel.get(index).color = color;
		
		// Cap nhat len server
		BoardAPI.updateColor(boards.get(index)).thenAccept(res -> {
			// Hien popup neu xay ra loi
			if(!res.status) {
				SwingLibrary.alert(res.message);
			}
		});
	}
}
