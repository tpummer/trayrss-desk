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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.configuration.model.LanguageShortcut;
import at.nullpointer.trayrss.configuration.timeframes.TimeFrameUtil;
import at.nullpointer.trayrss.error.ErrorListener;

/**
 * {@docRoot}
 * 
 * @author Thomas Pummer
 *
 */
public class ConfigurationControllerImpl implements ConfigurationController {
	
	private Set<ErrorListener> errorListeners;
	
	Logger log = Logger.getLogger(ConfigurationControllerImpl.class);
	
	private static ConfigurationControllerImpl instance;
	
	private Properties props;
	
	private ConfigurationModel configModel;
	
	private ConfigurationControllerImpl(){
	}
	
	/**
	 * Gives the singleton instance of the ConfigurationController
	 * 
	 * @return
	 */
	public static synchronized ConfigurationController getInstance(){
		if(instance == null){
			instance = new ConfigurationControllerImpl();
		}
		return instance;
	}

	/**
	 * <p>stores the given model to a file</p>
	 * 
	 * @param configurationModel the config to save 
	 */
	@Override
	public synchronized void save(ConfigurationModel configurationModel) {
		props.setProperty(ConfigurationConstants.LANGUAGE, configModel.getLanguage().toString());
		props.setProperty(ConfigurationConstants.DISPLAYSECOND, configModel.getDisplayTime().toString());
		props.setProperty(ConfigurationConstants.DISPLAYCOUNT, configModel.getDisplayCount().toString());
		
		//timerestriction
		props.setProperty(ConfigurationConstants.TIMERESTRICTION, configModel.getIsTimeFrameActivated().toString());
		
		props.setProperty(ConfigurationConstants.TIMEFRAME, TimeFrameUtil.singleTimeFrameToString(configModel.getTimeFrames()));

		props.setProperty(ConfigurationConstants.TIME_MO, configModel.getIsMondayEnabled().toString());
		props.setProperty(ConfigurationConstants.TIME_TU, configModel.getIsTuesdayEnabled().toString());
		props.setProperty(ConfigurationConstants.TIME_WE, configModel.getIsWednesdayEnabled().toString());
		props.setProperty(ConfigurationConstants.TIME_TH, configModel.getIsThursdayEnabled().toString());
		props.setProperty(ConfigurationConstants.TIME_FR, configModel.getIsFridayEnabled().toString());
		props.setProperty(ConfigurationConstants.TIME_SA, configModel.getIsSaturdayEnabled().toString());
		props.setProperty(ConfigurationConstants.TIME_SU, configModel.getIsSundayEnabled().toString());
		
		props.setProperty(ConfigurationConstants.VACATION_START, Long.toString(configModel.getVacationStart().getTime()));
		props.setProperty(ConfigurationConstants.VACATION_END, Long.toString(configModel.getVacationEnd().getTime()));
		
		try {
			props.storeToXML(new FileOutputStream(ConfigurationConstants.CONFIG),"TrayRSS Configuration");
		} catch (FileNotFoundException e) {
			for(ErrorListener listener : errorListeners )
				listener.addError("Configuration", "Config file not found");
		} catch (IOException e) {
			for(ErrorListener listener : errorListeners )
				listener.addError("Configuration", "IO Error at saving");
		}

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
		log.debug("Startup: started loading properties file");
		
		Properties props = null; 
		
		InputStream reader = null;
		try {
	
			reader = new FileInputStream(ConfigurationConstants.CONFIG);
	
			props = new Properties();
			props.loadFromXML(reader);
	
		} catch (FileNotFoundException e) {
			String errorMsg = "No config file found! - "
				+ "\n Please reinstall the application!";
			for(ErrorListener listener : errorListeners )
				listener.addError("Configuration", errorMsg);
			log.error(errorMsg);
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
		
		log.debug("Startup: finished reading property file");
		
		return props;
	}

	private synchronized ConfigurationModel loadInitialProperties(Properties props) {
		log.debug("Startup: loading Propertys");
	
		ConfigurationModel configModel = new ConfigurationModel();
		
		//general
		configModel.setLanguage(LanguageShortcut.valueOf(props.getProperty(ConfigurationConstants.LANGUAGE).toUpperCase()));
		configModel.setDisplayTime(Integer.valueOf(props.getProperty(ConfigurationConstants.DISPLAYSECOND)));
		configModel.setDisplayCount(Integer.valueOf(props.getProperty(ConfigurationConstants.DISPLAYCOUNT)));
		
		//timerestriction
		configModel.setIsTimeFrameActivated(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIMERESTRICTION)));
		
		configModel.setTimeFrames(TimeFrameUtil.stringToSingleTimeFrame(props.getProperty(ConfigurationConstants.TIMEFRAME)));
		
		configModel.setIsMondayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_MO)));
		configModel.setIsTuesdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_TU)));
		configModel.setIsWednesdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_WE)));
		configModel.setIsThursdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_TH)));
		configModel.setIsFridayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_FR)));
		configModel.setIsSaturdayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_SA)));
		configModel.setIsSundayEnabled(Boolean.valueOf(props.getProperty(ConfigurationConstants.TIME_SU)));
		
		String vacationStart = props.getProperty(ConfigurationConstants.VACATION_START);
		if(vacationStart.length() > 0)
			configModel.setVacationStart(Date.valueOf(vacationStart));
		
		String vacationEnd = props.getProperty(ConfigurationConstants.VACATION_END);
		if(vacationEnd.length() > 0)
			configModel.setVacationEnd(Date.valueOf(vacationEnd));
		
		log.debug("Startup: Finished loading properties");
		
		return configModel;
	
	}

	
	/***** Getter // Setter *****/
	
	/**
	 * @return the errorQueue
	 */
	public Set<ErrorListener> getErrorListeners() {
		return errorListeners;
	}

	/**
	 * @param errorListeners the errorQueue to set
	 */
	public void setErrorListeners(Set<ErrorListener> errorListeners) {
		this.errorListeners = errorListeners;
	}
	
	
	
}
