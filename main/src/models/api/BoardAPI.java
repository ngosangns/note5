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
import models.library.MainLibrary;

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
				ResponseModel ress = MainLibrary.sendGet("http://api.kaito.ninja/boards", Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject(String.valueOf(ress.data.get("response")));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
						res.data = new HashMap<String, Object>();
						List<BoardModel> boards = new ArrayList<BoardModel>();
						Iterator<String> resJSONkeys = resJSON.keys();
						try {
							while(resJSONkeys.hasNext()) {
							    String key = resJSONkeys.next();
						    	JSONObject ijsonObject = (JSONObject) resJSON.get(key);
						    	BoardModel iboard = new BoardModel(
						    			String.valueOf(ijsonObject.get("ID")),
						    			String.valueOf(ijsonObject.get("NAME")),
						    			String.valueOf(ijsonObject.get("created_at")),
						    			String.valueOf(ijsonObject.get("USER_ID")),
						    			String.valueOf(ijsonObject.get("COLOR")));
						    	boards.add(iboard);
							}
						}
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						res.data.put("boards", boards);
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
	
	/**
	 * Tạo board
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...,
	 * 		data: {
	 * 			"id": string,
	 * 			"date_created": string,
	 * 			"user_id": string
	 * 		}
	 * }
	 */
	public static CompletableFuture<ResponseModel> create(BoardModel board) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request lấy boards
				ResponseModel ress = MainLibrary.sendPost("http://api.kaito.ninja/boards", "name="+board.name,Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject(String.valueOf(ress.data.get("response")));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
						res.data = new HashMap<String, Object>();
						res.data.put("id", resJSON.get("ID"));
						res.data.put("date_created", resJSON.get("created_at"));
						res.data.put("user_id", resJSON.get("USER_ID"));
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
	
	/**
	 * Cap nhat board
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...,
	 * 		data: {}
	 * }
	 */
	public static CompletableFuture<ResponseModel> update(BoardModel board) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request cap nhat board
				ResponseModel ress = MainLibrary.sendPost("http://api.kaito.ninja/board/"+board.id+"/put", "name="+board.name, Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject(String.valueOf(ress.data.get("response")));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
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
	
	/**
	 * Cap nhat mau board
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...,
	 * 		data: {}
	 * }
	 */
	public static CompletableFuture<ResponseModel> updateColor(BoardModel board) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request cap nhat board
				ResponseModel ress = MainLibrary.sendPost("http://api.kaito.ninja/board/"+board.id+"/color/put", "color="+board.color, Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject(String.valueOf(ress.data.get("response")));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
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

	/**
	 * Xoa board
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...,
	 * 		data: {}
	 * }
	 */
	public static CompletableFuture<ResponseModel> delete(BoardModel board) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request xoa board
				ResponseModel ress = MainLibrary.sendGet("http://api.kaito.ninja/board/"+board.id+"/delete", Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject(String.valueOf(ress.data.get("response")));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
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
