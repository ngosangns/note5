package models.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;

import main.Main;
import models.BoardModel;
import models.ResponseModel;
import models.NoteModel;
import models.library.MainLibrary;

public class NoteAPI {
	/**
	 * Lấy tất cả note từ board
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...,
	 * 		data: {
	 * 			"0": {...}
	 * 		}
	 * }
	 */
	public static CompletableFuture<ResponseModel> read(String board_id) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request lấy boards
				ResponseModel ress = MainLibrary.sendGet("http://api.kaito.ninja/notes/"+board_id, Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject(String.valueOf(ress.data.get("response")));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
						res.data = new HashMap<String, Object>();
						List<NoteModel> notes = new ArrayList<NoteModel>();
						Iterator<String> resJSONkeys = resJSON.keys();
						try {
							while(resJSONkeys.hasNext()) {
							    String key = resJSONkeys.next();
						    	JSONObject ijsonObject = (JSONObject) resJSON.get(key);
						    	NoteModel imodel = new NoteModel(
						    			String.valueOf(ijsonObject.get("ID")),
						    			String.valueOf(ijsonObject.get("NAME")),
						    			String.valueOf(ijsonObject.get("created_at")),
						    			String.valueOf(ijsonObject.get("BOARD_ID")),
						    			String.valueOf(ijsonObject.get("COLOR")));
						    	notes.add(imodel);
							}
						}
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						res.data.put("notes", notes);
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
	public static CompletableFuture<ResponseModel> create(NoteModel note, String board_id) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request lấy boards
				ResponseModel ress = MainLibrary.sendPost("http://api.kaito.ninja/notes/"+board_id, "name="+note.name,Main.logging_user.token).get();
				// Kiểm tra status, không có lỗi thì tiếp tục
				if(ress.status) {
					JSONObject resJSON = new JSONObject(String.valueOf(ress.data.get("response")));
					// Nếu không tồn tại thông báo lỗi
					if(!resJSON.has("message")) {
						res.status = true;
						res.data = new HashMap<String, Object>();
						res.data.put("id", resJSON.get("ID"));
						res.data.put("date_created", resJSON.get("created_at"));
						res.data.put("board_id", resJSON.get("BOARD_ID"));
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
	 * Cap nhat note
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...
	 * }
	 */
	public static CompletableFuture<ResponseModel> update(NoteModel note) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request cap nhat board
				ResponseModel ress = MainLibrary.sendPost("http://api.kaito.ninja/notes/"+note.board_id+"/"+note.id+"/put", "name="+note.name, Main.logging_user.token).get();
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
	 * Cap nhat mau note
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...
	 * }
	 */
	public static CompletableFuture<ResponseModel> updateColor(NoteModel note) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request cap nhat board
				ResponseModel ress = MainLibrary.sendPost("http://api.kaito.ninja/notes/"+note.board_id+"/"+note.id+"/color/put", "color="+note.color, Main.logging_user.token).get();
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
	 * Xoa note
	 * @return ResponseModel {
	 * 		status: ...,
	 * 		message: ...,
	 * 		data: {}
	 * }
	 */
	public static CompletableFuture<ResponseModel> delete(NoteModel note) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				// Gửi request xoa board
				ResponseModel ress = MainLibrary.sendGet("http://api.kaito.ninja/notes/"+note.board_id+"/"+note.id+"/delete", Main.logging_user.token).get();
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
