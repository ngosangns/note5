package models;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.json.JSONException;

public class UserAPI extends API implements ICRUD {
	@Override
	public void create() {}
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
	public CompletableFuture<ResponseModel> create(UserModel user) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request tạo user và nhận lại response
				ResponseModel ress = sendPost("http://api.kaito.ninja/user",
							"username="+user.username+"&password="+user.password).get();
				
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					// Chuyển json trả về thành map
					Map<String, Object> mapRess = stringToMap(ress.message);
					// Nếu tồn tại token thì trả về
					if(mapRess.containsKey("token")) {
						res.status = true;
						res.data = mapRess;
					}
					// Nếu không tồn tại token thì trả về lỗi
					else {
						res.status = false;
						res.message = "Tạo tài khoản không thành công";
					}
				}
				else {
					res.status = false;
					res.message = "Có lỗi xảy ra khi tạo tài khoản";
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.status = false;
				res.message = "Có lỗi xảy ra khi tạo tài khoản";
			}
			return res;
		});
	}
	
	@Override
	public void read() {}
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
	public CompletableFuture<ResponseModel> read(String token) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request tạo user và nhận lại response
				ResponseModel ress = sendGet("http://api.kaito.ninja/user", token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					// Chuyển json trả về thành map
					Map<String, Object> mapRess = stringToMap(ress.message);
					// Nếu tồn tại username thì trả về
					if(mapRess.containsKey("username")) {
						res.status = true;
						res.data = mapRess;
					}
					// Nếu không tồn tại username thì trả về lỗi
					else {
						res.status = false;
						res.message = "Lấy dữ liệu không thành công";
					}
				}
				else {
					res.status = false;
					res.message = "Có lỗi xảy ra khi lấy dữ liệu";
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.status = false;
				res.message = "Có lỗi xảy ra khi lấy dữ liệu";
			}
			return res;
		});
	}
	
	@Override
	public void update() { }
	
	@Override
	public void delete() { }
}
