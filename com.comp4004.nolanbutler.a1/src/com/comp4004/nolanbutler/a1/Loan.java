package com.comp4004.nolanbutler.a1;

public class Loan {
	private User user;
	private Item item;
	private int id, due, renews;
	
	public Loan(){
		user = null;
		item = null;
		id = -1;
		due = 99999; //high so that in event of an error, people won't get fined
		renews = 0;
	}

	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user = user;
	}
	public Item getItem(){
		return item;
	}
	public void setItem(Item item){
		this.item = item;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int itemId) {
		this.id = itemId;
	}
	public int getDue(){
		return due;
	}
	public void setDue(int due){
		this.due = due;
	}
	public int getRenews(){
		return renews;
	}
	public void setRenew(int renews){
		this.renews = renews;
	}
	public void renew(int due){
		renews++;
		this.due = due;
	}
	public void checkout(User user, Item item, int due){
		this.user = user;
		this.item = item;
		this.due = due;
	}
	public double calculateFine(){
		return Math.max(0, due * -0.25);
	}
	public void checkin(){
		user = null;
		item = null;
	}
}