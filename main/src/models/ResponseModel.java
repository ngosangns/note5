package models;

import java.util.Map;

/**
 * Model chứa thông tin trả về khi dùng bất đồng bộ
 * @author Admin
 *
 */
public class ResponseModel {
	public boolean status;
	public String message;
	public Map<String, Object> data;
}
