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

import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.file.ConfigurationFileService;
import at.nullpointer.trayrss.configuration.file.ConfigurationFileServiceImpl;
import at.nullpointer.trayrss.gui.tray.TrayIconChangeListener;
import at.nullpointer.trayrss.gui.tray.TrayIconPOJO;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.monitor.Monitor;
import at.nullpointer.trayrss.notification.JNotificationPopupFactory;
import at.nullpointer.trayrss.notification.TrayNotifier;
import at.nullpointer.trayrss.persistence.PersistenceAdapter;
import at.nullpointer.trayrss.persistence.PersistenceAdapterImpl;

/**
 * Prozesses all initial loadings
 * 
 * @author thefake
 * 
 */
public class StartUp {

    /**
     * Logger
     */
    private Logger log = Logger.getLogger( StartUp.class );

    /**
     * Properties
     */
    private Properties props = new Properties();


    /**
     * Prozesses all initial loadings
     * 
     * @param debug switches the logger to debug mode
     */
    public StartUp( final boolean debug ) {

        if ( debug ) {
            Logger.getRootLogger().setLevel( Level.DEBUG );
        }

        // Copy configuration file in user directory
        final ConfigurationFileService configurationFileService = new ConfigurationFileServiceImpl();

        if ( !configurationFileService.isConfigInUserDir() ) {
            configurationFileService.copyConfigToUserDir();
        }

        final ConfigurationController configControl = ConfigurationControllerImpl.getInstance();

        configControl.load();

        startDatabase();

        configControl.loadFeeds();

        initializeMessages();

        startTray();

        startMonitor();

        log.info( "Startup complete." );
    }


    private void initializeMessages() {

        log.debug( "Load Messages" );

        Messages.setup( ConfigurationControllerImpl.getInstance().getConfigurationModel().getLanguage().getShortcut() );

        final ConfigurationController configContr = ConfigurationControllerImpl.getInstance();
        configContr.addChangeListener( new MessageLanguageSwitcher() );

        log.debug( "Messages loaded" );
    }


    private void startTray() {

        log.debug( "Startup: Start Tray" );

        final TrayIconPOJO trayIconPOJO = new TrayIconPOJO();
        trayIconPOJO.startTrayIcon();
        ConfigurationControllerImpl.getInstance().addChangeListener( new TrayIconChangeListener() );

        log.debug( "Startup: Finished Start Tray" );
    }


    private void startDatabase() {

        log.debug( "Startup: Start Database" );

        PersistenceAdapter persistenceAdapter = PersistenceAdapterImpl.getInstance();

        persistenceAdapter.start();

        log.debug( "Startup: Finished Start Database" );

    }


    private void startMonitor() {

        log.debug( "Startup: Start Monitor" );

        final JNotificationPopupFactory jNotificationPopupFactory = new JNotificationPopupFactory();
        final TrayNotifier trayNotifier = new TrayNotifier( jNotificationPopupFactory );
        new Thread( trayNotifier ).start();

        Monitor.setTrayNotifier( trayNotifier );
        final Monitor monitor = Monitor.getInstance();

        final ConfigurationController configContr = ConfigurationControllerImpl.getInstance();
        configContr.addChangeListener( monitor );
        configContr.addChangeListener( trayNotifier );

        log.debug( "Startup: Finished Start Monitor" );
    }
}
