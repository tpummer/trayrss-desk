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
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.cfg.AnnotationConfiguration;

import at.nullpointer.trayrss.TrayRSS;
import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.configuration.model.LanguageShortcut;
import at.nullpointer.trayrss.dao.SessionFactoryRepository;
import at.nullpointer.trayrss.gui.tray.TrayIconPOJO;
import at.nullpointer.trayrss.monitor.Monitor;
import at.nullpointer.trayrss.notification.TrayNotifier;

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

	/**
	 * Prozesses all initial loadings
	 * 
	 * @param debug
	 *            switches the logger to debug mode
	 */
	public StartUp(boolean debug) {
		if(debug)
			Logger.getRootLogger().setLevel(ReferenceCollection.LOG_LEVEL_DEBUG);

		startDatabase();
		ConfigurationController configControl = ConfigurationControllerImpl.getInstance();
		configControl.load();
		ConfigurationModel configModel = configControl.getConfigurationModel();
		setCaptions(configModel.getLanguage());
		startTray();
		startMonitor();
		log.info("Startup complete.");
	}

	
	/**
	 * <p>preloads the captions of the tray menu</p>
	 * 
	 * @param lang of type LanguageShortcut
	 */
	private void setCaptions(LanguageShortcut lang) {
	
		log.debug("Startup: Load Captions started");
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

		log.debug("Startup: Finished Set Captions");
	
	}

	private void startTray() {
		log.debug("Startup: Start Tray");
	
		TrayIconPOJO trayIconPOJO = new TrayIconPOJO();
		trayIconPOJO.startTrayIcon();
	
		log.debug("Startup: Finished Start Tray");
	}

	private void startDatabase() {
			log.debug("Startup: Start Database");

		SessionFactoryRepository.setSessionFactory(new AnnotationConfiguration()
				.configure().buildSessionFactory());

		log.debug("Startup: Finished Start Database");

	}

	private void startMonitor() {
		log.debug("Startup: Start Monitor");

		TrayNotifier trayNotifier = new TrayNotifier();
		new Thread(trayNotifier).start();

		Monitor.setTrayNotifier(trayNotifier);
		Monitor monitor = Monitor.getInstance();
		
		Set<ChangeListener> listener = new HashSet<ChangeListener>();
		listener.add(monitor);
		listener.add(trayNotifier);
		ConfigurationControllerImpl.getInstance().setChangeListener(listener);

		log.debug("Startup: Finished Start Monitor");
	}
}
