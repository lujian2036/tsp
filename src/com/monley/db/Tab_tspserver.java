package com.monley.db;

//define table mobcenter.tsp_server object

public class Tab_tspserver {
	private int id;
	private String name;
	private String noteInformation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNoteInformation() {
		return noteInformation;
	}
	public void setNoteInformation(String noteInformation) {
		this.noteInformation = noteInformation;
	}

}
