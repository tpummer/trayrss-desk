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

import org.apache.log4j.Level;

import at.nullpointer.trayrss.gui.tray.Help;

/**
 * Collection of all references
 * 
 * @author thefake
 *
 */
public class ReferenceCollection {
	
	/*
	 * Configuration
	 */
	
	public static final String DE = "de";
	public static final String DE_LANG = "./lang/de.XML";
	public static final String EN = "en";
    public static final String EN_LANG = "./lang/en.XML";
	public static String LANGUAGE;
	
	/*
	 * Captions
	 */
	
	public static String TRAYRSS_APP_TITLE;

	
	/*
	 * Icons
	 */
	
	public static final String ICON_NORMAL = "./img/icons/rss-icon.png";
	public static final String ICON_HELP = "./img/trayrss.png";
	
	/*
	 * Logger
	 */
	
	//public static Logger LOG;
	// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
	public static final Level LOG_LEVEL = Level.INFO;
	public static final Level LOG_LEVEL_DEBUG = Level.DEBUG;
	
	/*
	 * Tray
	 */
	
	public static TrayIcon TRAY_ICON;
	public static String TRAYMENU_EXIT = "Exit";
	public static String TRAYMENU_MONITOR = "Check Feeds manually";
	public static String TRAYMENU_CONFIG = "Configuration";
	public static String TRAYMENU_HELP = "Help";
	
	/*
	 * Help Window
	 */
	public static String HELP_TITLE = "Help";
	public static Help HELP_WINDOW;
	public static String HELP_OK = "Ok";
	
}