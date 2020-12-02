package models;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class API {
	/**
	 * Gửi get request
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public CompletableFuture<ResponseModel> sendGet(String url) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
            try {
            	URL obj = new URL(url);
        		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        		con.setRequestMethod("GET");
        		// Thêm request header
        		con.setRequestProperty("User-Agent", "Mozilla/5.0");
        		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        		String inputLine;
        		StringBuffer resString = new StringBuffer();
        		while ((inputLine = in.readLine()) != null) {
        			resString.append(inputLine);
        		}
        		in.close(); // Đóng kết nối
        		res.message = (String) resString.toString();
        		res.status = true;
            } catch (Exception e) {
				e.printStackTrace();
				res.message = "Có lỗi xảy ra khi kết nối";
        		res.status = false;
			}
			return res;
        });
	}
	
	/**
	 * Gửi get request với token
	 * @param url
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public CompletableFuture<ResponseModel> sendGet(String url, String token) throws Exception {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
            try {
            	URL obj = new URL(url);
        		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        		con.setRequestMethod("GET");
        		// Thêm request header
        		con.setRequestProperty("User-Agent", "Mozilla/5.0");
        		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        		con.setRequestProperty("authorization", "Bearer "+token);
        		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        		String inputLine;
        		StringBuffer resString = new StringBuffer();
        		while ((inputLine = in.readLine()) != null) {
        			resString.append(inputLine);
        		}
        		in.close(); // Đóng kết nối
        		res.message = resString.toString();
        		res.status = true;
            } catch (Exception e) {
				e.printStackTrace();
				res.message = "Có lỗi xảy ra khi kết nối";
        		res.status = false;
			}
			return res;
        });
	}
	
	/**
	 * Gửi post request
	 * @param url
	 * @param urlParameters
	 * @return
	 */
	public CompletableFuture<ResponseModel> sendPost(String url, String urlParameters) {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection)obj.openConnection();
				// Thêm request header
				con.setRequestMethod("POST");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				// Gửi request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close(); // Đóng request
				BufferedReader in
					= new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer resString = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					resString.append(inputLine);
				}
				in.close();
				res.message = resString.toString();
				res.status = true;
            } catch (Exception e) {
				e.printStackTrace();
				res.message = "Có lỗi xảy ra khi kết nối";
        		res.status = false;
			}
			return res;
        });
	}
	
	/**
	 * Gửi post request với token
	 * @param url
	 * @param urlParameters
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public CompletableFuture<ResponseModel> sendPost(String url, String urlParameters, String token) throws Exception {
		return CompletableFuture.supplyAsync(() -> {
			ResponseModel res = new ResponseModel();
			try {
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection)obj.openConnection();
				// Thêm request header
				con.setRequestMethod("POST");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				con.setRequestProperty("authorization", "Bearer "+token);
				// Gửi request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close(); // Đóng request
				BufferedReader in
					= new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer resString = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					resString.append(inputLine);
				}
				in.close();
				res.message = resString.toString();
				res.status = true;
            } catch (Exception e) {
				e.printStackTrace();
				res.message = "Có lỗi xảy ra khi kết nối";
        		res.status = false;
			}
			return res;
        });
	}
	
	/**
	 * Convert string to map
	 * @param jsonString
	 * @return
	 * @throws JSONException
	 */
	public Map<String, Object> stringToMap(String jsonString) throws JSONException {
		return jsonToMap(new JSONObject(jsonString));
	}
	
	/**
	 * Convert json to map
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
	    Map<String, Object> retMap = new HashMap<String, Object>();
	    if(json != JSONObject.NULL) {
	        retMap = toMap(json);
	    }
	    return retMap;
	}
	public Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	public List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
	
	/**
	 * Ghi string vào user_token
	 * @param token
	 * @return
	 */
	public boolean writeTokenFile(String token) {
		// Đọc user token lấy token
	    try {
	    	//Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
	    	FileOutputStream fos = new FileOutputStream("user_token");
	    	DataOutputStream dos = new DataOutputStream(fos);
	    	//Bước 2: Ghi dữ liệu
	    	dos.writeBytes(token);
	    	//Bước 3: Đóng luồng
	    	fos.close();
	    	dos.close();
	    	return true;
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
	}
	
	/**
	 * Đọc user token lấy token
	 * @return Token string
	 */
	public String readTokenFile() {
		String res = null;
	    try {
			FileInputStream fileInputStream = new FileInputStream("user_token");
			Scanner scanner = new Scanner(fileInputStream);
			
			if(scanner.hasNextLine()) {
				res += scanner.nextLine();
			}
			
			// Đóng file
			try {
				scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
            	ex.printStackTrace();
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
}
