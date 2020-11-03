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

public class BottomBar extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton importButton, selectButton, removeButton;
	private JComboBox<String> list;

	public BottomBar() {
		importButton = new JButton("Import activity");
		selectButton = new JButton("Select activity");
		removeButton = new JButton("Remove activity");
		this.add(importButton);
		this.add(selectButton);
		this.add(removeButton);
		importButton.addActionListener(e -> importActivity());
		selectButton.addActionListener(e -> changeActivity());
		removeButton.addActionListener(e -> removeActivity());
		list = new JComboBox<String>();
		for (Activity a : Session.getInstance().getUser().getUserAM().getActivities()) {
			list.addItem(a.getName());
		}
	}

	public void importActivity() {
		try {
			JFileChooser file = new JFileChooser("C:\\Users\\axell\\git\\project-oopd\\Projekt");
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
			JOptionPane.showMessageDialog(getRootPane(), list);
			String s = (String) list.getSelectedItem();
			Session.getInstance().setCurrActivity(Session.getInstance().getUser().getUserAM().getActivity(s));
		}
		else 
			JOptionPane.showMessageDialog(getRootPane(), "Det finns inga aktiviteter.");
		updateList();
		MainGUI.update();
	}
	
	public void removeActivity() {
		if(list.getSelectedItem() != null) {
			int test = JOptionPane.showConfirmDialog(getRootPane(), list, "Ta bort aktivitet", JOptionPane.OK_CANCEL_OPTION);
			if(test == 0) {
				int sure = JOptionPane.showConfirmDialog(getRootPane(), "Vill du verkligen ta bort " + list.getSelectedItem() + "?", "Ta bort", JOptionPane.YES_NO_OPTION);
				if(sure == 0) {
					String s = (String) list.getSelectedItem();
					Session.getInstance().getUser().getUserAM().removeActivity(s);
					list.removeItem(s);
				}
			}
		} else 
			JOptionPane.showMessageDialog(getRootPane(), "Det finns inga aktiviteter.");
		updateList();
	}
	
	public void updateList() {
		list.removeAllItems();
		for (Activity a : Session.getInstance().getUser().getUserAM().getActivities()) {
			list.addItem(a.getName());
		}
		getRootPane().repaint();
		UserManager.getInstance().storeUsers();
	}	
}
