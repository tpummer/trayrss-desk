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
package at.nullpointer.trayrss.configuration;


import java.awt.TrayIcon;

import at.nullpointer.trayrss.gui.tray.Help;

/**
 * Collection of all references
 * TODO make class unused
 * 
 * @author thefake
 *
 */
public class ReferenceCollection {
	
	
	public static String TRAYRSS_APP_TITLE;
	
	public static final String ICON_NORMAL = "./img/icons/rss-icon.png";
	public static final String ICON_HELP = "./img/trayrss.png";
	
	public static TrayIcon TRAY_ICON;
	public static Help HELP_WINDOW;
	
}
