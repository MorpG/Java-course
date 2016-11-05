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

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(long timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
} 