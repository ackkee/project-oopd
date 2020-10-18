package view;


import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.UserManager;






public class MainGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private UserManager um = UserManager.getInstance();
	public MainGUI() {
		setTitle("Gizmo 2020");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		JButton button = new JButton("Click me");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(button, BorderLayout.SOUTH);
		add(panel);
		um.addUser("Axel", "asd");
		System.out.println(um.getAllUsers());
	}
}
