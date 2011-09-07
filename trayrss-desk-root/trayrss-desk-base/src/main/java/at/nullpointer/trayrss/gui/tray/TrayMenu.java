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

import java.awt.MenuItem;
import java.awt.PopupMenu;

import at.nullpointer.trayrss.messages.Messages;


public class TrayMenu extends PopupMenu {

	private static final long serialVersionUID = 1L;

	public TrayMenu() {


		MenuItem monitorItem = new MenuItem(Messages.getMessageResolver(Messages.GUI).getString("traymenu.command.monitor", "Check Feeds manually"));
		this.add(monitorItem);
		MenuItem configItem = new MenuItem(Messages.getMessageResolver(Messages.GUI).getString("traymenu.command.config", "Configuration"));
		this.add(configItem);
		MenuItem helpItem = new MenuItem(Messages.getMessageResolver(Messages.GUI).getString("traymenu.command.help", "Help"));
		this.add(helpItem);

		MenuItem exitItem = new MenuItem(Messages.getMessageResolver(Messages.GUI).getString("traymenu.command.exit", "Exit"));
		this.add(exitItem);
		
		this.addActionListener(new TrayMenuActionListener());

	}
}