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

/**
 * <p>
 * Stores the constants for retrieving the configuration, e.g. filepath and variable names in the porperties file
 * </p>
 * 
 * @author Thomas Pummer
 */
public final class ConfigurationConstants {

    /**
     * Location of the configuration
     */
    public static final String CONFIG = "./config.ini";

    /**
     * Matcher for the language
     */
    public static final String LANGUAGE = "trayrss.lang";

    /**
     * Matcher for displayseconds
     */
    public static final String DISPLAYSECOND = "trayrss.displaysecond";

    /**
     * Matcher for displaycount
     */
    public static final String DISPLAYCOUNT = "trayrss.displaycount";

    /**
     * Matcher for timerestrictions
     */
    public static final String TIMERESTRICTION = "trayrss.timerestriction";

    /**
     * Matcher for timeframes
     */
    public static final String TIMEFRAME = "trayrss.time.timeframe";

    /**
     * Matcher for monday
     */
    public static final String TIME_MO = "trayrss.time.mo";
    /**
     * Matcher for tuesday
     */
    public static final String TIME_TU = "trayrss.time.tu";
    /**
     * Matcher for wednesday
     */
    public static final String TIME_WE = "trayrss.time.we";
    /**
     * Matcher for thursday
     */
    public static final String TIME_TH = "trayrss.time.th";
    /**
     * Matcher for friday
     */
    public static final String TIME_FR = "trayrss.time.fr";
    /**
     * Matcher for saturday
     */
    public static final String TIME_SA = "trayrss.time.sa";
    /**
     * Matcher for sunday
     */
    public static final String TIME_SU = "trayrss.time.su";

    /**
     * Matcher for the vacationstart
     */
    public static final String VACATION_START = "trayrss.time.vacationstart";
    /**
     * Matcher for the vacationend
     */
    public static final String VACATION_END = "trayrss.time.vacationend";

    /**
     * Matcher for the config to find the value for the database location
     */
    public static final String DATABASE_LOCATION = "trayrss.database.location";


    private ConfigurationConstants() {

    }

}
