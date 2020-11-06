package view;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Session;
import model.Activity;
import observer.Observer;


public class MainGUI extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	final static String MAPPANEL = "Map panel";
	final static String ACTIVITYPANEL = "Activity panel";
	private JButton changeName;
	private static CardLayout cardLayout = new CardLayout();
	private static JPanel centerPanel;
	private JPanel mapCard, activityCard;
	private static JPanel activityCardCenter = new JPanel(new GridLayout(4,3));
	private BottomBar bottomBar;
	private static JLabel totDist,startTime,stopTime,maxAlt,minAlt,avgAlt,maxHr,minHr,avgHr,
	maxSpeed,minSpeed,avgSpeed,maxCad,minCad,avgCad, actName;
	public MainGUI() {
		setTitle("Gizmo 2020");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		Session.getInstance().addSubscriber(this);
		JPanel mainPanel = new JPanel(new BorderLayout());
		bottomBar = new BottomBar();
		centerPanel = new JPanel(cardLayout);
		activityCard = new JPanel(new BorderLayout());
		mapCard = new JPanel(new BorderLayout());
		activityCard.add(activityCardCenter, BorderLayout.CENTER);
		activityCard.add(activityCardSouth(), BorderLayout.SOUTH);
		if(Session.getInstance().getCurrActivity() != null) {
			activityCardCenter.add(new PlotGraph("HR", TP -> TP.getHRate()));
			activityCardCenter.add(new PlotGraph("ALT", TP -> TP.getAlt()));
			activityCardCenter.add(new PlotGraph("Speed", TP -> TP.getSpeed()));
			mapCard.add(new DrawMap(), BorderLayout.CENTER);
		}
		centerPanel.add(activityCard, ACTIVITYPANEL);
		centerPanel.add(mapCard, MAPPANEL);
		mainPanel.add(editButton(), BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomBar, BorderLayout.SOUTH);
		
		
		add(mainPanel);
	}
	
	private JPanel editButton() {
		JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		String name = (Session.getInstance().getCurrActivity() != null) ? Session.getInstance().getCurrActivity().getName() : "Ingen aktivitet vald";
		actName = new JLabel(name);
		ImageIcon icon = new ImageIcon("edit.png");
		Image temp = icon.getImage();
		Image newImg = temp.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		changeName = new JButton("Byt namn",icon);
		changeName.setBackground(Color.WHITE);
		editPanel.add(actName);
		editPanel.add(changeName);
		changeName.addActionListener(e -> {
			if(Session.getInstance().getCurrActivity() != null) {
				String input = JOptionPane.showInputDialog(this, "Byt namn på aktivitet.");
				if(input != null) {
					for(Activity a : Session.getInstance().getUser().getUserAM().getActivities()) {
						if(a.getName().equalsIgnoreCase(input)) {
							JOptionPane.showMessageDialog(getRootPane(), "Det här namnet är redan valt, var god välj ett annat.");
						} else {
							actName.setText(input);
							a.setName(input);
							BottomBar.updateList();
						}
					}
				}
			} else 
				JOptionPane.showMessageDialog(getRootPane(), "Ingen aktivitet vald.");
		});
		return editPanel;
	}
	
	public static void swapCard() {
		cardLayout.next(centerPanel);
	}
	
	private JPanel activityCardSouth() {
		JPanel activityCardSouth = new JPanel(new GridLayout(3,5));
		totDist = new JLabel("Total distance:");
		startTime = new JLabel("Start time:");
		stopTime = new JLabel("Stop time:");
		maxAlt = new JLabel("Max altitude:");
		minAlt =new JLabel("Min altitude:");
		avgAlt= new JLabel("Avg altitude:");
		maxHr = new JLabel("Max heartrate:");
		minHr= new JLabel("Min heartrate:");
		avgHr = new JLabel("Avg heartrate:");
		maxSpeed=new JLabel("Max speed:");
		minSpeed= new JLabel("Min speed:");
		avgSpeed = new JLabel("Avg speed:");
		maxCad = new JLabel("Max cadence:");
		minCad = new JLabel("Min cadence:");
		avgCad = new JLabel("Avg cadence:");
		activityCardSouth.add(totDist);
		activityCardSouth.add(startTime);
		activityCardSouth.add(stopTime);
		activityCardSouth.add(maxAlt);
		activityCardSouth.add(minAlt);
		activityCardSouth.add(avgAlt);
		activityCardSouth.add(maxHr);
		activityCardSouth.add(minHr);
		activityCardSouth.add(avgHr);
		activityCardSouth.add(maxSpeed);
		activityCardSouth.add(minSpeed);
		activityCardSouth.add(avgSpeed);
		activityCardSouth.add(maxCad);
		activityCardSouth.add(minCad);
		activityCardSouth.add(avgCad);
		return activityCardSouth;
	}
	
	public static JPanel getActivityCardCenter() {
		return activityCardCenter;
	}
	
	public void update() {
		Activity current = Session.getInstance().getCurrActivity();
		mapCard.removeAll();
		actName.setText("Ingen aktivitet vald");
		if(current != null){
			totDist.setText("Total Distance: " + current.getTotalDist());
			startTime.setText("Start Time: " + current.getStartTime());
			stopTime.setText("Stop Time: "+ current.getEndTime());
			maxAlt.setText("Max altitude: "+current.getMaxAlt());
			minAlt.setText("Min altitude: "+current.getMinAlt());
			avgAlt.setText("Avg altitude: " + current.getAvgAlt());
			maxHr.setText("Max heartrate: "+current.getMaxHR());
			minHr.setText("Min heartrate: "+current.getMinHR());
			avgHr.setText("Avg heartrate: "+current.getAvgHR());
			maxSpeed.setText("Max speed: " + current.getMaxSpd());
			minSpeed.setText("Min speed: " + current.getMinSpd());
			avgSpeed.setText("Avg speed: " + current.getAvgSpd());
			maxCad.setText("Max cad: " + current.getMaxCad());
			minCad.setText("Min cad: " + current.getMinCad());
			avgCad.setText("Avg cad: " + current.getAvgCad());
			actName.setText(current.getName());
			mapCard.add(new DrawMap(),BorderLayout.CENTER);
		}
		mapCard.repaint();
	}
}
