package models.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.json.JSONException;

import controllers.MainController;
import main.Main;
import models.ResponseModel;
import models.UserModel;
import models.library.NoteLibrary;
import models.library.SwingLibrary;

public class UserAPI {
	/**
	 * Đăng ký user trả về token.
	 * @param user
	 * @return ResponseModel res = {
	 * 		status: true,
	 * 		data: {
	 * 			token: string
	 * 		}
	 * }
	 */
	public static CompletableFuture<ResponseModel> create(UserModel user) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request tạo user và nhận lại response
				ResponseModel ress = NoteLibrary.sendPost("http://api.kaito.ninja/user",
							"username="+user.username+"&password="+user.password).get();
				
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					// Chuyển json trả về thành map
					Map<String, Object> mapRess = NoteLibrary.stringToMap((String) ress.data.get("response"));
					// Nếu tồn tại token thì trả về
					if(mapRess.containsKey("token")) {
						res.status = true;
						res.data = mapRess;
					}
					// Nếu tồn tại thông báo lỗi
					else if(mapRess.containsKey("message")) {
						res.status = false;
						res.message = (String) mapRess.get("message");
					}
					// Trả về thông báo mặc định nếu là lỗi khác
					else {
						res.status = false;
						res.message = "Tạo tài khoản không thành công";
					}
				}
				else {
					res.status = false;
					res.message = ress.message;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.status = false;
				res.message = "Có lỗi xảy ra";
			}
			return res;
		});
	}
	
	/**
	 * Trả về thông tin user lấy từ server thông qua token.
	 * @param token
	 * @return ResponseModel res = {
	 * 		status: true,
	 * 		data: {
	 * 			username: string,
	 * 			password: string
	 * 		}
	 * }
	 */
	public static CompletableFuture<ResponseModel> read(String token) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request tạo user và nhận lại response
				ResponseModel ress = NoteLibrary.sendGet("http://api.kaito.ninja/user", token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					// Chuyển json trả về thành map
					Map<String, Object> mapRess = NoteLibrary.stringToMap((String) ress.data.get("response"));
					// Nếu tồn tại username thì trả về
					if(mapRess.containsKey("username")) {
						res.status = true;
						res.data = mapRess;
					}
					// Nếu tồn tại thông báo lỗi
					else if(mapRess.containsKey("message")) {
						res.status = false;
						res.message = (String) mapRess.get("message");
					}
					// Nếu tồn tại lỗi khác
					else {
						res.status = false;
						res.message = "Lấy dữ liệu không thành công";
					}
				}
				else {
					res.status = false;
					res.message = "Có lỗi xảy ra";
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.status = false;
				res.message = "Có lỗi xảy ra";
			}
			return res;
		});
	}
	/**
	 * Trả về token thông qua user.
	 * @param user
	 * @return ResponseModel res = {
	 * 		status: true,
	 * 		data: {
	 * 			username: string,
	 * 			password: string
	 * 		}
	 * }
	 */
	public static CompletableFuture<ResponseModel> login(UserModel user) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request tạo user và nhận lại response
				ResponseModel ress = NoteLibrary.sendPost("http://api.kaito.ninja/login", "username="+user.username+"&password="+user.password).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					// Chuyển json trả về thành map
					Map<String, Object> mapRess = NoteLibrary.stringToMap((String) ress.data.get("response"));
					// Nếu tồn tại token thì trả về
					if(mapRess.containsKey("token")) {
						res.status = true;
						res.data = mapRess;
					}
					// Nếu tồn tại thông báo lỗi
					else if(mapRess.containsKey("Error")) {
						res.status = false;
						res.message = (String) mapRess.get("Error");
					}
					// Nếu tồn tại lỗi khác
					else {
						res.status = false;
						res.message = "Đăng nhập không thành công";
					}
				}
				else {
					res.status = false;
					res.message = "Có lỗi xảy ra";
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.status = false;
				res.message = "Có lỗi xảy ra";
			}
			return res;
		});
	}
	
	public static void logout() {
		Main.logging_user.token = "";
		if(!NoteLibrary.writeTokenFile("")) {
			SwingLibrary.alert("Có lỗi xảy ra");
		}
	}
	
	public static boolean checkLogin() {
		if(Main.logging_user.token != null && Main.logging_user.token.length() > 0) {
			if(Main.logging_user.username != null) {
				return true;
			}
		}
		return false;
	}
}
