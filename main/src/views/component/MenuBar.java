package views.component;
import controllers.MainController;
import models.api.UserAPI;
import views.context.menu.BoardContextMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu account = new JMenu("Tài khoản");
	private JMenuItem login = new JMenuItem("Đăng nhập");
	private JMenuItem register = new JMenuItem("Đăng ký");
	private JMenuItem changeInfo = new JMenuItem("Cập nhật thông tin");
	private JMenuItem logout = new JMenuItem("Đăng xuất");
	private JMenu board = new JMenu("Bảng");
	
	public MenuBar() {
		super();
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("LoginView");
			}
		});
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("RegisterView");
			}
		});
		
		changeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.invoke("ChangeInfoView");
			}
		});
		
		// Set hành động cho nút đăng xuất
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserAPI.checkLogin()) {
					UserAPI.logout();
					MainController.invoke("LoginView");
				}
			}
		});
		
		// Set hành động cho nút board
		board.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Set hanh dong khi click chuot trai
				if (SwingUtilities.isLeftMouseButton(e) ) {
					MainController.invoke("BoardView");
	            }
			}
		});
		
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
		
		// Add item cha vào menu bar
		add(account);
		add(board);
	}
}
