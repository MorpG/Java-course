package ru.agolovin.models;

public class Item{
	private String id;
	
	public String name;

	public String description;
	
	public long timeCreate;

	public Item() {}

	public Item(String name, String description, long timeCreate) {
		this.name = name;
		this.description = description;
		this.timeCreate = timeCreate;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public long getTimeCreate() {
		return this.timeCreate;
	} 

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
} 