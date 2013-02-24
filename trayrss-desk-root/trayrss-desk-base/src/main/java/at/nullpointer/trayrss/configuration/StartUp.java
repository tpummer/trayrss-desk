/*
 * TrayRSS - simply notification of feed information (c) 2009-2011 TrayRSS Developement Team visit the project at
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
import org.hibernate.cfg.AnnotationConfiguration;

import at.nullpointer.trayrss.dao.SessionFactoryRepository;
import at.nullpointer.trayrss.gui.tray.TrayIconChangeListener;
import at.nullpointer.trayrss.gui.tray.TrayIconPOJO;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.monitor.Monitor;
import at.nullpointer.trayrss.notification.JNotificationPopupFactory;
import at.nullpointer.trayrss.notification.TrayNotifier;

/**
 * Prozesses all initial loadings
 * 
 * @author thefake
 * 
 */
public class StartUp {

    Logger log = Logger.getLogger( StartUp.class );

    Properties props = new Properties();
    Properties langprops = new Properties();


    /**
     * Prozesses all initial loadings
     * 
     * @param debug switches the logger to debug mode
     */
    public StartUp( boolean debug ) {

        if ( debug )
            Logger.getRootLogger().setLevel( Level.DEBUG );

        startDatabase();
        ConfigurationController configControl = ConfigurationControllerImpl.getInstance();
        configControl.load();
        initializeMessages();
        startTray();
        startMonitor();
        log.info( "Startup complete." );
    }


    private void initializeMessages() {

        Messages.setup( ConfigurationControllerImpl.getInstance().getConfigurationModel().getLanguage().getShortcut() );

        ConfigurationController configContr = ConfigurationControllerImpl.getInstance();
        configContr.addChangeListener( new MessageLanguageSwitcher() );

    }


    private void startTray() {

        log.debug( "Startup: Start Tray" );

        TrayIconPOJO trayIconPOJO = new TrayIconPOJO();
        trayIconPOJO.startTrayIcon();
        ConfigurationControllerImpl.getInstance().addChangeListener( new TrayIconChangeListener() );

        log.debug( "Startup: Finished Start Tray" );
    }


    private void startDatabase() {

        log.debug( "Startup: Start Database" );

        SessionFactoryRepository.setSessionFactory( new AnnotationConfiguration().configure().buildSessionFactory() );

        log.debug( "Startup: Finished Start Database" );

    }


    private void startMonitor() {

        log.debug( "Startup: Start Monitor" );

        JNotificationPopupFactory jNotificationPopupFactory = new JNotificationPopupFactory();
        TrayNotifier trayNotifier = new TrayNotifier( jNotificationPopupFactory );
        new Thread( trayNotifier ).start();

        Monitor.setTrayNotifier( trayNotifier );
        Monitor monitor = Monitor.getInstance();

        ConfigurationController configContr = ConfigurationControllerImpl.getInstance();
        configContr.addChangeListener( monitor );
        configContr.addChangeListener( trayNotifier );

        log.debug( "Startup: Finished Start Monitor" );
    }
}
