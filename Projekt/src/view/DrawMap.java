package view;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

import javax.swing.JPanel;

import controller.Session;
import model.TrackPoint;
 
/*
 * 
 * Nästintill all designkod här är lånad av lärarna (Hanna)
 * 
 */

  
public class DrawMap extends JPanel {
	
	private static final long serialVersionUID = 1L;
	double maxLon=0.0;
	double minLon=0.0;
	double maxLat=0.0;
	double minLat=0.0;
	private LinkedList<TrackPoint> trackPointList;
	 public DrawMap(){
		 trackPointList = (LinkedList<TrackPoint>)Session.getInstance().getCurrActivity().getTracks();
		 this.setLayout(new BorderLayout());
	 }
	 protected void paintComponent(Graphics g){
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g;
		 findMaxMinLongLat();
		 
		 int xArr[] = new int[trackPointList.size()];
		 int yArr[] = new int[trackPointList.size()];
		 int i =0;
		 for(TrackPoint p : trackPointList){
			 xArr[i] = getXPixelValue(p);
			 yArr[i]= getYPixelValue(p);
			 i++;
		 }
		 g.drawPolyline(xArr, yArr, xArr.length);
		 g.setColor(Color.RED);
		 g.drawLine(10, 20, 10, getHeight());
		 g.drawLine(0, getHeight()-10, getWidth()-20, getHeight()-10);
		 g.drawString(""+maxLat, 5, 13);
		 Font font = new Font(null, Font.PLAIN, 10);   
		 AffineTransform affineTransform = new AffineTransform();
		 affineTransform.rotate(Math.toRadians(90), 0, 0);
		 Font rotatedFont = font.deriveFont(affineTransform);
		 g2.setFont(rotatedFont);
		 g2.drawString(""+maxLon, getWidth()-16,getHeight()-51);
		 g.setColor(Color.BLUE);
		
		 
	 }
	
	 private int getXPixelValue(TrackPoint tp){
		 int xPix= (int)(((tp.getLng()-minLon)/(maxLon-minLon))*getWidth());
		 return xPix;
	 }
	 private int getYPixelValue(TrackPoint tp){
		 int yPix = (int)(((tp.getLat()-minLat)/(maxLat-minLat))*getHeight());
		 yPix = getHeight()-yPix;
		 return yPix; 
	 }
	 
	 private void findMaxMinLongLat(){
		 minLat= trackPointList.getFirst().getLat();
		 maxLat = trackPointList.getLast().getLat();
		 minLon = trackPointList.getFirst().getLng();
		 maxLat = trackPointList.getLast().getLng();
		 for(TrackPoint p : trackPointList){
			 double lon = p.getLng();
			 double lat =p.getLat();
			 if(lon>maxLon)
				 maxLon = lon;
			 else if(lon < minLon)
				 minLon = lon;
			 if (lat > maxLat)
				 maxLat = lat;
			 else if (lat < minLat)
				 minLat = lat;
		 }
	 } 
}