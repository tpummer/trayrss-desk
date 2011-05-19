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
package at.nullpointer.trayrss.configuration;


import at.nullpointer.trayrss.configuration.feeds.FeedTable;
import at.nullpointer.trayrss.gui.configframe.ConfigFrame;
import at.nullpointer.trayrss.gui.tray.Help;
import at.nullpointer.trayrss.monitor.Monitor;
import at.nullpointer.trayrss.monitor.TrayNotifier;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import java.awt.*;

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
	public static Configuration CONFIGURATION;
	public static final String CONFIG = "./config.ini";
	public static final String DE = "de";
	public static final String DE_LANG = "./lang/de.XML";
	public static final String EN = "en";
    public static final String EN_LANG = "./lang/en.XML";
	public static String LANGUAGE;
	public static int DISPLAY_COUNT;
	public static int DISPLAY_SECONDS;
	
	/*
	 * Captions
	 */
	
	public static String TRAYRSS_APP_TITLE;

	
	/*
	 * Icons
	 */
	
	public static final String ICON_NORMAL = "./img/icons/rssTrayIcon.PNG";
	public static final String ICON_WARN = "./img/icons/rssTrayIconWarn.PNG";
	public static final String ICON_NEW = "./img/icons/rssTrayIconWarn.PNG";
	
	public static final String ICON_HELP = "./img/rssTrayHelp.PNG";
	
	/*
	 * Logger
	 */
	
	//public static Logger LOG;
	// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
	public static final Level LOG_LEVEL = Level.INFO;
	public static final Level LOG_LEVEL_DEBUG = Level.DEBUG;
	
	/*
	 * Notification Messages
	 */
	public static final String notification_read_error_uri = "Error reading URL";
	
	/*
	 * Tray
	 */
	
	public static TrayIcon TRAY_ICON;
	public static String TRAYMENU_EXIT = "Exit";
	public static String TRAYMENU_MONITOR = "Check Feeds manually";
	public static String TRAYMENU_CONFIG = "Configuration";
	public static String TRAYMENU_HELP = "Help";

	/*
	 * Monitor
	 */
	public static Monitor MONITOR;
	public static SessionFactory SESSION_FACTORY;
	
	/*
	 * Help Window
	 */
	public static String HELP_TITLE = "Help";
	public static Help HELP_WINDOW;
	public static String HELP_OK = "Ok";
	
	/*
	 * Configuration Window
	 */
	public static ConfigFrame CONFIG_WINDOW;
	
	public static String CONFIG_TITLE = "Configuration";
	
	public static String CONFIG_TIMEFRAMESLABEL;
	public static String CONFIG_TIMEFRAMES_VALUE;
	public static String CONFIG_MONITORINGDAYSLABEL;
	public static String CONFIG_MONITORINGDAYSMO;
	public static String CONFIG_MONITORINGDAYSMO_VALUE;
	public static String CONFIG_MONITORINGDAYSWE;
	public static String CONFIG_MONITORINGDAYSWE_VALUE;
	public static String CONFIG_MONITORINGDAYSFR;
	public static String CONFIG_MONITORINGDAYSFR_VALUE;
	public static String CONFIG_MONITORINGDAYSSA;
	public static String CONFIG_MONITORINGDAYSSA_VALUE;
	public static String CONFIG_MONITORINGDAYSTH;
	public static String CONFIG_MONITORINGDAYSTH_VALUE;
	public static String CONFIG_MONITORINGDAYSTU;
	public static String CONFIG_MONITORINGDAYSTU_VALUE;
	public static String CONFIG_MONITORINGDAYSSU;
	public static String CONFIG_MONITORINGDAYSSU_VALUE;
	public static String CONFIG_VACATIONSTARTLABEL;
	public static String CONFIG_VACATIONLABEL;
	public static String CONFIG_VACATIONENDLABEL;
	public static String CONFIG_VACATION_START_VALUE;
	public static String CONFIG_VACATION_END_VALUE;
	public static String CONFIG_TIMEFRAMESPANEL_BORDER_TITLE;
	public static String CONFIG_FEEDSPANEL_BORDER_TITLE;
	
	public static String CONFIG_SAVEBUTTON_TEXT;
	public static String CONFIG_ADDBUTTON_TEXT;
	public static String CONFIG_DELETEBUTTON_TEXT;
	public static String CONFIG_CANCELBUTTON_TEXT;
	
	public static String CONFIG_MAINCONFIGPANEL_BORDER_TITLE;
	public static String CONFIG_DISPLAYCOUNTLABEL;
	public static String CONFIG_DISPLAYTIMELABEL;
	public static Object[] CONFIG_TABLE_HEADER;
	public static String CONFIG_LANGUAGESELECTORLABEL;
	public static String CONFIG_LANGUAGESELECTORFIELD;
	
	public static String config_url_valid_title;
	public static String config_url_valid_text;
	public static String config_vacation_valid_title;
	public static String config_vacation_valid_text;
	public static String config_long_valid_text;
	public static String config_long_valid_title;
	public static String config_timeframe_valid_text;
	public static String config_timeframe_valid_title;
	
	public static FeedTable FEED_TABLE;
	public static TrayNotifier TRAYNOTIFIER;
}
