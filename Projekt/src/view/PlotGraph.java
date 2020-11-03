package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.Session;
import model.DataFetcher;
import model.TrackPoint;


public class PlotGraph extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private DataFetcher data;
	private double elapsedTime;
	private double minDataValue;
	private double maxDataValue;
	private int[] xPixel;
	private int[] yPixel;
	private LinkedList<TrackPoint> trackPointList;

	public PlotGraph(String title,DataFetcher fetcher){
		this.data = fetcher;
		this.trackPointList = (LinkedList<TrackPoint>) Session.getInstance().getCurrActivity().getTracks();
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(title));
	}
	
	private void findLimits(){
		if (trackPointList != null && trackPointList.size()>1){
			TrackPoint firstTp = trackPointList.getFirst();
			TrackPoint lastTp = trackPointList.getLast();
			minDataValue = maxDataValue = data.fetch(firstTp);
			elapsedTime= lastTp.getElapsedTime();
			for (TrackPoint p : trackPointList){
				double value = data.fetch(p);
				if (value > maxDataValue)
					maxDataValue = value;
				else if(value < minDataValue)
					minDataValue = value;
				
			}
		}
	}
	
	private void createArrays(){
		if(trackPointList != null && trackPointList.size() > 0){
			findLimits();
			width = getWidth();
			height = getHeight();
			yPixel = new int[width];
			xPixel = new int[width];
			double timeStep = elapsedTime/width;
			double yVariation = maxDataValue - minDataValue;
			double yScale = height/yVariation;
			Iterator<TrackPoint> tpit = trackPointList.iterator();
			if (tpit.hasNext()){
				TrackPoint tp = tpit.next();
				for(int i =0; i<width;i++){
					double time = i*timeStep;
					while(tpit.hasNext()&&tp.getElapsedTime()<time)
						tp = tpit.next();
					double value= data.fetch(tp);
					value = value-minDataValue;
					yPixel[i] = height - (int)(0.5 + yScale*value);
					xPixel[i] = i;
				}
			}
		}
	}
	
	public void update() {
		this.trackPointList = (LinkedList<TrackPoint>) Session.getInstance().getCurrActivity().getTracks();
	}
	
	@Override
	public void paintComponent (Graphics g){
		System.out.println(".");
		super.paintComponents(g);
		createArrays();
		g.setColor(Color.BLUE);
		g.drawPolyline(xPixel, yPixel, width);
	}
}