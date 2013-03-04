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
package at.nullpointer.trayrss.notification;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.jutzig.jnotification.JNotificationPopup;

/**
 * Factory for {@link JNotificationPopup}
 * 
 * @author Thomas Pummer
 * 
 */
public class JNotificationPopupFactory {

    /**
     * Creates new {@link JNotificationPopup} with a JPanel on it with a Title a Name and a URL
     * 
     * @param title
     * @param name
     * @return A JNotificationPopup
     */
    public JNotificationPopup createPopup( final String title, final String name ) {

        final JPanel panel = new JPanel( new GridLayout( 2, 1 ) );
        final JLabel ltitel = new JLabel();
        ltitel.setText( "<html><b>" + title + "</b></html>" );
        panel.add( ltitel );

        panel.setBorder( new TitledBorder( name ) );
        return new JNotificationPopup( panel );

    }

}
