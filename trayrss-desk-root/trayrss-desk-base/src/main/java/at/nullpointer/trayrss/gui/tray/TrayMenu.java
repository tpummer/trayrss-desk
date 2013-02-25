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

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;

import at.nullpointer.trayrss.messages.Messages;

/**
 * Composes the TrayMenu
 * 
 * @author Thomas Pummer
 * 
 */
public class TrayMenu
        extends PopupMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    /**
     * Creates the Menu and adds an Action Listener
     * 
     * @param trayIcon
     */
    public TrayMenu( final TrayIcon trayIcon ) {

        super();

        this.add( createMenuItem( "traymenu.command.monitor", "Check Feeds manually" ) );
        this.add( createMenuItem( "traymenu.command.config", "Configuration" ) );
        this.add( createMenuItem( "traymenu.command.help", "Help" ) );
        this.add( createMenuItem( "traymenu.command.exit", "Exit" ) );
        this.addActionListener( new TrayMenuActionListener( trayIcon ) );

    }


    private MenuItem createMenuItem( String textKey, String defaultMessage ) {

        String caption = Messages.getMessageResolver( Messages.GUI ).getString( textKey, defaultMessage );
        return new MenuItem( caption );
    }
}