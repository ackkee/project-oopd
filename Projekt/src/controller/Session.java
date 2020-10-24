package controller;

import java.io.File;

import model.Activity;
import model.User;

public class Session {
	private static Session instance;
	private User user;
	private Activity currActivity;
	private Session() {
		
	}
	
	public static Session getInstance() {
		if(instance == null)
			instance = new Session();
		return instance;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Activity getCurrActivity() {
		if(!user.getUserAM().getActivities().isEmpty()) {
			currActivity = user.getUserAM().getActivities().get(0); 
		}else {
			setCurrActivity(new Activity(new File("test.csv"), "Springa"));
		}
		return currActivity;
	}
	
	public void setCurrActivity(Activity a) {
		this.currActivity = a;
	}
}
