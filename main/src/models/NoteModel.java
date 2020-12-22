package models;

public class NoteModel extends ObjectModel {
	public String board_id;
	
	public NoteModel(String id, String name, String date_created, String board_id, String color) {
		this.id = id;
		this.name = name;
		this.date_created = date_created;
		this.board_id = board_id;
		this.color = color;
	}
	
	public NoteModel() { }
	
	public NoteModel(String name) {
		this.name = name;
	}
}
