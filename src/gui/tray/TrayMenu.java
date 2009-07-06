package gui.tray;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import configuration.ReferenceCollection;

public class TrayMenu extends PopupMenu {

	public TrayMenu() {


		MenuItem monitorItem = new MenuItem(ReferenceCollection.TRAYMENU_MONITOR);
		this.add(monitorItem);
		MenuItem configItem = new MenuItem(ReferenceCollection.TRAYMENU_CONFIG);
		this.add(configItem);
		MenuItem helpItem = new MenuItem(ReferenceCollection.TRAYMENU_HELP);
		this.add(helpItem);

		MenuItem exitItem = new MenuItem(ReferenceCollection.TRAYMENU_EXIT);
		this.add(exitItem);
		
		this.addActionListener(new TrayMenuActionListener());

	}
}