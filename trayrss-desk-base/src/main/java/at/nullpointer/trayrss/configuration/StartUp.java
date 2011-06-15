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

import at.nullpointer.trayrss.TrayRSS;
import at.nullpointer.trayrss.gui.configframe.ConfigFrameCaptions;
import at.nullpointer.trayrss.gui.tray.TrayIconPOJO;
import at.nullpointer.trayrss.monitor.Monitor;
import at.nullpointer.trayrss.monitor.TrayNotifier;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Prozesses all initial loadings
 * 
 * @author thefake
 * 
 */
public class StartUp {
	Logger log = Logger.getLogger(StartUp.class);
	Properties props = new Properties();
	Properties langprops = new Properties();
	boolean debug = false;

	/**
	 * Prozesses all initial loadings
	 * 
	 * @param debug
	 *            switches the logger to debug mode
	 */
	public StartUp(boolean debug) {
		this.debug = debug;
		//TODO debug into RC
		loadProps();
		loadInitialProperties();
		setCaptions();
		startTray();
		startDatabase();
		startMonitor();
		log.info("Startup complete.");
	}

	private void startDatabase() {
		long start = 0;
		if (debug) {
			start = System.currentTimeMillis();
			log.debug("Startup: Start Database at " + start);
		}

		ReferenceCollection.SESSION_FACTORY = new AnnotationConfiguration()
				.configure().buildSessionFactory();

		long end = 0;
		if (debug) {
			end = System.currentTimeMillis();
			log
					.debug("Startup: Finished Start Database at " + end);
		}
	}

	private void loadProps() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		log.debug("Startup: Load Properties at " + start);
		InputStream reader = null;
		try {

			reader = new FileInputStream(ReferenceCollection.CONFIG);

			props = new Properties();
			props.loadFromXML(reader);

			ReferenceCollection.CONFIGURATION = new Configuration(props);
		} catch (FileNotFoundException e) {
			log.error("No config file found! - "
					+ "\n Please reinstall the application!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				log.error("Error closing RSS Stream.");
			}
		}
		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		log.debug("Startup: Finished Load Properites at "
				+ end);
	}

	private void setCaptions() {

		log.debug("Startup: Load Languagefile at "
				+ System.currentTimeMillis());
		InputStream reader = null;

		String languagefile = ReferenceCollection.EN_LANG;

		if (ReferenceCollection.LANGUAGE.equals(ReferenceCollection.DE))
			languagefile = ReferenceCollection.DE_LANG;

		try {

			reader = TrayRSS.class.getResourceAsStream(languagefile
					.substring(1));

			if (ReferenceCollection.TRAYRSS_APP_TITLE.equals("TrayRSS null"))
				reader = new FileInputStream(languagefile);

			langprops = new Properties();
			langprops.loadFromXML(reader);
		} catch (FileNotFoundException e) {
			log.error("No config file found! - "
					+ "\n Please reinstall the application!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				log.error("Error closing RSS Stream.");
			}
		}

		log.debug("Startup: Set Captions at "
				+ System.currentTimeMillis());

		ReferenceCollection.TRAYMENU_EXIT = langprops.getProperty("trayrss."
				+ ReferenceCollection.LANGUAGE + ".traymenu_exit");
		ReferenceCollection.TRAYMENU_MONITOR = langprops.getProperty("trayrss."
				+ ReferenceCollection.LANGUAGE + ".traymenu_monitor");
		ReferenceCollection.TRAYMENU_CONFIG = langprops.getProperty("trayrss."
				+ ReferenceCollection.LANGUAGE + ".traymenu_config");
		ReferenceCollection.TRAYMENU_HELP = langprops.getProperty("trayrss."
				+ ReferenceCollection.LANGUAGE + ".traymenu_help");
		ReferenceCollection.HELP_TITLE = langprops.getProperty("trayrss."
				+ ReferenceCollection.LANGUAGE + ".help_title");
		ReferenceCollection.HELP_OK = langprops.getProperty("trayrss."
				+ ReferenceCollection.LANGUAGE + ".help_ok");

		ConfigFrameCaptions.load(langprops);

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		log.debug("Startup: Finished Set Captions at "
				+ end);

	}

	private void loadInitialProperties() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		log.debug("Startup: Set Language at " + start);

		ReferenceCollection.CONFIGURATION.load();

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		log.debug("Startup: Finished Set Language at "
				+ end);

	}

	private void startMonitor() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		log.debug("Startup: Start Monitor at " + start);

		ReferenceCollection.TRAYNOTIFIER = new TrayNotifier();
		new Thread(ReferenceCollection.TRAYNOTIFIER).start();

		ReferenceCollection.MONITOR = new Monitor();

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		log.debug("Startup: Finished Start Monitor at "
				+ end);
	}

	private void startTray() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		log.debug("Startup: Start Tray at " + start);

		TrayIconPOJO trayIconPOJO = new TrayIconPOJO();
		trayIconPOJO.startTrayIcon();

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		log.debug("Startup: Finished Start Tray at " + end);
	}
}
