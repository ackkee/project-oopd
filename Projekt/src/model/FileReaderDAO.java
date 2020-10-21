package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileReaderDAO {

	private static FileReaderDAO instance;
	private List<TrackPoint> activityList = new LinkedList<>();
	private List<User> userList = new LinkedList<>();
	private File userFile = new File("users.dta");
	
		
	private FileReaderDAO() {
		
	}
	
	public static FileReaderDAO getInstance() {
		if (instance == null)
			instance = new FileReaderDAO();
		return instance;
	}
	
	public List<TrackPoint> getActivity(File file) {
		try {
			activityList.clear();
			Scanner scanner = new Scanner(file);
			String lineOfText;
			while(scanner.hasNextLine()) {
				lineOfText = scanner.nextLine();
				if(lineOfText.startsWith("Date")) {continue;}
				TrackPoint mp = new TrackPoint(lineOfText);
				this.activityList.add(mp);
				
			}
			scanner.close();
			return this.activityList;
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<User> getUsers(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(userFile));
			LinkedList<User> test = (LinkedList<User>) input.readObject();
			userList = test;
			input.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public void storeUsers(List<User> list) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(userFile));
			output.writeObject(list);
			System.out.println("Writing user");
			output.flush();
			output.close();
		} catch(FileNotFoundException f) {
			f.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}