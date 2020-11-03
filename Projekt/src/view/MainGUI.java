package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Session;


public class MainGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private static JPanel center = new JPanel(new GridLayout(4,1));
	public MainGUI() {
		setTitle("Gizmo 2020");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north.add(new JLabel(Session.getInstance().getCurrActivity().getName()));
		ImageIcon icon = new ImageIcon("edit.png");
		Image temp = icon.getImage();
		Image newImg = temp.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		JButton changeName = new JButton("Byt namn",icon);
		changeName.setBackground(Color.WHITE);
		north.add(changeName);
		panel.add(north,BorderLayout.NORTH);
		test();
		panel.add(center, BorderLayout.CENTER);
		panel.add(new BottomBar(), BorderLayout.SOUTH);
		add(panel);
	}
	
	public void test() {
		center.add(new PlotGraph("HR", TP -> TP.getHRate()));
		center.add(new PlotGraph("Alt", TP -> TP.getAlt()));
		center.add(new PlotGraph("Speed", TP -> TP.getSpeed()));
	}
	
	public static void update() {
		center.removeAll();
		center.add(new PlotGraph("HR", TP -> TP.getHRate()));
		center.add(new PlotGraph("Alt", TP -> TP.getAlt()));
		center.add(new PlotGraph("Speed", TP -> TP.getSpeed()));
		center.repaint();
	}
}
