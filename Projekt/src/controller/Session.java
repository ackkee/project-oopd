package controller;

import java.util.ArrayList;
import java.util.List;

import model.Activity;
import model.User;
import observer.Observable;
import observer.Observer;

public class Session implements Observable{
	private static Session instance;
	private User user;
	private Activity currActivity;
	private List<Observer> observers = new ArrayList<>();
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
		return this.currActivity;
	}
	
	public void setCurrActivity(Activity a) {
		this.currActivity = a;
		notifySubscribers();
	}

	@Override
	public void addSubscriber(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeSubscriber(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifySubscribers() {
		for(Observer o : observers)
			o.update();
	}

}
