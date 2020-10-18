package model;

import java.util.LinkedList;

public class UserManager {
	private static UserManager instance;
	private int age = 0;
	private LinkedList<User> list = new LinkedList<>();
	private UserManager() {
		list = (LinkedList<User>) FileReaderDAO.getInstance().getUsers();
	}
	
	public static UserManager getInstance() {
		if(instance == null)
			instance = new UserManager();
		return instance;
	}
	
	public void removeUser(String userName) {
		for(User u : list) 
			if(u.getUserName().equalsIgnoreCase(userName)) 
				list.remove(u);
	}
	public void addUser(String userName, String passWord) {
		list.add(new User(userName + ";" + passWord));
	}
	
	public User getUser(String userName) {
		try {
			for(User u : list) {
				if(u.getUserName().equalsIgnoreCase(userName))
					return u;
			} 
		} catch (NullPointerException e) {
			System.err.println("There is no user of that name");
		}
		return null;
	}
	
	public LinkedList<User> getAllUsers(){
		return list;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
}
