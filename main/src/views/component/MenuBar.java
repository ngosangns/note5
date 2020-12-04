package views.component;
import controllers.MainController;
import models.api.UserAPI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu account = new JMenu("Tài khoản");
		JMenuItem login = new JMenuItem("Đăng nhập");
		JMenuItem register = new JMenuItem("Đăng ký");
		JMenuItem changeInfo = new JMenuItem("Cập nhật thông tin");
		JMenuItem logout = new JMenuItem("Đăng xuất");
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
		
		// Set hành động cho nút đăng xuất
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserAPI.checkLogin()) {
					UserAPI.logout();
					MainController.invoke("LoginView");
				}
			}
		});
		logout.setBackground(Color.white);
		
		// Set hành động cho nút board
		boardItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("BoardView");
			}
		});
		boardItem.setBackground(Color.white);
		
		// Set hành động cho nút Account
		account.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				boolean isLogin = UserAPI.checkLogin();
				login.setVisible(!isLogin);
				register.setVisible(!isLogin);
				changeInfo.setVisible(isLogin);
				logout.setVisible(isLogin);
			}
			@Override
			public void menuDeselected(MenuEvent e) { }
			@Override
			public void menuCanceled(MenuEvent e) { }
		});
		
		// Add item con vào item cha
		account.add(login);
		account.add(register);
		account.add(changeInfo);
		account.add(logout);
		board.add(boardItem);
		
		// Add item cha vào menu bar
		add(account);
		add(board);
		
		setBackground(Color.white);
	}
}
