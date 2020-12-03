package models;

import java.util.List;
import java.util.ArrayList;

public class BoardModel {
	public String id;
	public String name;
	public String date_created;
	public String user_id;
	public List<NoteModel> notes = new ArrayList<NoteModel>();
	
	public BoardModel(String id, String name, String date_created, String user_id) {
		super();
		this.id = id;
		this.name = name;
		this.date_created = date_created;
		this.user_id = user_id;
	}
	
	public BoardModel() { }
	
	@Override
	public String toString() {
	    return name;
	}
}