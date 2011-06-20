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

import at.nullpointer.trayrss.ConfigurationConstants;
import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.configuration.model.LanguageShortcut;

/**
 * {@docRoot}
 * 
 * @author Thomas Pummer
 * @version 1.0
 *
 */
public class ConfigurationControllerImpl implements ConfigurationController {
	
	Logger log = Logger.getLogger(ConfigurationControllerImpl.class);
	
	private static ConfigurationControllerImpl instance;
	
	//TODO debug to static
	private Boolean debug = false;
	private Properties props;
	
	private ConfigurationModel configModel;
	
	private ConfigurationControllerImpl(Boolean debug){
		this.debug = debug;
	}
	
	/**
	 * Gives the singleton instance of the ConfigurationController
	 * 
	 * @param debug
	 * @return
	 */
	public static synchronized ConfigurationController getInstance(Boolean debug){
		if(instance == null){
			instance = new ConfigurationControllerImpl(debug);
		} else {
			instance.setDebug(debug);
		}
		return instance;
	}

	
	/**
	 * @return the debug
	 */
	public synchronized Boolean getDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public synchronized void setDebug(Boolean debug) {
		this.debug = debug;
	}

	/**
	 * <p>stores the given model to a file</p>
	 * 
	 * @param configurationModel the config to save 
	 */
	@Override
	public synchronized void save(ConfigurationModel configurationModel) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>preloads the configuration from the filesystem</p>
	 */
	@Override
	public synchronized void load() {
		Properties loadProps = loadProps();
		this.configModel = loadInitialProperties(loadProps);

	}

	/**
	 * <p>returns the actual configuration</p>
	 * 
	 * @return configModel
	 */
	@Override
	public synchronized ConfigurationModel getConfigurationModel() {
		if(this.configModel == null){
			loadProps();
		}
		return configModel;
	}
	
	/**
	 * <p>Reads all properties into memory</p>
	 */
	private synchronized Properties loadProps() {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		log.debug("Startup: Load Properties at " + start);
		
		Properties props = null; 
		
		InputStream reader = null;
		try {
	
			reader = new FileInputStream(ConfigurationConstants.CONFIG);
	
			props = new Properties();
			props.loadFromXML(reader);
	
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
		
		return props;
	}

	private synchronized ConfigurationModel loadInitialProperties(Properties props) {
		long start = 0;
		if (debug)
			start = System.currentTimeMillis();
		log.debug("Startup: Set Language at " + start);
	
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
}
