package at.nullpointer.trayrss.gui.tray;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

import java.awt.*;


public class TrayMenu extends PopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4671463948924491627L;

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