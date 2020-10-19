package model;

import java.util.LinkedList;

public class ActivityManager {
	private LinkedList<Activity> list = new LinkedList<>();
	private static int ID = 1;
	public ActivityManager() {
	}

	public int addActivity(String file, String name) {
		list.add(new Activity(file,name, ID));
		return ID++;
	}
	
	public int removeActivity(String name) {
		int returnedID = -1;
		for(Activity a : list) {
			if(a.getName().equalsIgnoreCase(name)) {
				list.remove(a);
				returnedID = a.getID();
				a = null;
			}
		}
		return returnedID -1;
	}
	
	public Activity getActivity(int id) {
		for(Activity a : list) {
			if(a.getID() == id) {
				return a;
			}
		}
		return null;
	}
	
	public LinkedList<Activity> getActivities(){
		return list;
	}
}
