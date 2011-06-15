/*
    TrayRSS - simply notification of feed information
    (c) 2009-2011 TrayRSS Developement Team
    visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
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