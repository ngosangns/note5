package views;
import controllers.MainController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	JMenu account = new JMenu("Tài khoản");
		JMenuItem login = new JMenuItem("Đăng nhập");
		JMenuItem register = new JMenuItem("Đăng ký");
		JMenuItem changeInfo = new JMenuItem("Cập nhật thông tin");
	JMenu board = new JMenu("Bảng");
		JMenuItem boardItem = new JMenuItem("Bảng");
	
	public MenuBar() {
		super();
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("LoginView");
			}
		});
		login.setBackground(Color.white);
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("RegisterView");
			}
		});
		register.setBackground(Color.white);
		
		changeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("ChangeInfoView");
			}
		});
		changeInfo.setBackground(Color.white);
		
		boardItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("BoardView");
			}
		});
		boardItem.setBackground(Color.white);
		
		account.add(login);
		account.add(register);
		account.add(changeInfo);
		
		board.add(boardItem);
		
		add(account);
		add(board);
		setBackground(Color.white);
	}
}
