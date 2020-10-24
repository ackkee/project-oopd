package model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private int age = 0;
	private String row;
	private ActivityManager am = new ActivityManager();
	public User(String row) {
		this.row = row;
		this.userName = fixRow();
		this.passWord = fixRow();
	}
	
	public String fixRow(){
		String temp = "";
		for (int i=0; i<this.row.length();i++){
			if(this.row.charAt(i) != ';' ){
				temp += this.row.charAt(i);
			} else {
				String newRow = this.row.substring(i+1, this.row.length());
				this.row = newRow;
				break;
			}
		}
		//För att kunna lagra i double krävs . och inte ,
		temp = temp.replace(",", ".");
		return temp;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassWord() {
		return this.passWord;
	}
	
	public ActivityManager getUserAM() {
		return this.am;
	}
	
	public void printActivityNames() {
		for(Activity a : am.getActivities())
			System.out.println(a.getName());
	}
}
