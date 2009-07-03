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

import gui.tray.TrayIconPOJO;

import java.io.IOException;

import monitor.Monitor;

import org.apache.log4j.FileAppender;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;

/**
 * Prozesses all initial loadings
 * 
 * @author thefake
 *
 */
public class StartUp {
	Logger log;
	
	/**
	 * Prozesses all initial loadings
	 * 
	 * @param debug switches the logger to debug mode
	 */
	public StartUp(boolean debug){
		startLogger(debug);
		startTray();
		startMonitor();
		ReferenceCollection.log.info("Startup complete.");
	}
	
	private void startMonitor() {
		Thread monitor = new Thread(new Monitor());
		monitor.setName("Monitor");
		ReferenceCollection.MONITOR_THREAD = monitor;
		monitor.start();
	}

	private void startTray() {
		TrayIconPOJO trayIconPOJO = new TrayIconPOJO();
		trayIconPOJO.startTrayIcon();
	}

	private void startLogger(boolean debug){
		log = Logger.getRootLogger();
		
		ReferenceCollection.log = log;
		
		String pattern = "%d{MM/dd/yyyy HH:mm:ss,SSSS}: %m %n";
		PatternLayout layout = new PatternLayout(pattern);
		ConsoleAppender consoleAppender = new ConsoleAppender(layout);
		log.addAppender(consoleAppender);
		FileAppender fileAppender;
		try {
			fileAppender = new FileAppender(layout,
					"logs/TrayRSS.log", false);
			log.addAppender(fileAppender);
			// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
			log.setLevel(ReferenceCollection.LOG_LEVEL);
			if(debug) log.setLevel(ReferenceCollection.LOG_LEVEL_DEBUG);
		} catch (IOException e) {
			System.err.println("Logdatei kann nicht geöffnet werden!");
		}
	}
}
