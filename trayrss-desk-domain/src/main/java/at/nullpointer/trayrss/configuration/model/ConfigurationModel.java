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
package at.nullpointer.trayrss.configuration.model;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import at.nullpointer.trayrss.domain.Feed;

/**
 * <p>
 * The ConfigurationModel is the connection between the configuration GUI and the persistence of the configuration.
 * </p>
 * 
 * @author Thomas Pummer
 */
@Data
public class ConfigurationModel {

    /**
     * DisplayCount
     */
    private Integer displayCount;
    /**
     * DisplayTime
     */
    private Integer displayTime;
    /**
     * Language
     */
    private LanguageShortcut language;
    /**
     * Actual Set of Feeds
     */
    private Set<Feed> feeds;
    /**
     * Marker for TimeFrame activated
     */
    private Boolean isTimeFrameActivated;
    /**
     * Marker for enabled Mondays
     */
    private Boolean isMondayEnabled;
    /**
     * Marker for enabled Tuesdays
     */
    private Boolean isTuesdayEnabled;
    /**
     * Marker for enabled Wednesdays
     */
    private Boolean isWednesdayEnabled;
    /**
     * Marker for enabled Thursdays
     */
    private Boolean isThursdayEnabled;
    /**
     * Marker for enabled Fridays
     */
    private Boolean isFridayEnabled;
    /**
     * Marker for enabled Saturdays
     */
    private Boolean isSaturdayEnabled;
    /**
     * Marker for enabled Sundays
     */
    private Boolean isSundayEnabled;
    /**
     * Timeframes
     */
    private Set<SingleTimeFrame> timeFrames;
    /**
     * Vacation start
     */
    private Date vacationStart;
    /**
     * Vacation end
     */
    private Date vacationEnd;
    /**
     * database Location
     */
    private String databaseLocation;
    /**
     * log Location
     */
    private String logLocation;

}
