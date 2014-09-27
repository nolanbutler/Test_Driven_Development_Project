package com.comp4004.nolanbutler.a1;

public class User {
	private String name;
	private int id;
	
	public User(){
		this.name = "";
		this.id = -1;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setId(int userId) {
		this.id = userId;
	}

	public int getId() {
		return this.id;
	}
}
