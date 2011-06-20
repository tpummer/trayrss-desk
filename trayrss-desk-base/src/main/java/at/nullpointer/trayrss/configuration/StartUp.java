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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.cfg.AnnotationConfiguration;

import at.nullpointer.trayrss.ConfigurationConstants;
import at.nullpointer.trayrss.TrayRSS;
import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.configuration.model.LanguageShortcut;
import at.nullpointer.trayrss.configuration.timeframes.Timeframe;
import at.nullpointer.trayrss.gui.tray.TrayIconPOJO;
import at.nullpointer.trayrss.monitor.Monitor;
import at.nullpointer.trayrss.monitor.TrayNotifier;

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
		//TODO read Config
		ReferenceCollection.CONFIGURATION = ConfigurationControllerImpl.getInstance(debug);
		ReferenceCollection.CONFIGURATION.load();
		ConfigurationModel configModel = ReferenceCollection.CONFIGURATION.getConfigurationModel();
		setCaptions(configModel.getLanguage());
		startTray();
		startDatabase();
		startMonitor();
		log.info("Startup complete.");
	}

	/**
	 * <p>Initializes the {@link ConfigurationModel}</p>
	 */
	private ConfigurationModel loadInitialProperties() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		log.debug("Startup: Set Language at " + start);
	
		//TODO Config laden
		ConfigurationModel configModel = new ConfigurationModel();
		
		//general
		configModel.setLanguage(LanguageShortcut.valueOf(props.getProperty(ConfigurationConstants.LANGUAGE).toUpperCase()));
		configModel.setDisplayTime(Integer.valueOf(props.getProperty(ConfigurationConstants.DISPLAYSECOND)));
		configModel.setDisplayCount(Integer.valueOf(props.getProperty(ConfigurationConstants.DISPLAYCOUNT)));
		
		//timerestriction
		configModel.setIsTimeFrameActivated(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIMERESTRICTION)));
		//TODO configModel.setTimeFrames(timeFrames)
		//props.getProperty(ConfigurationConstants.TIMEFRAME);
		configModel.setIsMondayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_MO)));
		configModel.setIsTuesdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_TU)));
		configModel.setIsWednesdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_WE)));
		configModel.setIsThursdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_TH)));
		configModel.setIsFridayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_FR)));
		configModel.setIsSaturdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_SA)));
		configModel.setIsSundayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_SU)));
		
		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		log.debug("Startup: Finished Set Language at "
				+ end);
		
		return configModel;
	
	}

	
	/**
	 * <p>preloads the captions of the tray menu</p>
	 * 
	 * @param lang of type LanguageShortcut
	 */
	private void setCaptions(LanguageShortcut lang) {
	
		log.debug("Startup: Load Languagefile at "
				+ System.currentTimeMillis());
		InputStream reader = null;
	
		//TODO Sprache auf neues Konzept umstellen
		String languagefile = ReferenceCollection.EN_LANG;
	
		if (lang.getShortcut().equals(ReferenceCollection.DE))
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
		
		String language_short = lang.getShortcut(); 
	
		ReferenceCollection.TRAYMENU_EXIT = langprops.getProperty("trayrss."
				+ language_short + ".traymenu_exit");
		ReferenceCollection.TRAYMENU_MONITOR = langprops.getProperty("trayrss."
				+ language_short + ".traymenu_monitor");
		ReferenceCollection.TRAYMENU_CONFIG = langprops.getProperty("trayrss."
				+ language_short + ".traymenu_config");
		ReferenceCollection.TRAYMENU_HELP = langprops.getProperty("trayrss."
				+ language_short + ".traymenu_help");
		ReferenceCollection.HELP_TITLE = langprops.getProperty("trayrss."
				+ language_short + ".help_title");
		ReferenceCollection.HELP_OK = langprops.getProperty("trayrss."
				+ language_short + ".help_ok");
	
		long end = 0;
		if (debug)
			end = System.currentTimeMillis();
		log.debug("Startup: Finished Set Captions at "
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
}
