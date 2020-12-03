package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import models.UserModel;
import models.api.UserAPI;
import models.library.SwingLibrary;
import net.miginfocom.swing.MigLayout;

public class ChangeInfoView extends JPanel {
	private JPasswordField password;
	private JPasswordField rePassword;
	public ChangeInfoView() {
		super();
		setLayout(new MigLayout("insets 0", "[grow][::250px,grow][grow]", "[grow][::300px,grow][grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 1,grow");
		panel.setLayout(new MigLayout("insets 0", "[grow]", "[][]"));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("", "[grow][grow]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		panel_1.add(lblNewLabel, "cell 0 0,alignx left");
		
		JLabel username = new JLabel(Main.logging_user.username);
		panel_1.add(username, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu mới");
		panel_1.add(lblNewLabel_1, "cell 0 1,alignx left");
		
		password = new JPasswordField();
		panel_1.add(password, "cell 1 1,growx");
		password.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nhập lại mật khẩu");
		panel_1.add(lblNewLabel_2, "cell 0 2,alignx left");
		
		rePassword = new JPasswordField();
		panel_1.add(rePassword, "cell 1 2,growx");
		rePassword.setColumns(10);
		
		JButton submit = new JButton("C\u1EADp nh\u1EADt m\u1EADt kh\u1EA9u");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passwordText = String.valueOf(password.getPassword());
				String rePasswordText = String.valueOf(rePassword.getPassword());
				UserModel user = new UserModel();
				user.username = username.getText();
	    		user.password = String.valueOf(password.getPassword());
	    		
	    		// Kiểm tra đầu vào trống
	    		if(passwordText.length() == 0 || rePasswordText.length() == 0) {
	    			SwingLibrary.alert("Vui lòng nhập đầy đủ các trường");
	    			return;
	    		}
	    		
	    		// Kiểm tra đầu vào hợp lệ
	    		if(!passwordText.matches("[0-9a-zA-Z._]+") || !rePasswordText.matches("[0-9a-zA-Z._]+")) {
	    			SwingLibrary.alert("Trường chứa kí tự không hợp lệ");
	    			return;
	    		}
	    		
	    		// Kiểm tra 2 mật khẩu trùng nhau
	    		if(!passwordText.equals(rePasswordText)) {
	    			SwingLibrary.alert("Mật khẩu không trùng khớp");
	    			return;
	    		}
	    		
	    		UserAPI.update(user).thenAccept(res -> {
	    			if(res.status) {
	    				SwingLibrary.alert("Cập nhật mật khẩu thành công");
	    				password.setText(null);
	    				rePassword.setText(null);
	    			}
	    			else {
	    				SwingLibrary.alert(res.message);
	    			}
	    		});
			}
		});
		panel.add(submit, "cell 0 1,alignx center");
		Main.frame.setTitle("Cập nhật mật khẩu");
		
		
	}
}
