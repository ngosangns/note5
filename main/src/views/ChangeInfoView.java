package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import models.User;
import net.miginfocom.swing.MigLayout;

public class ChangeInfoView extends JPanel {
	private JPasswordField password;
	public ChangeInfoView() {
		super();
		setLayout(new MigLayout("insets 0", "[grow][::250px,grow][grow]", "[grow][::300px,grow][grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 1,grow");
		panel.setLayout(new MigLayout("insets 0", "[grow]", "[][]"));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		panel_1.add(lblNewLabel, "cell 0 0,alignx left");
		
		JLabel username = new JLabel("Username");
		panel_1.add(username, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
		panel_1.add(lblNewLabel_1, "cell 0 1,alignx left");
		
		password = new JPasswordField();
		panel_1.add(password, "cell 1 1,growx");
		password.setColumns(10);
		
		JButton submit = new JButton("C\u1EADp nh\u1EADt m\u1EADt kh\u1EA9u");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passwordText = String.valueOf(password.getPassword());
				String rePasswordText = String.valueOf(password.getPassword());
				User user = new User();
	    		user.username = username.getText();
	    		user.password = String.valueOf(password.getPassword());
			}
		});
		panel.add(submit, "cell 0 1,alignx center");
		Main.frame.setTitle("Cập nhật mật khẩu");
		
		
	}
}
