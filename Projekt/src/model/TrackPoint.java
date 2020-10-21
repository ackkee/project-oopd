
package model;

import java.io.Serializable;

public class TrackPoint implements Serializable {
	private static final long serialVersionUID = 1L;
	private String row;
	private String date;
	private String time;
	private double elapsedTime;
	private double lng;
	private double lat;
	private double alt;
	private double dist;
	private double hRate;
	private double speed;
	private double cadence;
	
	public TrackPoint(String row){
		this.row = row;
		this.date = fixRow();
		this.time = fixRow();
		this.elapsedTime = Double.parseDouble(fixRow());
		this.lng = Double.parseDouble(fixRow());
		this.lat = Double.parseDouble(fixRow());
		this.alt = Double.parseDouble(fixRow());
		this.dist = Double.parseDouble(fixRow());
		this.hRate = Double.parseDouble(fixRow());
		this.speed = Double.parseDouble(fixRow());
		this.cadence = Double.parseDouble(fixRow());
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getTime(){
		return this.time;
	}
	
	public double getElapsedTime(){
		return this.elapsedTime;
	}
	
	public double getLng(){
		return this.lng;
	}
	public double getLat(){
		return this.lat;
	}
	public double getAlt(){
		return this.alt;
	}
	public double getDist(){
		return this.dist;
	}
	public double getHRate(){
		return this.hRate;
	}
	public double getSpeed(){
		return this.speed;
	}
	public double getCadence(){
		return this.cadence;
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
}