package views;
import main.Main;
import controllers.MainController;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	private JTextField username;
	private JPasswordField password;
	
	public LoginPanel() {
		super();
		Main.frame.setTitle("Đăng nhập");
		
		// Username panel
		JLabel usernameTitle = new JLabel("Tên đăng nhập");
	    username = new JTextField();
	    JPanel usernamePanel = new JPanel();
	    usernamePanel.add(usernameTitle);
	    usernamePanel.add(username);
	    usernamePanel.setLayout(new GridLayout(1, 3));
	    
	    // Password panel
	    JLabel passwordTitle = new JLabel("Mật khẩu");
	    password = new JPasswordField();
	    JPanel passwordPanel = new JPanel();
	    passwordPanel.add(passwordTitle);
	    passwordPanel.add(password);
	    passwordPanel.setLayout(new GridLayout(1, 3));
	    
	    // Action panel
	    JButton submit = new JButton("Đăng nhập");
	    JButton signUp = new JButton("Đăng ký");
		signUp.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		MainController.invoke("RegisterPanel");
	    	}  
    	});
		JPanel actionPanel = new JPanel();
		actionPanel.add(submit);
		actionPanel.add(signUp);
		
		// Add child panel to parent panel
		add(new JLabel("Đăng nhập", JLabel.CENTER));
	    add(usernamePanel);
	    add(passwordPanel);
	    add(actionPanel);
	    
	    // Config parent panel
		setBounds(
			Main.frame.getPercentOfWidth(30), Main.frame.getPercentOfHeight(15),
			Main.frame.getPercentOfWidth(40), Main.frame.getPercentOfHeight(25)
		);
		
		// Set grid 4 dòng 1 cột
		setLayout(new GridLayout(4, 1));
	}
}
