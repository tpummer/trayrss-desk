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
package at.nullpointer.trayrss.gui.tray;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.Shutdown;
import at.nullpointer.trayrss.gui.TrayRssConfigWindow;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.monitor.Monitor;

public class TrayMenuActionListener
        implements ActionListener {

    private Logger log = Logger.getLogger( TrayIconActionListener.class );

    private TrayIcon trayIcon;


    public TrayMenuActionListener( TrayIcon trayIcon ) {

        super();
        this.trayIcon = trayIcon;
    }


    public void actionPerformed( ActionEvent e ) {

        if ( e.getActionCommand().equals(
                Messages.getMessageResolver( Messages.GUI ).getString( "traymenu.command.monitor",
                        "Check Feeds manually" ) ) ) {
            Monitor.getInstance().stopAll( 2 );
            Monitor.getInstance().loadFeeds();
            this.trayIcon.displayMessage(
                    Messages.getMessageResolver( Messages.GUI ).getString( "trayicon.monitor.action.title",
                            "Useraction" ),
                    Messages.getMessageResolver( Messages.GUI ).getString( "trayicon.monitor.action.text",
                            "Handmade Monitoringintervall" ), TrayIcon.MessageType.INFO );

        } else
            if ( e.getActionCommand()
                    .equals(
                            Messages.getMessageResolver( Messages.GUI ).getString( "traymenu.command.config",
                                    "Configuration" ) ) ) {
                javax.swing.SwingUtilities.invokeLater( new Runnable() {

                    public void run() {

                        TrayRssConfigWindow trayRssConfigWindow = new TrayRssConfigWindow();
                        trayRssConfigWindow.getJFrame().setVisible( true );
                    }
                } );

            } else
                if ( e.getActionCommand().equals(
                        Messages.getMessageResolver( Messages.GUI ).getString( "traymenu.command.help", "Help" ) ) ) {
                    javax.swing.SwingUtilities.invokeLater( new Runnable() {

                        public void run() {

                            new Help();
                        }
                    } );

                } else
                    if ( e.getActionCommand().equals(
                            Messages.getMessageResolver( Messages.GUI ).getString( "traymenu.command.exit", "Exit" ) ) ) {
                        Shutdown shutdown = new Shutdown();
                        shutdown.now();
                        System.exit( 0 );
                    }

    }
}
