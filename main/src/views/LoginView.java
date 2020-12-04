package views;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import main.Main;
import models.LoggingUserModel;
import models.UserModel;
import models.api.UserAPI;
import models.library.MainLibrary;
import models.library.SwingLibrary;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import controllers.MainController;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField username;
	private JPasswordField password;
	public LoginView() {
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
		
		username = new JTextField();
		panel_1.add(username, "cell 1 0,growx");
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
		panel_1.add(lblNewLabel_1, "cell 0 1,alignx left");
		
		password = new JPasswordField();
		panel_1.add(password, "cell 1 1,growx");
		password.setColumns(10);
		
		JButton submit = new JButton("Đăng nhập");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passwordText = String.valueOf(password.getPassword());
				UserModel user = new UserModel();
				user.username = username.getText();
	    		user.password = String.valueOf(password.getPassword());
	    		
	    		// Kiểm tra đầu vào trống
	    		if(user.username.length() == 0 || passwordText.length() == 0) {
	    			SwingLibrary.alert("Vui lòng nhập đầy đủ các trường");
	    			return;
	    		}
	    		
	    		// Kiểm tra đầu vào hợp lệ
	    		if(!user.username.matches("[0-9a-zA-Z._]+") || !passwordText.matches("[0-9a-zA-Z._]+")) {
	    			SwingLibrary.alert("Trường chứa kí tự không hợp lệ");
	    			return;
	    		}
	    		
	    		UserAPI.login(user).thenAccept(res -> {
	    			// Nếu đăng nhập thành công
	    			if(res.status) {
	    				// Thêm thông tin vào logging user (đăng nhập)
	    				Main.logging_user = new LoggingUserModel();
	    				Main.logging_user.username = user.username;
	    				Main.logging_user.token = (String) res.data.get("token");
	    				// Ghi token vào user_token
	    				MainLibrary.writeTokenFile((String) res.data.get("token"));
	    				// Direct đến trang board
	    				MainController.invoke("BoardView");
	    			}
	    			else {
	    				SwingLibrary.alert(res.message);
	    			}
	    		});
			}
		});
		panel.add(submit, "cell 0 1,alignx center");
		Main.frame.setTitle("Đăng nhập");
		
		
	}
}
