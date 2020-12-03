package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import models.NoteModel;
import models.api.NoteAPI;
import models.library.SwingLibrary;

public class NoteContextMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JMenuItem rename = new JMenuItem("Sửa");
	public JMenuItem delete = new JMenuItem("Xóa");
	
	public NoteContextMenu(List<NoteModel> notes, int index, DefaultListModel<NoteModel> noteListModel) {
		// Sua ten note
		rename.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JPanel panel = new JPanel();
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
		    	int option = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa: "+notes.get(index).name+"?", "Xóa bảng", JOptionPane.YES_NO_OPTION);
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
		
		add(rename);
		add(delete);
	}
}
