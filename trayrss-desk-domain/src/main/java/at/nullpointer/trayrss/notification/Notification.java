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
package at.nullpointer.trayrss.notification;

import lombok.Data;

/**
 * A container for an Item to be displayed
 * 
 * @author Thomas Pummer
 * 
 */
@Data
public class Notification {

    /**
     * Title of the News
     */
    private String title;

    /**
     * Name of the Feed
     */
    private String feedName;

    /**
     * Key of the newsitem in the database
     */
    private String newsUri;

}
