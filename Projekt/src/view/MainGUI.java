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
	public MainGUI() {
		setTitle("Gizmo 2020");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new GridLayout(4,1));
		JPanel test = new JPanel(new FlowLayout(FlowLayout.LEFT));
		test.add(new JLabel(Session.getInstance().getCurrActivity().getName()));
		ImageIcon icon = new ImageIcon("edit.png");
		Image temp = icon.getImage();
		Image newImg = temp.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		JButton changeName = new JButton("Byt namn",icon);
		changeName.setBackground(Color.WHITE);
		test.add(changeName);
		panel.add(test,BorderLayout.NORTH);
		center.add(new PlotGraph("HR", Session.getInstance().getCurrActivity(), TP -> TP.getHRate()));
		center.add(new PlotGraph("Alt", Session.getInstance().getCurrActivity(), TP -> TP.getAlt()));
		center.add(new PlotGraph("Speed", Session.getInstance().getCurrActivity(), TP -> TP.getSpeed()));
		panel.add(center, BorderLayout.CENTER);
		panel.add(new BottomBar(), BorderLayout.SOUTH);
		add(panel);
	}
}
