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
package monitor;

import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;

/**
 * Controller to manage the TrayIcon and its possible states
 * 
 * @author thefake
 *
 */
public class IconChanger {
	
	public static final String NORMALICON = "./img/icons/rssTrayIcon.PNG";
	public static final String WARNICON = "./img/icons/rssTrayIconWarn.PNG";
	public static final String NEWICON = "./img/icons/rssTrayIconWarn.PNG";
	
	/**
	 * Changes the Image of a TrayIcon
	 * 
	 * @param trayIcon Reference of the TrayIcon
	 * @param icon Location of the Icon
	 */
	public static void setIcon(TrayIcon trayIcon, String icon){
		
		// Image image = Toolkit.getDefaultToolkit().getImage(
		// getClass().getResource("/img/rsstrayicon.PNG"));
		
		Image image = Toolkit.getDefaultToolkit().getImage(
				icon);
		
		trayIcon.setImage(image);
	}

	/**
	 * Creates a new TrayIcon
	 * 
	 * @param popup PopupMenu for the Tray
	 * @return TrayIcon
	 */
	public static TrayIcon createTrayIcon(PopupMenu popup) {
		Image image = Toolkit.getDefaultToolkit().getImage(
				NORMALICON);
		return new TrayIcon(image, "TrayRSS 0.4 Alpha", popup);
	}

}
