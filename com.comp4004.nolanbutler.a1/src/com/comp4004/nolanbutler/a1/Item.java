package com.comp4004.nolanbutler.a1;

public class Item {
	private Title title;
	private int id;
	
	public Item(){
		this.title = null;
		id = -1;
	}
	public Title getTitle() {
		return this.title;
	}
	public void setTitle(Title t) {
		this.title = t;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int itemId) {
		this.id = itemId;
	}

}
