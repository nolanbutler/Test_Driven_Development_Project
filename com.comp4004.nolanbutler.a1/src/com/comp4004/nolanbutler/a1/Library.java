package com.comp4004.nolanbutler.a1;
import java.util.Map;
import java.util.HashMap;

public class Library {
	private Map<Integer, User> users;
	private Map<Integer, Title> titles;
	private Map<Integer, Item> items;
	private Map<Integer, Loan> loans;
	public Library(){
		users = new HashMap<Integer, User>();
		titles = new HashMap<Integer, Title>();
		items = new HashMap<Integer, Item>();
		loans = new HashMap<Integer, Loan>();
	}
	
	public int addUser(User newUser) {
		int userId = newUser.hashCode();
		if(users.get(userId) == null){
			newUser.setId(userId);
			users.put(userId, newUser);
			return userId;
		}
		throw new IllegalArgumentException("addUser: User already exists");
	}

	public User getUser(int userId) {
		User user = users.get(userId);
		if(user == null) throw new IllegalArgumentException("getUser: User does not exist");
		return user;
	}

	public void deleteUser(int userId) {
		User user = users.get(userId);
		if(user == null) throw new IllegalArgumentException("deleteUser: User does not exist");
		if(user.hasLoan()) throw new IllegalArgumentException("deleteUser: User has loan");
		
		users.remove(userId);
	}	

	public int addTitle(Title newTitle) {
		if(newTitle.getTitle().equals("") || newTitle.getAuthor().equals("")) throw new IllegalArgumentException("addTitle: Not enough information");
		int titleId = newTitle.hashCode();
		if(titles.get(titleId) == null){
			newTitle.setId(titleId);
			titles.put(titleId, newTitle);
			return titleId;
		}
		throw new IllegalArgumentException("addTitle: Title already exists");
	}

	public Title getTitle(int titleId) {
		Title title = titles.get(titleId);
		if(title == null) throw new IllegalArgumentException("getTitle: Title does not exist");
		return title;
	}

	public void deleteTitle(int titleId) {
		Title title = titles.get(titleId);
		if(title == null) throw new IllegalArgumentException("deleteTitle: Title does not exist");	
		if(title.isReserved()) throw new IllegalArgumentException("deleteTitle: This title is reserved");
		if(title.hasLoanedCopy()) throw new IllegalArgumentException("deleteTitle: This title has a loaned copy");
		titles.remove(titleId);
	}	
	
	public int addItem(int titleId, Item newItem) {
		if(titles.get(titleId) == null) throw new IllegalArgumentException("addItem: Title does not exist");
		int itemId = newItem.hashCode();
		if(items.get(itemId) == null){
			newItem.setId(itemId);
			newItem.setTitle(titles.get(titleId));
			newItem.getTitle().addItem(itemId, newItem);
			
			items.put(itemId, newItem);
						
			return itemId;
		}
		//if we're still here, the item was found properly
		throw new IllegalArgumentException("addItem: Item already exists");
	}
	
	public Item getItem(int itemId) {
		Item item = items.get(itemId);
		if(item == null) throw new IllegalArgumentException("getItem: Item does not exist");
		return item;
	}

	public void deleteItem(int itemId) {
		Item item = items.remove(itemId);
		if(item == null) throw new IllegalArgumentException("deleteItem: Item does not exist");
		if(item.isLoaned()) throw new IllegalArgumentException("deleteItem: Item is loaned");
		
		item.getTitle().removeItem(itemId);
	}

	public int requestLoan(int userId, int itemId) {
		User user = users.get(userId);
		Item item = items.get(itemId);
		
		if(user == null) throw new IllegalArgumentException("requestLoan: User does not exist");
		if(item == null) throw new IllegalArgumentException("requestLoan: Item does not exist");
		if(!user.canLoan()) throw new IllegalArgumentException("requestLoan: User has a fine or >10 books");
		if(item.isReferenceCopy()) throw new IllegalArgumentException("requestLoan: Item is a reference copy");
		if(item.getTitle().isReserved()) throw new IllegalArgumentException("requestLoan: Item is reserved");
		
		Loan newLoan = new Loan();
		int loanId = user.getId()/10 + item.getId()/10;
		newLoan.checkout(user, item, 7); //default 1 week to return
		newLoan.setId(loanId);
		
		loans.put(loanId, newLoan);
		
		user.addLoan(newLoan);
		item.addLoan(newLoan);
		
		return loanId;
	}

	public Loan getLoan(int loanId) {
		Loan loan = loans.get(loanId);
		if(loan == null) throw new IllegalArgumentException("getLoan: Loan does not exist");
		return loan;
	}
	
	public void renewLoan(int loanId){
		Loan loan = loans.get(loanId);
		if(loan == null) throw new IllegalArgumentException("renewLoan: loan does not exist");
		if(loan.getUser().getFine() > 0) throw new IllegalArgumentException("renewLoan: user's fine must be settled");
		if(loan.getItem().getTitle().isReserved()) throw new IllegalArgumentException("renewLoan: title is reserved");
		if(loan.getRenews() >= 2) throw new IllegalArgumentException("renewLoan: item has already been renewed twice in a row");
		
		loan.renew(7);
	}
}
