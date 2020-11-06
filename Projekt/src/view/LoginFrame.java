package view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Session;
import model.User;
import model.UserManager;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout = new CardLayout();
	private JPanel mainPanel, loginPanel, createPanel;
	private JLabel userNameLabel, passWordLabel, createUserLabel, createPassLabel;
	private JTextField userInput, createPassInput, createUserInput;
	private JPasswordField passInput;
	private JButton loginButton, createButton, createUser, cancelCreation;
	final static String LOGINPANEL = "Login Panel";
	final static String CREATEPANEL = "Create Panel";
	public LoginFrame() {
		super("Logga in");
		setSize(350, 175);
		setVisible(true);
		setLocationRelativeTo(null);
		
		mainPanel = new JPanel(cardLayout);
		mainPanel.setBackground(Color.WHITE);
		
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBackground(Color.white);
		
		createPanel = new JPanel();
		createPanel.setLayout(null);
		createPanel.setBackground(Color.white);
		
		userNameLabel = new JLabel();
		userNameLabel.setBounds(10, 20, 90, 25);
		userNameLabel.setText("Anv�ndarnamn:");
		
		userInput = new JTextField(15);
		userInput.setBounds(110,20,165,25); 
		userInput.setBackground(Color.LIGHT_GRAY);
		
		passWordLabel = new JLabel();
		passWordLabel.setBounds(10,50,90,25);
		passWordLabel.setText("L�senord:");
		
		passInput = new JPasswordField(15);
		passInput.setBounds(110,50,165,25);  
		passInput.setBackground(Color.LIGHT_GRAY);
		
		loginButton = new JButton("Logga in");
		loginButton.setBounds(10,80,100,25);
		loginButton.setBackground(Color.LIGHT_GRAY);
		
		createButton = new JButton("Skapa anv�ndare");
		createButton.setBounds(10, 80, 155, 25);
		createButton.setBackground(Color.LIGHT_GRAY);
		
		createUserLabel = new JLabel();
		createUserLabel.setBounds(10, 20, 90, 25);
		createUserLabel.setText("Anv�ndarnamn:");
		
		createUserInput = new JTextField(15);
		createUserInput.setBounds(110,20,165,25); 
		createUserInput.setBackground(Color.LIGHT_GRAY);

		createPassLabel = new JLabel();
		createPassLabel.setBounds(10,50,90,25);
		createPassLabel.setText("L�senord:");
		
		createPassInput = new JTextField(15);
		createPassInput.setBounds(110, 50, 165, 25);
		createPassInput.setBackground(Color.LIGHT_GRAY);
		
		createUser = new JButton("Skapa ny anv�ndare");
		createUser.setBounds(119,80,155,25);
		createUser.setBackground(Color.LIGHT_GRAY);
		
		cancelCreation = new JButton("Avbryt");
		cancelCreation.setBounds(174,80,80,25);
		cancelCreation.setBackground(Color.LIGHT_GRAY);
		
		loginPanel.add(userNameLabel);
		loginPanel.add(userInput);
		loginPanel.add(passWordLabel);
		loginPanel.add(passInput);
		loginPanel.add(loginButton);
		loginPanel.add(createUser);

		createPanel.add(createUserLabel);
		createPanel.add(createUserInput);
		createPanel.add(createPassLabel);
		createPanel.add(createPassInput);
		createPanel.add(createButton);
		createPanel.add(cancelCreation);
		
		mainPanel.add(loginPanel, LOGINPANEL);
		mainPanel.add(createPanel, CREATEPANEL);
		
		add(mainPanel);
		for(User u : UserManager.getInstance().getAllUsers()) {
			System.out.println(u.getUserName() + ":" + u.getPassWord() + u.getUserAM().getActivities());
		}
		loginButton.addActionListener(e -> onLogin());
		createUser.addActionListener(e -> {
			cardLayout.next(mainPanel);
			this.setTitle("Skapa anv�ndare");
		});
		cancelCreation.addActionListener(e -> {
			cardLayout.next(mainPanel); 
			this.setTitle("Logga in");
		});
		createButton.addActionListener(e -> onCreate());
	}
	
	public void onCreate() {
		String name = "";
		String pass = "";
		boolean corrUser = false;
		boolean corrPass = false;
		String regex = "[a-zA-Z0-9]{4,8}";
		String passRegex = "^(?=.*\\d).{4,8}$";
		boolean flag = false;
		try {
			name = createUserInput.getText();
			pass = createPassInput.getText();
			if(name.matches(regex)) {
				for(User u : UserManager.getInstance().getAllUsers()) {
					if(u.getUserName().equalsIgnoreCase(name)) {
						flag = true;
					}
				}
				if(flag == true)
					JOptionPane.showMessageDialog(this, "En anv�ndare med det h�r anv�ndarnamnet existerar redan.");
				else {
					corrUser = true;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Ditt anv�ndarnamn m�ste vara mellan 4 och 8 symboler l�ngt och f�r inte inneh�lla annat �n bokst�ver och nummer");
			}
			if(pass.matches(passRegex)) 
				corrPass = true;
			else 
				JOptionPane.showMessageDialog(this, "Ditt l�senord m�ste vara mellan 4 och 8 symboler l�ngt och m�ste inneh�lla minst en siffra.");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		if(corrUser && corrPass) {
			UserManager.getInstance().addUser(name, pass);
			cardLayout.next(mainPanel);
			JOptionPane.showMessageDialog(this, "Anv�ndare skapad.");
		}
	}
	
	public void onLogin() {
		String name = "";
		String pass = "";
		boolean corrUser = false;
		boolean corrPass = false;
		try {
			name = userInput.getText();
			pass = String.valueOf(passInput.getPassword());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		for(User u : UserManager.getInstance().getAllUsers()) {
			if(u.getUserName().equalsIgnoreCase(name)){
				corrUser = true;
				if(pass.equals(u.getPassWord())) {
					corrPass = true;
				}
			}
		}
		if(corrUser != true)
			JOptionPane.showMessageDialog(this, "Anv�ndare finns ej.");
		if(corrPass != true) {
			JOptionPane.showMessageDialog(this, "Fel l�senord, f�rs�k igen.");
		}
		
		if(corrUser && corrPass) {
			Session.getInstance().setUser(UserManager.getInstance().getUser(name));
			new MainGUI();
			this.dispose();
		}
	}
}