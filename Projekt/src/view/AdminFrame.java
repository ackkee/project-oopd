package view;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.User;
import model.UserManager;

public class AdminFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> box = new JComboBox<>();
	private JButton removeButton = new JButton("Ta bort användare");
	public AdminFrame() {
		super("Administrator-vy");
		setSize(350,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		box.setBounds(20, 40, 90, 20);
		removeButton.setBounds(120,40, 150, 20);
		JLabel text = new JLabel("Alla användare");
		text.setBounds(20, 10, 90, 20);
		for(User u : UserManager.getInstance().getAllUsers()) {
			if(!u.getUserName().equalsIgnoreCase("Admin"))
				box.addItem(u.getUserName());
		}
		add(text);
		add(box);
		add(removeButton);
		removeButton.addActionListener(e -> removeUser());
	}
	
	public void removeUser() {
		if(box.getSelectedItem() != null) {
			int test = JOptionPane.showConfirmDialog(getRootPane(), box.getItemAt(box.getSelectedIndex()), "Ta bort användare", JOptionPane.OK_CANCEL_OPTION);
			if(test == 0) {
				int sure = JOptionPane.showConfirmDialog(getRootPane(), "Vill du verkligen ta bort användaren " + box.getSelectedItem() + "?", "Ta bort", JOptionPane.YES_NO_OPTION);
				if(sure == 0) {
					String s = (String) box.getSelectedItem();
					UserManager.getInstance().removeUser(s);
					UserManager.getInstance().storeUsers();
					box.removeItem(s);
				}
			}
		} else
			JOptionPane.showMessageDialog(getRootPane(), "Det finns inga användare förutom Admin");
		
	}
}
