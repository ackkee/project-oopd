package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public LoginFrame() {
		super("Login");
		setSize(300, 300);
		setVisible(true);
		JPanel panel = new JPanel(new GridLayout(3,2));
		panel.add(new JLabel("Username: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Password: "));
		panel.add(new JPasswordField());
		panel.add(new JButton("Logga in"));
		add(panel);
		
	}
}
