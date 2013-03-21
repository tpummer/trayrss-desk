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
package at.nullpointer.trayrss.gui.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import lombok.Getter;
import lombok.Setter;
import at.nullpointer.trayrss.monitor.IconChanger;

/**
 * Prepares the TrayIcon
 * 
 * @author Thomas Pummer
 * 
 */
public class TrayIconPOJO {

    /**
     * TrayIcon
     */
    @Getter
    @Setter
    private TrayIcon trayIcon;


    /**
     * Starts up the TrayIcon
     */
    public void startTrayIcon() {

        if ( SystemTray.isSupported() ) {
            final SystemTray tray = SystemTray.getSystemTray();

            trayIcon = IconChanger.createTrayIcon( new TrayMenu( trayIcon ) );

            trayIcon.setImageAutoSize( true );
            trayIcon.addActionListener( new TrayIconActionListener() );
            trayIcon.addMouseListener( new TrayIconMouseListener() );

            try {
                tray.add( trayIcon );
            } catch ( AWTException e ) {
                System.err.println( "TrayIcon could not be added." );
            }
        }

        else {
            System.err.println( "Systray not supported!" );
            System.exit( 1 );
        }
    }


    /**
     * refreshes the TrayIcon
     */
    public void refreshTrayIcon() {

        if ( SystemTray.isSupported() ) {
            final SystemTray tray = SystemTray.getSystemTray();

            tray.remove( trayIcon );

            startTrayIcon();
        }

    }

}
