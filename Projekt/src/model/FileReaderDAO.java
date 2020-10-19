package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileReaderDAO {

	private static FileReaderDAO instance;
	private List<TrackPoint> activityList = new LinkedList<>();
	private List<User> userList = new LinkedList<>();
	private File userFile = new File("users.txt");
	
		
	private FileReaderDAO() {
		
	}
	
	public static FileReaderDAO getInstance() {
		if (instance == null)
			instance = new FileReaderDAO();
		return instance;
	}
	
	public List<TrackPoint> getActivity(String file) {
		try {
			activityList.clear();
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()) {
				TrackPoint mp = new TrackPoint(scanner.nextLine());
				this.activityList.add(mp);
			}
			scanner.close();
			return this.activityList;
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	

	public List<User> getUsers(){
		try {
			Scanner scanner = new Scanner(userFile);
			while(scanner.hasNextLine()) {
				User u = new User(scanner.nextLine());
				this.userList.add(u);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public void storeUsers() {
		try {
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(userFile));
			for(Object u : userList) {
				ois.writeObject(u);
			}
			ois.close();
		} catch(FileNotFoundException f) {
			f.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}