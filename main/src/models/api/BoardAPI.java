package models.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;

import main.Main;
import models.ResponseModel;
import models.BoardModel;
import models.library.NoteLibrary;

public class BoardAPI {
	/**
	 * Lấy tất cả board từ user
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...,
	 * 		data: {
	 * 			"0": {...}
	 * 		}
	 * }
	 */
	public static CompletableFuture<ResponseModel> read() {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request lấy boards
				ResponseModel ress = NoteLibrary.sendGet("http://api.kaito.ninja/boards", Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject((String) ress.data.get("response"));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
						res.data = new HashMap<String, Object>();
						res.data.put("boards", resJSON);
					}
					// Nếu tồn tại thông báo lỗi
					else {
						res.status = false;
						res.message = resJSON.getString("message");
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
}
