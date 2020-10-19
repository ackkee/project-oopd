package model;

import java.util.LinkedList;
import java.util.List;

public class Activity {
	private List<TrackPoint> list = new LinkedList<>();
	private String name;
	private String file;
	private int ID;
	public Activity(String file, String name, int id) {
		this.file = file;
		this.name = name;
		this.ID = id;
		this.list = FileReaderDAO.getInstance().getActivity(this.file);
	}
	
	public double getTotalDist() {
		TrackPoint tp = list.get(list.size()-1);
		return tp.getDist();
	}
	
	public String getStartTime() {
		TrackPoint tp = list.get(0);
		return tp.getTime();
	}
	
	public String getEndTime() {
		TrackPoint tp = list.get(list.size()-1);
		return tp.getTime();
	}
	
	public double getMaxAlt() {
		double max = 0.0;
		for(int i = 1; i < list.size(); i++) {
			if(list.get(i).getAlt() > list.get(i - 1).getAlt())
				max = list.get(i).getAlt();
		}
		return max;
	}
	
	public double getMinAlt() {
		double min = list.get(0).getAlt();
		for(TrackPoint tp : list) {
			min = (min < tp.getAlt()) ? min : tp.getAlt();
		}
		return min;
	}
	
	public double getAvgAlt() {
		double avgAlt = 0.0;
		for(TrackPoint tp : list) {
			avgAlt += tp.getAlt();
		}
		avgAlt /= list.size();
		String s = String.format("%.1f", avgAlt).replaceAll(",", ".");
		return Double.parseDouble(s);
	}
	
	public double getMaxSpd() {
		double max = 0.0;
		for(int i = 1; i < list.size(); i++) {
			if(list.get(i).getSpeed() > list.get(i - 1).getSpeed())
				max = list.get(i).getSpeed();
		}
		return max;
	}
	
	public double getMinSpd() {
		double min = list.get(0).getSpeed();
		for(TrackPoint tp : list) {
			min = (min < tp.getSpeed()) ? min : tp.getSpeed();
		}
		return min;
	}
	
	public double getAvgSpd() {
		double avgSpd = 0.0;
		for(TrackPoint tp : list) {
			avgSpd += tp.getSpeed();
		}
		avgSpd /= list.size();
		String s = String.format("%.1f", avgSpd).replaceAll(",", ".");
		return Double.parseDouble(s);
	}
	
	public double getMaxCad() {
		double max = 0.0;
		for(int i = 1; i < list.size(); i++) {
			if(list.get(i).getCadence() > list.get(i - 1).getCadence())
				max = list.get(i).getCadence();
		}
		return max;
	}
	
	public double getMinCad() {
		double min = list.get(0).getCadence();
		for(TrackPoint tp : list) {
			min = (min < tp.getCadence()) ? min : tp.getCadence();
		}
		return min;
	}
	
	public double getAvgCad() {
		double avgCad = 0.0;
		for(TrackPoint tp : list) {
			avgCad += tp.getCadence();
		}
		avgCad /= list.size();
		String s = String.format("%.1f", avgCad).replaceAll(",", ".");
		return Double.parseDouble(s);
	}
	
	public double getMaxHR() {
		double max = 0.0;
		for(int i = 1; i < list.size(); i++) {
			if(list.get(i).getHRate() > list.get(i - 1).getHRate())
				max = list.get(i).getHRate();
		}
		return max;
	}
	
	public double getMinHR() {
		double min = list.get(0).getHRate();
		for(TrackPoint tp : list) {
			min = (min < tp.getHRate()) ? min : tp.getHRate();
		}
		
		return min;
	}
	
	public double getAvgHR() {
		double avgHr = 0.0;
		for(TrackPoint tp : list) {
			avgHr += tp.getHRate();
		}
		avgHr /= list.size();
		String s = String.format("%.1f", avgHr).replaceAll(",", ".");
		return Double.parseDouble(s);
	}
	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public List<TrackPoint> getTracks(){
		return this.list;
	}
}
