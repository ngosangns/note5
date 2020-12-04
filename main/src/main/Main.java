package main;
import java.io.File;
import java.io.IOException;

import controllers.MainController;
import models.LoggingUserModel;
import models.api.UserAPI;
import models.library.MainLibrary;
import models.library.SwingLibrary;
import views.component.MainFrame;

public class Main {
	public static MainFrame frame;
	public static LoggingUserModel logging_user;
	public static boolean enableSortBoardByColor;
	public static boolean enableSortNoteByColor;
	
	public static void main(String[] args) {
		// Dựng frame
		frame = new MainFrame();
		
		// Cài đặt nút sắp xếp theo màu (Settings)
		enableSortBoardByColor = false;
		enableSortNoteByColor = false;
		
		// Tạo mới logging user
		logging_user = new LoggingUserModel();
		
		// Tạo file chứa user token nếu chưa tồn tại
	    File tokenFile = new File("user_token");
	    try {
			tokenFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			SwingLibrary.alert("Không thể khởi tạo đăng nhập");
		}
	    
	    
	    // Đọc user token lấy token
	    logging_user.token = MainLibrary.readTokenFile();
	    
	    // Kiểm tra tồn tại token
		// Nếu có thì lấy thông tin user
		if(Main.logging_user.token.length() > 0) {
			UserAPI.read(Main.logging_user.token).thenAccept(res -> {
				// Nếu có user từ token đó thì direct đến trang board
				if(res.status) {
					Main.logging_user.username = (String) res.data.get("username");
					MainController.invoke("BoardView");
				}
				// Nếu không thì direct đến trang đăng nhập
				else {
					SwingLibrary.alert("Lỗi kết nối, vui lòng đăng nhập lại");
					controllers.MainController.invoke("LoginView");
				}
			});
		}
		// Nếu không tồn tại token thì direct đến trang đăng nhập
		else {
			controllers.MainController.invoke("LoginView");
		}
	}
}