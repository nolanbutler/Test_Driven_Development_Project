package com.comp4004.nolanbutler.a1;
import java.util.Map;
import java.util.HashMap;

public class Title {
	private String title, author;
	private int publicationYear, id;
	private boolean reserved;
	Map<Integer, Item> items;
	
	public Title(){
		this.title = "";
		this.author = "";
		this.publicationYear = -1;
		this.id = -1;
		this.reserved = false;
		items = new HashMap<Integer, Item>();
	}	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Object getPublicationYear() {
		return this.publicationYear;
	}
	public void setPublicationYear(int year) {
		this.publicationYear = year;
	}	
	public int getId() {
		return this.id;
	}
	public void setId(int titleId) {
		this.id = titleId;
	}
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	public boolean isReserved(){
		return this.reserved;
	}
	public void addItem(int itemId, Item newItem) {
		items.put(itemId, newItem);
	}
	public void removeItem(int itemId){
		items.remove(itemId);
	}
}
