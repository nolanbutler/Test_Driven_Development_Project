package com.comp4004.nolanbutler.a1;

public class User {
	private String name;
	private int id;
	private double fine;
	private Loan[] loans;
	int numOfLoans;
	
	public User(){
		this.name = "";
		this.id = -1;
		fine = 0.0;
		loans = new Loan[10];
		numOfLoans = 0;
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
	public void changeFine(double f){
		fine = Math.max(fine + f, 0);
	}
	public double getFine(){
		return fine;
	}
	public void addLoan(Loan l){
		for(int i = 0; i < 10; i++){
			if(loans[i] == null){
				loans[i] = l;
				numOfLoans++;
				break;
			}
		}
	}
	public boolean hasLoan(){
		return numOfLoans > 0;
	}
	public void removeLoan(int loadId){
		for(int i = 0; i < 10; i++){
			if(loans[i].getId() == loadId){
				loans[i] = null;
				numOfLoans--;
			}
		}
	}
	public boolean canLoan(){
		return fine == 0 && numOfLoans < 10;
	}
}
