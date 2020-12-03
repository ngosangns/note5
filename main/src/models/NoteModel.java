package models;

import java.sql.Date;

public class NoteModel {
	public String id;
	public String name;
	public String date_created;
	public String board_id;
	
	public NoteModel(String id, String name, String date_created, String board_id) {
		this.id = id;
		this.name = name;
		this.date_created = date_created;
		this.board_id = board_id;
	}
	
	public NoteModel() { }
	
	public NoteModel(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
	    return name;
	}
}
