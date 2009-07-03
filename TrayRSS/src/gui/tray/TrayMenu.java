package gui.tray;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrayMenu extends PopupMenu {

	public TrayMenu() {


		MenuItem listRss = new MenuItem("List RSS");
		this.add(listRss);

		MenuItem addRss = new MenuItem("Add RSS");
		this.add(addRss);

		MenuItem exitItem = new MenuItem("Exit");
		this.add(exitItem);
		
		this.addActionListener(new TrayMenuActionListener());

	}
}