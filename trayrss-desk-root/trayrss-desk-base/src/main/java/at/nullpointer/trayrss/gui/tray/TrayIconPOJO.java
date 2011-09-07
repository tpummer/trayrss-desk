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
import at.nullpointer.trayrss.monitor.IconChanger;

import java.awt.*;



/**
 * Prepares the TrayIcon
 * 
 * @author thefake
 *
 */
public class TrayIconPOJO {
	private TrayIcon trayIcon;

	public TrayIconPOJO() {
		super();
	}

	public TrayIcon getTrayIcon() {
		return trayIcon;
	}

	public void setTrayIcon(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}
	
	public void startTrayIcon() {
		if (SystemTray.isSupported()) {
			SystemTray tray=SystemTray.getSystemTray();

			trayIcon = IconChanger.createTrayIcon(new TrayMenu());

			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(new TrayIconActionListener());
			trayIcon.addMouseListener(new TrayIconMouseListener());
			
			ReferenceCollection.TRAY_ICON = trayIcon;

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println("TrayIcon could not be added.");
			}
		}

		else {
			System.err.println("Systray not supported!");
			System.exit(1);
		}
	}

	public void refreshTrayIcon() {
		if (SystemTray.isSupported()) {
			SystemTray tray=SystemTray.getSystemTray();
			
			tray.remove(ReferenceCollection.TRAY_ICON);
			
			startTrayIcon();
		}
		
	}

}
