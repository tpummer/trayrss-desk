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

import java.util.Set;

import lombok.Setter;

/**
 * Thread handles the notification of several listeners
 * 
 * @author Thomas Pummer
 * 
 */
class NotifyThread
        extends Thread {

    /**
     * Listeners to be notified
     */
    @Setter
    private Set<ChangeListener> changeListener;


    /**
     * Notifies the Listeners that a change has happend
     */
    @Override
    public void run() {

        for ( ChangeListener singleListener : this.changeListener ) {
            singleListener.notifyChange();
        }
    }
}