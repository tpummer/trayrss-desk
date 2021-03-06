/*
 * TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
 * http://trayrss.nullpointer.at/
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package at.nullpointer.trayrss.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.configuration.model.LanguageShortcut;
import at.nullpointer.trayrss.configuration.timeframes.TimeFrameUtil;
import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.error.ErrorListener;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.persistence.FeedRepository;

/**
 * 
 * 
 * @author Thomas Pummer
 * 
 */
public final class ConfigurationControllerImpl
        implements ConfigurationController {

    private Set<ErrorListener> errorListeners;

    private Set<ChangeListener> changeListener = new HashSet<ChangeListener>();

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( ConfigurationControllerImpl.class );

    private static ConfigurationControllerImpl instance;

    private Properties props;

    private ConfigurationModel configModel;


    private ConfigurationControllerImpl() {

    }


    /**
     * Gives the singleton instance of the ConfigurationController
     * 
     * @return
     */
    public static synchronized ConfigurationController getInstance() {

        if ( instance == null ) {
            instance = new ConfigurationControllerImpl();
        }
        return instance;
    }


    /**
     * <p>
     * stores the given model to a file
     * </p>
     * 
     * @param configurationModel the config to save
     */
    public synchronized void save( ConfigurationModel configurationModel ) {

        // general
        props.setProperty( ConfigurationConstants.LANGUAGE, configModel.getLanguage().toString() );
        props.setProperty( ConfigurationConstants.DISPLAYSECOND, configModel.getDisplayTime().toString() );
        props.setProperty( ConfigurationConstants.DISPLAYCOUNT, configModel.getDisplayCount().toString() );

        // databaselocation
        props.setProperty( ConfigurationConstants.DATABASE_LOCATION, configModel.getDatabaseLocation().toString() );

        // Feed
        FeedRepository feedRepository = ReferenceCollection.getInstance().getContext()
                .getBean( "feedRepository", FeedRepository.class );
        Collection<Feed> oldFeeds = feedRepository.retrieveFeeds();
        Set<Feed> feeds = configModel.getFeeds();
        for ( Feed feed : feeds ) {
            feedRepository.saveOrUpdate( feed );
            oldFeeds.remove( feed );
        }
        for ( Feed feed : oldFeeds ) {
            feedRepository.delete( feed.getUrl() );
        }

        // timerestriction
        props.setProperty( ConfigurationConstants.TIMERESTRICTION, configModel.getIsTimeFrameActivated().toString() );

        props.setProperty( ConfigurationConstants.TIMEFRAME,
                TimeFrameUtil.singleTimeFrameToString( configModel.getTimeFrames() ) );

        props.setProperty( ConfigurationConstants.TIME_MO, configModel.getIsMondayEnabled().toString() );
        props.setProperty( ConfigurationConstants.TIME_TU, configModel.getIsTuesdayEnabled().toString() );
        props.setProperty( ConfigurationConstants.TIME_WE, configModel.getIsWednesdayEnabled().toString() );
        props.setProperty( ConfigurationConstants.TIME_TH, configModel.getIsThursdayEnabled().toString() );
        props.setProperty( ConfigurationConstants.TIME_FR, configModel.getIsFridayEnabled().toString() );
        props.setProperty( ConfigurationConstants.TIME_SA, configModel.getIsSaturdayEnabled().toString() );
        props.setProperty( ConfigurationConstants.TIME_SU, configModel.getIsSundayEnabled().toString() );

        Date vacationStart = configModel.getVacationStart();
        Date vacationEnd = configModel.getVacationEnd();
        if ( vacationStart != null )
            props.setProperty( ConfigurationConstants.VACATION_START,
                    Long.toString( configModel.getVacationStart().getTime() ) );
        if ( vacationEnd != null )
            props.setProperty( ConfigurationConstants.VACATION_END,
                    Long.toString( configModel.getVacationEnd().getTime() ) );

        try {
            props.storeToXML( new FileOutputStream( ConfigurationConstants.CONFIG_USER ), "TrayRSS Configuration" );
        } catch ( FileNotFoundException e ) {
            for ( ErrorListener listener : errorListeners )
                listener.addError(
                        Messages.getMessageResolver( Messages.ERROR ).getString( "error.config.load.title",
                                "Configuration" ),
                        Messages.getMessageResolver( Messages.ERROR ).getString( "error.config.load.filenotfound",
                                "Config file not found" ) );
        } catch ( IOException e ) {
            for ( ErrorListener listener : errorListeners )
                listener.addError(
                        Messages.getMessageResolver( Messages.ERROR ).getString( "error.config.load.title",
                                "Configuration" ),
                        Messages.getMessageResolver( Messages.ERROR ).getString( "error.config.load.ioerror",
                                "IO Error at saving" ) );
        }

        notifyAllListeners();

    }


    private void notifyAllListeners() {

        NotifyThread notifyAll = new NotifyThread();

        notifyAll.setChangeListener( changeListener );

        notifyAll.start();

    }


    /**
     * <p>
     * preloads the configuration from the filesystem
     * </p>
     */
    public synchronized void load() {

        LOG.debug( "Load configuration properties" );

        this.props = loadProps();
        this.configModel = loadInitialProperties( this.props );

        LOG.debug( "Configuration properties loaded" );

    }


    /**
     * <p>
     * returns the actual configuration
     * </p>
     * 
     * @return configModel
     */
    public synchronized ConfigurationModel getConfigurationModel() {

        if ( this.configModel == null ) {
            loadProps();
        }
        return configModel;
    }


    /**
     * Method to enter a Mock Model
     * 
     * @param model
     */
    public synchronized void setConfigurationModel( ConfigurationModel model ) {

        this.configModel = model;
    }


    /**
     * <p>
     * Reads all properties into memory
     * </p>
     */
    private synchronized Properties loadProps() {

        LOG.debug( "Startup: started loading properties file" );

        Properties properties = null;

        InputStream reader = null;
        try {

            reader = new FileInputStream( ConfigurationConstants.CONFIG_USER );

            properties = new Properties();
            properties.loadFromXML( reader );

        } catch ( FileNotFoundException e ) {
            String errorMsg = Messages.getMessageResolver( Messages.ERROR ).getString(
                    "error.config.general.filenotfound",
                    "No config file found! - " + "\n Please reinstall the application!" );
            for ( ErrorListener listener : errorListeners )
                listener.addError( "Configuration", errorMsg );
            LOG.error( errorMsg );
            System.exit( 0 );
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( reader != null ) {
                try {
                    reader.close();
                } catch ( IOException e ) {
                    LOG.error( "Error closing RSS Stream." );
                }
            }

        }

        LOG.debug( "Startup: finished reading property file" );

        return properties;
    }


    private synchronized ConfigurationModel loadInitialProperties( Properties props ) {

        LOG.debug( "Startup: loading Propertys" );

        ConfigurationModel configModel = new ConfigurationModel();

        // general
        configModel.setLanguage( LanguageShortcut.valueOf( props.getProperty( ConfigurationConstants.LANGUAGE )
                .toUpperCase() ) );
        configModel.setDisplayTime( Integer.valueOf( props.getProperty( ConfigurationConstants.DISPLAYSECOND ) ) );
        configModel.setDisplayCount( Integer.valueOf( props.getProperty( ConfigurationConstants.DISPLAYCOUNT ) ) );

        // databaselocation
        configModel.setDatabaseLocation( props.getProperty( ConfigurationConstants.DATABASE_LOCATION ) );

        // timerestriction
        configModel.setIsTimeFrameActivated( Boolean.valueOf( props
                .getProperty( ConfigurationConstants.TIMERESTRICTION ) ) );

        configModel.setTimeFrames( TimeFrameUtil.stringToSingleTimeFrame( props
                .getProperty( ConfigurationConstants.TIMEFRAME ) ) );

        configModel.setIsMondayEnabled( Boolean.valueOf( props.getProperty( ConfigurationConstants.TIME_MO ) ) );
        configModel.setIsTuesdayEnabled( Boolean.valueOf( props.getProperty( ConfigurationConstants.TIME_TU ) ) );
        configModel.setIsWednesdayEnabled( Boolean.valueOf( props.getProperty( ConfigurationConstants.TIME_WE ) ) );
        configModel.setIsThursdayEnabled( Boolean.valueOf( props.getProperty( ConfigurationConstants.TIME_TH ) ) );
        configModel.setIsFridayEnabled( Boolean.valueOf( props.getProperty( ConfigurationConstants.TIME_FR ) ) );
        configModel.setIsSaturdayEnabled( Boolean.valueOf( props.getProperty( ConfigurationConstants.TIME_SA ) ) );
        configModel.setIsSundayEnabled( Boolean.valueOf( props.getProperty( ConfigurationConstants.TIME_SU ) ) );

        String vacationStart = props.getProperty( ConfigurationConstants.VACATION_START );
        if ( vacationStart.length() > 0 ) {
            Date date = new Date();
            date.setTime( Long.valueOf( vacationStart ) );
            configModel.setVacationStart( date );
        }

        String vacationEnd = props.getProperty( ConfigurationConstants.VACATION_END );
        if ( vacationEnd.length() > 0 ) {
            Date date = new Date();
            date.setTime( Long.valueOf( vacationEnd ) );
            configModel.setVacationEnd( date );
        }

        LOG.debug( "Startup: Finished loading properties" );

        return configModel;

    }


    private void notifyAllErrorListener( String title, String text ) {

        for ( ErrorListener el : errorListeners ) {
            el.addError( title, text );
        }
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
    public void setErrorListeners( Set<ErrorListener> errorListeners ) {

        this.errorListeners = errorListeners;
    }


    public void addChangeListener( ChangeListener listener ) {

        this.changeListener.add( listener );
    }


    /**
     * @see ConfigurationController#loadFeeds()
     */
    public void loadFeeds() {

        LOG.debug( "Load Feed Data" );

        FeedRepository feedRepository = ReferenceCollection.getInstance().getContext()
                .getBean( "feedRepository", FeedRepository.class );
        configModel.setFeeds( new HashSet<Feed>( feedRepository.retrieveFeeds() ) );

        LOG.debug( "Load Feed Data finished" );

    }

}
