package model;

import java.io.Serializable;
import java.util.LinkedList;

public class ActivityManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private LinkedList<Activity> list = new LinkedList<>();
	public ActivityManager() {
	}

	public void addActivity(String file, String name) {
		list.add(new Activity(file,name));
	}
	
	public void removeActivity(String name) {
		for(Activity a : list) {
			if(a.getName().equalsIgnoreCase(name)) {
				list.remove(a);
				a = null;
			}
		}
	}
	
	public Activity getActivity(String name) {
		for(Activity a : list) {
			if(a.getName().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}
	
	public LinkedList<Activity> getActivities(){
		return list;
	}
}
