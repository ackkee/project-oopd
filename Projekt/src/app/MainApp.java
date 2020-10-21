package app;

import javax.swing.SwingUtilities;

import view.LoginFrame;

public class MainApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new LoginFrame());
	}
}
