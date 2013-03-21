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
package at.nullpointer.trayrss.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ActivateTimeItemListener
        implements ItemListener {

    private TrayRssConfigWindow window;


    public ActivateTimeItemListener( TrayRssConfigWindow window ) {

        super();
        this.window = window;
    }


    public void itemStateChanged( ItemEvent e ) {

        if ( e.getStateChange() == ItemEvent.SELECTED ) {
            this.window.setTimeEnabled( true );
        } else
            if ( e.getStateChange() == ItemEvent.DESELECTED ) {
                this.window.setTimeEnabled( false );
            }

    }

}
