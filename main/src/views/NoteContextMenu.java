package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import models.BoardModel;
import models.NoteModel;
import models.api.BoardAPI;
import models.api.NoteAPI;
import models.library.SwingLibrary;

public class NoteContextMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JMenuItem rename = new JMenuItem("Sửa");
	public JMenuItem delete = new JMenuItem("Xóa");
	
	private JMenuItem bounder = new JMenuItem();
	private JMenuItem setRed = new JMenuItem("Đặt màu đỏ", new ImageIcon("color/red.png"));
	private JMenuItem setOrange = new JMenuItem("Đặt màu cam", new ImageIcon("color/orange.png"));
	private JMenuItem setYellow = new JMenuItem("Đặt màu vàng", new ImageIcon("color/yellow.png"));
	private JMenuItem setGreen = new JMenuItem("Đặt màu xanh lá", new ImageIcon("color/green.png"));
	private JMenuItem setBlue = new JMenuItem("Đặt màu xanh dương", new ImageIcon("color/blue.png"));
	private JMenuItem setTransparent = new JMenuItem("Đặt lại màu");
	
	public NoteContextMenu(List<NoteModel> notes, int index, DefaultListModel<NoteModel> noteListModel) {
		// Sua ten note
		rename.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JTextField textField = new JTextField(notes.get(index).name);
		    	textField.setPreferredSize(new Dimension(250, 25));
		    	int option = JOptionPane.showConfirmDialog(null, textField, "Cập nhật ghi chú", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		    	String text = textField.getText();
		    	
		    	// Neu khong chon YES
		    	if(option != JOptionPane.YES_OPTION) return;
		    	
		    	// Kiem tra dau vao
		    	if(!(text.length() >= 0 && !text.equals(notes.get(index).name))) return;
		    	
		    	notes.get(index).name = text;
		    	noteListModel.get(index).name = text;
	    		// Cap nhat len server
	    		NoteAPI.update(notes.get(index)).thenAccept(res -> {
	    			// Hien popup neu xay ra loi
	    			if(!res.status) {
	    				SwingLibrary.alert(res.message);
	    			}
	    		});
		    }
		});
		
		// Xoa note
		delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JLabel label = new JLabel("Bạn muốn xóa: "+notes.get(index).name+"?");
		    	label.setPreferredSize(new Dimension(300, 25));
		    	int option = JOptionPane.showConfirmDialog(null, label, "Xóa bảng", JOptionPane.YES_NO_OPTION);
		    	// Neu chon xoa
		    	if(option == JOptionPane.YES_OPTION) {
		    		// Cap nhat len server
		    		NoteAPI.delete(notes.get(index)).thenAccept(res -> {
		    			// Hien popup neu xay ra loi
		    			if(!res.status) {
		    				SwingLibrary.alert(res.message);
		    			}
		    		});
		    		notes.remove(index);
		    		noteListModel.remove(index);
		    	}
		    }
		});
		
		setRed.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(notes, index, noteListModel, "red");
		    }
		});
		
		setOrange.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(notes, index, noteListModel, "orange");
		    }
		});
		
		setYellow.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(notes, index, noteListModel, "yellow");
		    }
		});
		
		setGreen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(notes, index, noteListModel, "green");
		    }
		});
		
		setBlue.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(notes, index, noteListModel, "blue");
		    }
		});
		
		setTransparent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	updateColor(notes, index, noteListModel, null);
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
			List<NoteModel> notes,
			int index,
			DefaultListModel<NoteModel> boardListModel,
			String color
		) {
		notes.get(index).color = color;
		boardListModel.get(index).color = color;
		
		// Cap nhat len server
		NoteAPI.updateColor(notes.get(index)).thenAccept(res -> {
			// Hien popup neu xay ra loi
			if(!res.status) {
				SwingLibrary.alert(res.message);
			}
		});
	}
}
