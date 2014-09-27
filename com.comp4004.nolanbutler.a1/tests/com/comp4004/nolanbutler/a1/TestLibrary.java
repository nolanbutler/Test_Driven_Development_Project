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

		assertEquals("Now the user should exist", library.getUser(userId).getName(), name);
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

		library.addUser(newUser); //this should throw an exception due to newUser already existing in the system
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

		assertEquals("Now the title should exist: test title", library.getTitle(id).getTitle(), title);
		assertEquals("Now the title should exist: test author", library.getTitle(id).getAuthor(), author);
		assertEquals("Now the title should exist: test year", library.getTitle(id).getPublicationYear(), year);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addTitleNotEnoughInformation() { //USER STORY 2.2
		//test input
		String title = "Dune Messiah";
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		
		Library library = new Library();
		
		library.addTitle(newTitle); //throws exception due to newTitle not having enough information
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

		library.addTitle(newTitle); //throws exception due to newTitle already existing in the system
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
		
		assertEquals("Now the item exists: check it's title's name", library.getItem(itemId).getTitle().getTitle(), title);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addItemNoTitleExists() { //USER STORY 3.2
		//test input
		String title = "Heretics of Dune";
		String author = "Frank Herbert";
		int year = 1984;
		//end of test input
		
		Title newTitle = new Title();
		newTitle.setTitle(title);
		newTitle.setAuthor(author);
		newTitle.setPublicationYear(year);
		
		Item newItem = new Item();
		
		Library library = new Library();
		
		library.addItem(1111, newItem);
		//todo: verify that a prompt occurs -how?
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
		
		assertEquals("Now the user should exist", library.getUser(id).getName(), name);
		
		library.deleteUser(id);

		fail("This should throw an error: library.getUser = " + library.getUser(id));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteUserDoesNotExist() { //USER STORY 4.2
		//test input
		int id = 1234;
		//end of test input
		
		Library library = new Library();
		
		library.deleteUser(id);
	}
	
	@Ignore //cannot implement until we tackle the loaning system
	void deleteUserHasLoan() { //USER STORY 4.3
		fail("not yet implemented");
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

		assertEquals("Now the title should exist: test title", library.getTitle(titleId).getTitle(), title);
		assertEquals("Now the title should exist: test author", library.getTitle(titleId).getAuthor(), author);
		assertEquals("Now the title should exist: test year", library.getTitle(titleId).getPublicationYear(), year);
		
		library.deleteTitle(titleId);
		
		fail("This should throw an error: library.getTitle = " + library.getTitle(titleId));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteTitleDoesNotExist() { //USER STORY 5.2
		//test input
		int id = 5678;
		//end of test input

		Library library = new Library();
		
		library.deleteTitle(id);
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
	}
	
	@Ignore //cannot implement until we tackle loaning system
	public void deleteTitleHasCopyLoaned() { //USER STORY 5.4
		fail("not yet implemented");
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
		
		assertEquals("Now the item exists: check it's title's name", library.getItem(itemId).getTitle().getTitle(), title);
		
		library.deleteItem(itemId);
		
		fail("This should throw an error: library.getItem = " + library.getItem(itemId));
	}
	
@Ignore //cannot implement until we tackle loaning system
	public void deleteItemIsLoaned() { //USER STORY 6.2
		fail("not yet implemented");
	}
}
