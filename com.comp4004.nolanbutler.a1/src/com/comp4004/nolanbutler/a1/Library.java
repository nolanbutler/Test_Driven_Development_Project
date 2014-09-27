package com.comp4004.nolanbutler.a1;
import java.util.Map;
import java.util.HashMap;

public class Library {
	private Map<Integer, User> users;
	private Map<Integer, Title> titles;
	private Map<Integer, Item> items;
	public Library(){
		users = new HashMap<Integer, User>();
		titles = new HashMap<Integer, Title>();
		items = new HashMap<Integer, Item>();
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
		if(users.remove(userId) == null) throw new IllegalArgumentException("deleteUser: User does not exist");
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
		titles.remove(titleId);
	}	
	
	public int addItem(int titleId, Item newItem) {
		if(titles.get(titleId) == null){
			throw new IllegalArgumentException("addItem: Title does not exist");
		}
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
		item.getTitle().removeItem(itemId);
	}
}
