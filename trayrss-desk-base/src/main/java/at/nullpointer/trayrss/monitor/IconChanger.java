/**
    TrayRSS - simply alerting at new Feed Information
	visit the project at http://trayrss.nullpointer.at/

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
package at.nullpointer.trayrss.monitor;

import at.nullpointer.trayrss.TrayRSS;
import at.nullpointer.trayrss.configuration.ReferenceCollection;

import java.awt.*;


/**
 * Controller to manage the TrayIcon and its possible states
 * 
 * @author thefake
 *
 */
public class IconChanger {
	
	/**
	 * Changes the Image of a TrayIcon
	 * 
	 * @param trayIcon Reference of the TrayIcon
	 * @param icon Location of the Icon
	 */
	public static void setIcon(TrayIcon trayIcon, String icon){
		

		
		Image image = Toolkit.getDefaultToolkit().getImage(
				 TrayRSS.class.getResource(icon.substring(1)));
		
		
		if (ReferenceCollection.TRAYRSS_APP_TITLE.equals("TrayRSS null")) image = Toolkit.getDefaultToolkit().getImage("."+
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
				 TrayRSS.class.getResource(ReferenceCollection.ICON_NORMAL.substring(1)));
		
		
		if (ReferenceCollection.TRAYRSS_APP_TITLE.equals("TrayRSS null")) image = Toolkit.getDefaultToolkit().getImage("."+
				ReferenceCollection.ICON_NORMAL);
		return new TrayIcon(image, ReferenceCollection.TRAYRSS_APP_TITLE, popup);
	}

}
