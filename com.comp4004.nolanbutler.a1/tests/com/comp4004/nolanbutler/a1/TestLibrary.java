package com.comp4004.nolanbutler.a1;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class TestLibrary {
	//___________________________USER STORY BLOCK 1_______________________________________
	@Test
	public void addUser() { //USER STORY 1.1
		//test input
		String name = "Nolan Butler";
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		Library library = new Library();

		int userId = library.addUser(newUser);

		assertEquals("Now the user should exist", name, library.getUser(userId).getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addUserAlreadyExists() { //USER STORY 1.2
		//test input
		String name = "Nolan Butler";
		// end of test input
		
		User newUser = new User();
		newUser.setName(name);
		Library library = new Library();
		library.addUser(newUser);

		fail("This should throw an error: library.addUser = " + library.addUser(newUser)); //this should throw an exception due to newUser already existing in the system
	}
	
	//__________________________USER STORY BLOCK 2_______________________________________
	@Test
	public void addTitle() { //USER STORY 2.1
		//test input
		String title = "God Emperor of Dune";
		String author = "Frank Herbert";
		int year = 1981;
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Library library = new Library();
		
		int id = library.addTitle(newTitle);

		assertEquals("Now the title should exist: test title", title, library.getTitle(id).getTitle());
		assertEquals("Now the title should exist: test author", author, library.getTitle(id).getAuthor());
		assertEquals("Now the title should exist: test year", year, library.getTitle(id).getPublicationYear());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addTitleNotEnoughInformation() { //USER STORY 2.2
		//test input
		String title = "Dune Messiah";
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		
		Library library = new Library();
		
		fail("This should throw an error: library.addTitle = " + library.addTitle(newTitle)); //throws exception due to newTitle not having enough information
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addTitleAlreadyExists() { //USER STORY 2.3
		//test input
		String title = "Dune";
		String author = "Frank Herbert";
		int year = 1965;
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Library library = new Library();
		library.addTitle(newTitle);

		fail("This should throw an error: library.addTitle = " + library.addTitle(newTitle)); //throws exception due to newTitle already existing in the system
	}
	
	//_____________________________USER STORY BLOCK 3_____________________________________________
	@Test
	public void addItem() { //USER STORY 3.1
		//test input
		String title = "Children of Dune";
		String author = "Frank Herbert";
		int year = 1976;
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int titleId = library.addTitle(newTitle);
		
		int itemId = library.addItem(titleId, newItem);
		
		assertEquals("Now the item exists: check it's title's name", title, library.getItem(itemId).getTitle().getTitle());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addItemNoTitleExists() { //USER STORY 3.2
		//test input
		String title = "Heretics of Dune";
		String author = "Frank Herbert";
		int year = 1984;
		
		int fakeId = 1111;
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		fail("This should throw an error: library.additem = " + library.addItem(fakeId, newItem));
	}

	//__________________________USER STORY BLOCK 4_________________________________________________
	@Test(expected = IllegalArgumentException.class)
	public void deleteUser() { //USER STORY 4.1
		//test input
		String name = "Phillip K Dick";
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Library library = new Library();
		
		int id = library.addUser(newUser);
		
		assertEquals("Now the user should exist", name, library.getUser(id).getName());
		
		library.deleteUser(id);

		fail("This should throw an error: library.getUser = " + library.getUser(id));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteUserDoesNotExist() { //USER STORY 4.2
		//test input
		int fakeId = 1234;
		//end of test input
		
		Library library = new Library();
		
		library.deleteUser(fakeId);
		fail("Deleting a nonexistant user worked?");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteUserHasLoan() { //USER STORY 4.3
		//test input
		String name = "Ricardo";
		String title = "Beowulf";
		String author = "Seamus Heaney";
		int year = 1999;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		library.requestLoan(userId, itemId);
		
		library.deleteUser(userId);
		fail("Deleting the title should've thrown an error");
	}

	//____________________________USER STORY BLOCK 5_______________________________________________
	@Test(expected = IllegalArgumentException.class)
	public void deleteTitle() { //USER STORY 5.1
		//test input
		String title = "Chapterhouse Dune";
		String author = "Frank Herbert";
		int year = 1985;
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Library library = new Library();
		
		library.addTitle(newTitle);
		
		int titleId = library.addTitle(newTitle);

		assertEquals("Now the title should exist: test title", title, library.getTitle(titleId).getTitle());
		assertEquals("Now the title should exist: test author", author, library.getTitle(titleId).getAuthor());
		assertEquals("Now the title should exist: test year", year, library.getTitle(titleId).getPublicationYear());
		
		library.deleteTitle(titleId);
		
		fail("This should throw an error: library.getTitle = " + library.getTitle(titleId));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteTitleDoesNotExist() { //USER STORY 5.2
		//test input
		int fakeId = 5678;
		//end of test input

		Library library = new Library();
		
		library.deleteTitle(fakeId);
		fail("Deleting that title should've thrown an error");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteTitleIsReserved() { //USER STORY 5.3
		//test input
		String title = "Do Andorids Dream of Electric Sheep?";
		String author = "Phillip K. Dick";
		int year = 1968;
		//end of test input
				
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		newTitle.setReserved(true);
				
		Library library = new Library();
		int id = library.addTitle(newTitle);

		library.deleteTitle(id);
		fail("Deleting that title should've thrown an error");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteTitleHasCopyLoaned() { //USER STORY 5.4
		//test input
		String name = "Richard";
		String title = "My Russia";
		String author = "Peter Ustinov";
		int year = 1983;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		library.requestLoan(userId, itemId);
		
		library.deleteTitle(titleId);
		fail("Deleting the title should've thrown an error");
	}
	
	//____________________________USER STORY BLOCK 6___________________________________________
	@Test(expected = IllegalArgumentException.class)
	public void deleteItem() { //USER STORY 6.1
		//test input
		String title = "Children of Dune";
		String author = "Frank Herbert";
		int year = 1976;
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();		
		int titleId = library.addTitle(newTitle);		
		int itemId = library.addItem(titleId, newItem);
		
		assertEquals("Now the item exists: check it's title's name", title, library.getItem(itemId).getTitle().getTitle());
		
		library.deleteItem(itemId);
		
		fail("This should throw an error: library.getItem = " + library.getItem(itemId));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteItemIsLoaned() { //USER STORY 6.2
		//test input
		String name = "Robert";
		String title = "The Bible";
		String author = "King James";
		int year = 2014;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		library.requestLoan(userId, itemId);
		
		library.deleteItem(itemId);
		fail("Deleting the item should've thrown an error");
	}

	//_________________________________USER STORY BLOCK 7__________________________________________
	@Test
	public void requestLoan() { //7.1
		//test input
		String name = "John";
		String title = "Detective comics";
		String author = "Bman";
		int year = 1939;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		int loanId = library.requestLoan(userId, itemId);

		assertEquals("Loan is made: name of user = ", name, library.getLoan(loanId).getUser().getName());
		assertEquals("Loan is made: title of item = ", title, library.getLoan(loanId).getItem().getTitle().getTitle());
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestLoanNoUserExists() { //7.2
		//test input
		String title = "Action Comics #1";
		String author = "Supes";
		int year = 1938;
		
		int fakeId = 2468;
		//end of test input
	
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		fail("This should throw an error: library.requestLoan = " + library.requestLoan(fakeId, itemId));
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestLoanNoTitleExists() { //7.3
		//test input
		String name = "Jack";
		
		int fakeId = 1234;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		
		fail("This should throw an error: library.requestLoan = " + library.requestLoan(userId, fakeId));
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestLoanUserHasFine() { //7.4
		//test input
		String name = "Jason";
		double fine = 5.25;
		String title = "World's Finest Comics";
		String author = "Bman & Supes";
		int year = 1941;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		newUser.changeFine(fine);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		fail("This should throw an error: library.requestLoan = " + library.requestLoan(userId, itemId));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void requestLoanItemIsReferenceCopy(){ //7.5
		//test input
		String name = "Testbug";
		String title = "TDD for Dummies";
		String author = "Testbug Sr";
		int year = 2015;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		newItem.setReferenceCopy(true);
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		fail("This should throw an error: library.requestLoan = " + library.requestLoan(userId, itemId));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void requestLoanUserHasTenBooksAlready(){ //7.6
		//test input
		String name = "Jerry";
		String title = "Dictionary";
		String author = "Oxford";
		int year = 2014;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
	
		Item[] items = new Item[11];
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		
		for(int i = 0; i < 11; i++){
			items[i] = new Item();
			
			int itemId = library.addItem(titleId, items[i]);
			
			library.requestLoan(userId, itemId);
		}
		fail("It should've errored on the eleventh loop above");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void requestLoanTitleIsReserved(){ //7.7
		//test input
		String name = "Jordan";
		String title = "The Hobbit";
		String author = "J. R. R. Tolkien";
		int year = 1937;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		newTitle.setReserved(true);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		library.requestLoan(userId, itemId);
		fail("A reserved title was loaned");
	}
	
	@Test
	public void requestLoanTenthBookChangesPriveledges(){ //7.8
		//test input
		String name = "Jerry";
		String title = "Dictionary";
		String author = "Oxford";
		int year = 2014;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
	
		Item[] items = new Item[11];
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		
		for(int i = 0; i < 10; i++){
			items[i] = new Item();
			
			int itemId = library.addItem(titleId, items[i]);
			
			library.requestLoan(userId, itemId);
			
			assertEquals("Once user has ten books out, he cannot take another", i+1 != 10, library.getUser(userId).canLoan());
		}
	}

	@Ignore
	public void requestLoanReservationIsRemoved(){ //7.9
		fail("As the TOTEM paper's Appendix B does not include functionality to handle reservations... this is not implemented");
	}

	//_________________________________USER STORY BLOCK 8__________________________________________
	@Test
	public void renewLoan(){ //8.1
		//test input
		String name = "Jordan";
		String title = "The Hobbit";
		String author = "J. R. R. Tolkien";
		int year = 1937;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		int loanId = library.requestLoan(userId, itemId);

		assertEquals("The book has not been renewed",0, library.getLoan(loanId).getRenews());
		
		library.renewLoan(loanId);
		
		assertEquals("The book has been renewed", 1, library.getLoan(loanId).getRenews());
	}
	@Test(expected = IllegalArgumentException.class)
	public void renewLoanLoanDoesNotExist() { //8.2
		//test input
		int fakeId = 9999;
		//end of test input

		Library library = new Library();
		
		library.renewLoan(fakeId);
	}
	@Test(expected = IllegalArgumentException.class)
	public void renewLoanUserHasFine() { //8.3
		//test input
		String name = "Jack";
		String title = "A Game of Thrones";
		String author = "George R. R. Martin";
		int year = 1937;
		
		double fine = 1.11;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		int loanId = library.requestLoan(userId, itemId);

		library.getUser(userId).changeFine(fine);
		
		library.renewLoan(loanId);
		fail("Loan was renewed when fine was due");
	}
	@Test(expected = IllegalArgumentException.class)
	public void renewLoanTitleIsReserved() { //8.4
		//test input
		String name = "Jacob";
		String title = "A Clash of Kings";
		String author = "George R. R. Martin";
		int year = 1937;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		int loanId = library.requestLoan(userId, itemId);

		library.getTitle(titleId).setReserved(true);
		
		library.renewLoan(loanId);
		fail("Reserved book was renewed");
	}
	@Test(expected = IllegalArgumentException.class)
	public void renewLoanRenewedTwiceAlready() { //8.5
		//test input
		String name = "Jeffrey";
		String title = "A Storm of Swords";
		String author = "George R. R. Martin";
		int year = 1937;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
		
		int loanId = library.requestLoan(userId, itemId);

		library.renewLoan(loanId);
		library.renewLoan(loanId);
		
		assertEquals("The book has been renewed twice", 2, library.getLoan(loanId).getRenews());
		
		library.renewLoan(loanId);
		fail("Loan was renewed a third time");
	}
	//________________________________USER STORY BLOCK 9_____________________________
	@Test
	public void returnLoan(){ //9.1
		//test input
		String name = "John";
		String title = "Detective comics";
		String author = "Bman";
		int year = 1939;
		//end of test input
				
		User newUser = new User();
		newUser.setName(name);
				
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
				
		Item newItem = new Item();
				
		Library library = new Library();
				
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
				
		int loanId = library.requestLoan(userId, itemId);

		assertEquals("Loan is made: name of user = ", name, library.getLoan(loanId).getUser().getName());
		assertEquals("Loan is made: title of item = ", title, library.getLoan(loanId).getItem().getTitle().getTitle());
		
		library.returnLoan(loanId);

		assertEquals("Loan returned: item.isLoaned = ", false, library.getItem(itemId).isLoaned());
		assertEquals("Loan returned: user.hasLoan = ", false, library.getUser(userId).hasLoan());
	}
	@Test(expected = IllegalArgumentException.class)
	public void returnLoanLoanDoesNotExist(){
		//test input
		int fakeId = 9876;
		//end of test input
		
		Library library = new Library();
		
		library.returnLoan(fakeId);
	}
	@Test
	public void returnLoanLate(){
		//test input
		String name = "John";
		String title = "Detective comics";
		String author = "Bman";
		int year = 1939;
		//end of test input
				
		User newUser = new User();
		newUser.setName(name);
				
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
				
		Item newItem = new Item();
				
		Library library = new Library();
				
		int userId = library.addUser(newUser);
		int titleId = library.addTitle(newTitle);
		int itemId = library.addItem(titleId, newItem);
				
		int loanId = library.requestLoan(userId, itemId);

		library.getLoan(loanId).setDue(-2);
		
		library.returnLoan(loanId);
		
		assertTrue("User should now have a fine for returning it late: " + library.getUser(userId).getFine(), library.getUser(userId).getFine() > 0.0);
	}
	//________________________________USER STORY BLOCK 10_____________________________
	
	@Test
	public void collectFine(){ //10.1
		//test input
		String name = "Tywin";
		double debt = 1.50;
		double amountPayed = -1.50;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		newUser.changeFine(debt);
		Library library = new Library();
		int userId = library.addUser(newUser);
		
		assertTrue("In debt: " + newUser.getFine(), newUser.getFine() == debt);
		
		library.collectFine(userId, amountPayed);
		
		assertTrue("In debt", newUser.getFine() == debt + amountPayed);
	}
	@Test(expected = IllegalArgumentException.class)
	public void collectFineUserDoesNotExist(){ //10.2
		//test input
		int fakeId = 9999;
		double amountPayed = -1.00;
		//end of test input
		
		Library library = new Library();
		
		library.collectFine(fakeId, amountPayed);
	}
	@Test(expected = IllegalArgumentException.class)
	public void collectFinePayingNegativeAmount(){ //10.3
		//test input
		String name = "Cersei";
		double amountPayed = 1.50;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);

		Library library = new Library();
		int userId = library.addUser(newUser);
		
		library.collectFine(userId, amountPayed);
	}
	@Test(expected = IllegalArgumentException.class)
	public void collectFinePayingTooMuch(){ //10.4
		//test input
		String name = "Tyrion";
		double debt = 10.0;
		double amountPayed = -100.0;
		//end of test input
		
		User newUser = new User();
		newUser.setName(name);
		newUser.changeFine(debt);

		Library library = new Library();
		int userId = library.addUser(newUser);
		
		library.collectFine(userId, amountPayed);
	}
	//________________________________USER STORY BLOCK 11_____________________________
	@Test
	public void monitorSystem(){ //11.1
		//test input
		String name1 = "Jon", name2 = "Robb", name3 = "Sansa", name4 = "Arya", name5 = "Bran", name6 = "Rickon";
		String t1 = "Ghost", t2 = "Grey Wind", t3 = "Lady", t4 = "Nymeria", t5 = "Summer", t6 = "Shaggydog";
		String author = "Ned";
		int year = 1996;
		//
		
		User user1 = new User(); User user2 = new User(); User user3 = new User();
		User user4 = new User(); User user5 = new User(); User user6 = new User();
		user1.setName(name1); user2.setName(name2); user3.setName(name3);
		user4.setName(name4); user5.setName(name5); user6.setName(name6);
		
		Title title1 = new Title(); Title title2 = new Title(); Title title3 = new Title();
		Title title4 = new Title(); Title title5 = new Title(); Title title6 = new Title();
		title1.setTitle(t1); title2.setTitle(t2); title3.setTitle(t3);
		title4.setTitle(t4); title5.setTitle(t5); title6.setTitle(t6);
		title1.setAuthor(author); title2.setAuthor(author); title3.setAuthor(author);
		title4.setAuthor(author); title5.setAuthor(author); title6.setAuthor(author);
		title1.setPublicationYear(year); title2.setPublicationYear(year); title3.setPublicationYear(year);
		title4.setPublicationYear(year); title5.setPublicationYear(year); title6.setPublicationYear(year);
		
		Item item1 = new Item(); Item item2 = new Item(); Item item3 = new Item(); Item item4 = new Item();
		Item item5 = new Item(); Item item6 = new Item(); Item item7 = new Item(); Item item8 = new Item();
		
		Library library = new Library();
		
		int u1id = library.addUser(user1); int u2id = library.addUser(user2); int u3id = library.addUser(user3);
		int u4id = library.addUser(user4); int u5id = library.addUser(user5); int u6id = library.addUser(user6);
		
		int t1id = library.addTitle(title1); int t2id = library.addTitle(title2); int t3id = library.addTitle(title3);
		int t4id = library.addTitle(title4); int t5id = library.addTitle(title5); int t6id = library.addTitle(title6);
		
		int i1id = library.addItem(t1id, item1); int i2id = library.addItem(t2id, item2); int i3id = library.addItem(t3id, item3);
		int i4id = library.addItem(t4id, item4); int i5id = library.addItem(t5id, item5); int i6id = library.addItem(t6id, item6);
		int i7id = library.addItem(t3id, item7); int i8id = library.addItem(t5id, item8);
		
		library.requestLoan(u1id, i1id); library.requestLoan(u2id, i2id); library.requestLoan(u3id, i3id);
		library.requestLoan(u4id, i4id); library.requestLoan(u5id, i5id); library.requestLoan(u6id, i6id);
		
		//this needs manual verification, I'm not exactly sure how to do it
		String reportString = library.report();
		//fail(reportString);
	}
}
