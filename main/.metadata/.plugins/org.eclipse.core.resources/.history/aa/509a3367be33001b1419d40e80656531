package views;
import controllers.MainController;
import main.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.Map;

public class RegisterPanel extends JPanel {
	private JTextField username;
	private JPasswordField password;
	private JPasswordField rePassword;
	
	public RegisterPanel() {
		super();
		Main.frame.setTitle("Đăng ký");
		Main.frame.setBackground(Color.white);
		
		// Username panel
		JLabel usernameTitle = new JLabel("Tên đăng nhập");
	    username = new JTextField();
	    JPanel usernamePanel = new JPanel();
	    usernamePanel.add(usernameTitle);
	    usernamePanel.add(username);
	    usernamePanel.setLayout(new GridLayout(1, 2));
	    usernamePanel.setBackground(Color.white);
	    
	    // Password panel
	    JLabel passwordTitle = new JLabel("Mật khẩu");
	    password = new JPasswordField();
	    JPanel passwordPanel = new JPanel();
	    passwordPanel.add(passwordTitle);
	    passwordPanel.add(password);
	    passwordPanel.setLayout(new GridLayout(1, 2));
	    passwordPanel.setBackground(Color.white);
	    
	    // Repassword panel
	    JLabel rePasswordTitle = new JLabel("Nhập lại mật khẩu");
	    rePassword = new JPasswordField();
	    JPanel rePasswordPanel = new JPanel();
	    rePasswordPanel.add(rePasswordTitle);
	    rePasswordPanel.add(rePassword);
	    rePasswordPanel.setLayout(new GridLayout(1, 2));
	    rePasswordPanel.setBackground(Color.white);
	    
	    // Action panel
	    JButton submit = new JButton("Đăng ký");
		JLabel login = new JLabel("Đăng nhập");
		login.setForeground(Color.BLUE.darker());
		// Set hành động của nút đăng nhập
		login.addMouseListener(new MouseAdapter() {
			// Click vào thì điều hướng đến trang đăng nhập
			public void mouseClicked(MouseEvent e) {
				MainController.invoke("LoginPanel");
		    }
			// Di chuột vào sẽ hiện gạch chân
			public void mouseEntered(MouseEvent e) {
				Map attributes = login.getFont().getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				login.setFont(login.getFont().deriveFont(attributes));
		    }
			// Ẩn gạch chân khi di chuột ra ngoài
		    public void mouseExited(MouseEvent e) {
		    	Map attributes = login.getFont().getAttributes();
				attributes.put(TextAttribute.UNDERLINE, -1);
				login.setFont(login.getFont().deriveFont(attributes));
		    }
		});
		JPanel actionPanel = new JPanel();
		actionPanel.add(submit);
		actionPanel.add(login);
		actionPanel.setBackground(Color.white);
		    
		// Add child panel to parent panel
		JLabel title = new JLabel("Đăng ký", JLabel.CENTER);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 18));
		add(title);
	    add(usernamePanel);
	    add(passwordPanel);
	    add(rePasswordPanel);
	    add(actionPanel);
	    
	    // Config parent panel
		setBounds(
			Main.frame.getPercentOfWidth(30), Main.frame.getPercentOfHeight(15),
			Main.frame.getPercentOfWidth(40), Main.frame.getPercentOfHeight(31)
		);
		setBackground(Color.white);
		setLayout(new GridLayout(5, 1));
	}
}
