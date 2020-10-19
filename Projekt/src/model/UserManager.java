package model;

import java.util.LinkedList;
import java.util.List;

public class UserManager {
	private static UserManager instance;
	private int age = 0;
	private List<User> list = new LinkedList<>();
	private UserManager() {
		list = FileReaderDAO.getInstance().getUsers();
	}
	
	public static UserManager getInstance() {
		if(instance == null)
			instance = new UserManager();
		return instance;
	}
	
	public void removeUser(String userName) {
		for(User u : list) 
			if(u.getUserName().equalsIgnoreCase(userName)) {
				list.remove(u);
				storeUsers();
				break;
			}
	}
	public void addUser(String userName, String passWord) {
		list.add(new User(userName + ";" + passWord));
		storeUsers();
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
	
	public void storeUsers() {
		FileReaderDAO.getInstance().storeUsers(this.list);
	}
	
	public List<User> getAllUsers(){
		return list;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
}
