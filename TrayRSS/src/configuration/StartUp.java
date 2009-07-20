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

import gui.tray.ConfigFrameCaptions;
import gui.tray.TrayIconPOJO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import monitor.Monitor;

import org.apache.log4j.FileAppender;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

/**
 * Prozesses all initial loadings
 * 
 * @author thefake
 * 
 */
public class StartUp {
	Logger log;
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
		startLogger(debug);
		loadProps();
		loadLanguage();
		setCaptions();
		startTray();
		startDatabase();
		startMonitor();
		ReferenceCollection.LOG.info("Startup complete.");
	}

	private void startDatabase() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Start Database at " + start);
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();
		ReferenceCollection.SESSION_FACTORY = sessionFactory;

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Finished Start Database at "
				+ end);
	}

	private void loadProps() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Load Properties at " + start);
		InputStream reader = null;
		try {
			reader = new FileInputStream(ReferenceCollection.CONFIG);

			props = new Properties();
			props.loadFromXML(reader);
		} catch (FileNotFoundException e) {
			ReferenceCollection.LOG.error("No config file found! - "
					+ "\n Please reinstall the application!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Finished Load Properites at "
				+ end);
	}

	private void setCaptions() {
		
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		
		ReferenceCollection.LOG.debug("Startup: Load Languagefile at " + System.currentTimeMillis());
		InputStream reader = null;
		
		String languagefile = ReferenceCollection.EN_LANG;
		
		if(ReferenceCollection.LANGUAGE.equals(ReferenceCollection.DE)) languagefile = ReferenceCollection.DE_LANG;
		
		try {
			reader = new FileInputStream(languagefile);

			langprops = new Properties();
			langprops.loadFromXML(reader);
		} catch (FileNotFoundException e) {
			ReferenceCollection.LOG.error("No config file found! - "
					+ "\n Please reinstall the application!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
		
		
		
		
		ReferenceCollection.LOG.debug("Startup: Set Captions at " + System.currentTimeMillis());

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
		ReferenceCollection.LOG.debug("Startup: Finished Set Captions at "
				+ end);

	}

	private void loadLanguage() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Set Language at " + start);

		ReferenceCollection.LANGUAGE = props.getProperty("trayrss.lang");

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Finished Set Language at "
				+ end);

	}

	private void startMonitor() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Start Monitor at " + start);

		Thread monitor = new Thread(new Monitor());
		monitor.setName("Monitor");
		ReferenceCollection.MONITOR_THREAD = monitor;
		monitor.start();

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Finished Start Monitor at "
				+ end);
	}

	private void startTray() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Start Tray at " + start);

		TrayIconPOJO trayIconPOJO = new TrayIconPOJO();
		trayIconPOJO.startTrayIcon();

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Finished Start Tray at " + end);
	}

	private void startLogger(boolean debug) {
		log = Logger.getRootLogger();

		ReferenceCollection.LOG = log;

		String pattern = "%d{MM/dd/yyyy HH:mm:ss,SSSS}: %m %n";
		PatternLayout layout = new PatternLayout(pattern);
		ConsoleAppender consoleAppender = new ConsoleAppender(layout);
		log.addAppender(consoleAppender);
		FileAppender fileAppender;
		try {
			fileAppender = new FileAppender(layout, "logs/TrayRSS.log", false);
			log.addAppender(fileAppender);
			// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
			log.setLevel(ReferenceCollection.LOG_LEVEL);
			if (debug)
				log.setLevel(ReferenceCollection.LOG_LEVEL_DEBUG);
		} catch (IOException e) {
			System.err.println("Logdatei kann nicht geöffnet werden!");
		}

		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		ReferenceCollection.LOG.debug("Startup: Finished Start Database at "
				+ end);
	}
}
