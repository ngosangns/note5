package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.MainController;
import main.Main;

public class ChangeInfoPanel extends JPanel {
	private JTextField password = new JPasswordField();
	
	public ChangeInfoPanel() {
		super();
		// Name panel
		JLabel usernameTitle = new JLabel("Tên đăng nhập");
	    JPanel usernamePanel = new JPanel();
	    usernamePanel.add(usernameTitle);
	    usernamePanel.add(new JLabel("ngosangns"));
	    usernamePanel.setLayout(new GridLayout(1, 2));
	    
	    // Name panel
 		JLabel nameTitle = new JLabel("Tên hiển thị");
 	    JPanel namePanel = new JPanel();
 	    namePanel.add(nameTitle);
 	    namePanel.add(new JTextField("Ngô Quang Sang"));
 	    namePanel.setLayout(new GridLayout(1, 2));
	    
	    // Password panel
	    JLabel passwordTitle = new JLabel("Mật khẩu mới");
	    JPanel passwordPanel = new JPanel();
	    passwordPanel.add(passwordTitle);
	    passwordPanel.add(password);
	    passwordPanel.setLayout(new GridLayout(1, 2));
	    
	    // Action panel
	    JButton submit = new JButton("Cập nhật");
		JPanel actionPanel = new JPanel();
		actionPanel.add(submit);
		
		// Add child panel to parent panel
		add(new JLabel("Cập nhật thông tin", JLabel.CENTER));
	    add(usernamePanel);
	    add(namePanel);
	    add(passwordPanel);
	    add(actionPanel);
	    
	    // Config parent panel
 		setBounds(
 			Main.frame.getPercentOfWidth(30), Main.frame.getPercentOfHeight(15),
 			Main.frame.getPercentOfWidth(40), Main.frame.getPercentOfHeight(31)
 		);
 		setLayout(new GridLayout(5, 1));
	}
	
}
