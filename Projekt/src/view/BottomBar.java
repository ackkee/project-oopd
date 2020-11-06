package view;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Session;
import model.Activity;
import model.UserManager;
import observer.Observer;

public class BottomBar extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private JButton importButton, selectButton, removeButton, switchCard, aboutButton, adminButton;
	private static JComboBox<String> list;

	public BottomBar() {
		Session.getInstance().addSubscriber(this);
		list = new JComboBox<String>();
		updateList();
		importButton = new JButton("Importera aktivitet");
		selectButton = new JButton("Välj aktivitet");
		removeButton = new JButton("Ta bort aktivitet");
		switchCard = new JButton("Byt vy");
		aboutButton = new JButton("Om dig");
		adminButton = new JButton("Administrator-vy");
		this.add(importButton);
		this.add(selectButton);
		this.add(removeButton);
		this.add(switchCard);
		this.add(aboutButton);
		if(Session.getInstance().getUser().getUserName().equalsIgnoreCase("Admin"))
			this.add(adminButton);
		importButton.addActionListener(e -> importActivity());
		selectButton.addActionListener(e -> changeActivity());
		removeButton.addActionListener(e -> removeActivity());
		switchCard.addActionListener(e -> MainGUI.swapCard());
		aboutButton.addActionListener(e -> new UserData());
		adminButton.addActionListener(e -> new AdminFrame());
	}

	public void importActivity() {
		try {
			JFileChooser file = new JFileChooser("C:\\Users\\axell\\git\\project-oopd\\Projekt\\Data\\csv\\csv");
			file.setFileSelectionMode(JFileChooser.FILES_ONLY);
			file.showOpenDialog(null);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "csv");
			file.setFileFilter(filter);
			if(file.getSelectedFile().toString().endsWith(".csv")){
				Session.getInstance().getUser().getUserAM().addActivity(file.getSelectedFile(),
					file.getSelectedFile().getName());
			} else file.changeToParentDirectory();
		} catch (NullPointerException e) {}
		updateList();
	}

	public void changeActivity() {
		if(list.getSelectedItem() != null) {
			try {
				int test = JOptionPane.showConfirmDialog(getRootPane(), list, "Välj aktivitet", JOptionPane.OK_OPTION);
				if(test == 0) {
					String s = (String) list.getSelectedItem();
					Session.getInstance().setCurrActivity(Session.getInstance().getUser().getUserAM().getActivity(s));
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		else 
			JOptionPane.showMessageDialog(getRootPane(), "Det finns inga aktiviteter.");
		updateList();
	}
	
	public void removeActivity() {
		if(list.getSelectedItem() != null) {
			int test = JOptionPane.showConfirmDialog(getRootPane(), list, "Ta bort aktivitet", JOptionPane.OK_CANCEL_OPTION);
			if(test == 0) {
				int sure = JOptionPane.showConfirmDialog(getRootPane(), "Vill du verkligen ta bort " + list.getSelectedItem() + "?", "Ta bort", JOptionPane.YES_NO_OPTION);
				if(sure == 0) {
					String s = (String) list.getSelectedItem();
					Session.getInstance().getUser().getUserAM().removeActivity(s);
					Session.getInstance().setCurrActivity(null);
					list.removeItem(s);
					Session.getInstance().notifySubscribers();
				}
			}
		} else 
			JOptionPane.showMessageDialog(getRootPane(), "Det finns inga aktiviteter.");
		updateList();
	}
	
	public static void updateList() {
		list.removeAllItems();
		for (Activity a : Session.getInstance().getUser().getUserAM().getActivities()) {
			list.addItem(a.getName());
		}
		UserManager.getInstance().storeUsers();
	}

	@Override
	public void update() {
		MainGUI.getActivityCardCenter().removeAll();
		MainGUI.getActivityCardCenter().revalidate();
		if(Session.getInstance().getCurrActivity() != null) {
			MainGUI.getActivityCardCenter().add(new PlotGraph("HR", TP -> TP.getHRate()));
			MainGUI.getActivityCardCenter().add(new PlotGraph("Alt", TP -> TP.getAlt()));
			MainGUI.getActivityCardCenter().add(new PlotGraph("Speed", TP -> TP.getSpeed()));
		}
		MainGUI.getActivityCardCenter().repaint();
	}

}
