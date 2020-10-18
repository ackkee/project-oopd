package app;

import javax.swing.SwingUtilities;

import view.MainGUI;

public class MainApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainGUI());
	}
}
