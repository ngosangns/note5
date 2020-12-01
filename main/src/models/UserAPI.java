package models;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.json.JSONException;

public class UserAPI extends API {
	/**
	 * Trả về thông tin user lấy từ server thông qua token
	 * @param token
	 * @return
	 */
	public static CompletableFuture<User> getLoggingUserInfoFromToken(String token) {
		return CompletableFuture.supplyAsync(() -> {
			User user = new User();
			user.token = token;
			
			try {
				sendGet("http://api.kaito.ninja/user", token)
					.thenAccept(res -> {
						try {
							Map<String, Object> mapUser = stringToMap(res);
							user.id = (String)mapUser.get("id");
							user.username = (String)mapUser.get("name");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return user;
		});
	}
	
	/**
	 * Đăng ký user trả về token
	 * @param user
	 * @return
	 */
	public static CompletableFuture<String> register(User user) {
		return CompletableFuture.supplyAsync(() -> {
			String resToken;
			try {
				sendPost("http://api.kaito.ninja/user", "username="+user.username+"&password="+user.password)
					.thenAccept(res -> {
						try {
							Map<String, Object> mapRes = stringToMap(res);
							resToken = (String)mapRes.get("token");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return resToken;
		});
	}
}
