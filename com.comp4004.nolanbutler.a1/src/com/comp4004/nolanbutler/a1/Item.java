package com.comp4004.nolanbutler.a1;

public class Item {
	private Title title;
	private int id;
	private boolean referenceCopy;
	private Loan loan;
	
	public Item(){
		this.title = null;
		id = -1;
		referenceCopy = false;
		loan = null;
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
	public boolean isReferenceCopy(){
		return referenceCopy;
	}
	public void setReferenceCopy(boolean b){
		referenceCopy = b;
	}
	public void addLoan(Loan l){
		this.loan = l;
	}
	public boolean isLoaned(){
		return !(loan == null);
	}
	public Loan getLoan(){
		return loan;
	}
	public void removeLoan(){
		loan = null;
	}

}
