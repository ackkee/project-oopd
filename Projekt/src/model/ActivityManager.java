package model;

import java.util.LinkedList;

public class ActivityManager {
	private LinkedList<Activity> list = new LinkedList<>();
	private static int ID = 1;
	public ActivityManager() {
	}

	public void addActivity(String file, String name) {
		list.add(new Activity(file,name, ID));
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
