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
package configuration;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Collection of all references
 * 
 * @author thefake
 *
 */
public class ReferenceCollection {
	
	/*
	 * Captions
	 */
	
	public final static String TRAYRSS_APP_TITLE = "TrayRSS 0.4 Alpha";

	
	/*
	 * Icons
	 */
	
	public static final String ICON_NORMAL = "./img/icons/rssTrayIcon.PNG";
	public static final String ICON_WARN = "./img/icons/rssTrayIconWarn.PNG";
	public static final String ICON_NEW = "./img/icons/rssTrayIconWarn.PNG";
	
	/*
	 * Logger
	 */
	
	public static Logger log;
	// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
	public static final Level LOG_LEVEL = Level.INFO;
	public static final Level LOG_LEVEL_DEBUG = Level.DEBUG;
}
