package gui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu {
	
	public Menu(String name) {
		setMenu(name);
	}	
	public JMenu getMenu() {
		return _menu;
	}
	public void addItem(String Iname) {
		this.getMenu().add(new JMenuItem(Iname));
	}

	
	/** private data  */
	private void setMenu(String name) {
		this._menu = new JMenu(name);
	}
	
	private JMenu _menu;
	
}
