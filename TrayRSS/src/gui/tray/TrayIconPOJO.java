/**
    RSSTray - simply alerting at new Feed Information
    Copyright (C) 2009 Thomas Pummer
    conatct me fake (at) sprossenwanne.at

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package gui.tray;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import monitor.IconChanger;

/**
 * Prepares the TrayIcon
 * 
 * @author thefake
 *
 */
public class TrayIconPOJO {
	private SystemTray tray = null;
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
			tray = SystemTray.getSystemTray();

			MouseListener mouseListener = new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse clicked!");
				}

				public void mouseEntered(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse entered!");
				}

				public void mouseExited(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse exited!");
				}

				public void mousePressed(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse pressed!");
				}

				public void mouseReleased(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse released!");
				}
			};

			PopupMenu popup = new PopupMenu();

			ActionListener ReadListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			};

			MenuItem listRss = new MenuItem("List RSS");
			listRss.addActionListener(ReadListener);
			popup.add(listRss);

			ActionListener addListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			};

			MenuItem addRss = new MenuItem("Add RSS");
			addRss.addActionListener(addListener);
			popup.add(addRss);

			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					System.exit(0);
				}
			};

			MenuItem exitItem = new MenuItem("Exit");
			exitItem.addActionListener(exitListener);
			popup.add(exitItem);

			trayIcon = IconChanger.createTrayIcon(popup);

			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					trayIcon.displayMessage("Action Event",
							"An Action Event Has Been Performed!",
							TrayIcon.MessageType.INFO);
				}
			};

			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(actionListener);
			trayIcon.addMouseListener(mouseListener);

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

}
