/*
    TrayRSS - simply notification of feed information
    (c) 2009-2011 TrayRSS Developement Team
    visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss;



/**
 * <p>Stores the constants for retrieving the configuration, e.g. filepath
 * and variable names in the porperties file</p>
 * 
 * @author Thomas Pummer
 * @version 1.0
 */
public class ConfigurationConstants {
	
	/***********************************************
	 * File Reference
	 ***********************************************/
	
	public static final String CONFIG = "./config.ini";
	
	/***********************************************
	 * property name
	 ***********************************************/
	
	
	//general
	public static final String LANGUAGE = "trayrss.lang";
	
	public static final String DISPLAYSECOND = "trayrss.displaysecond";
	public static final String DISPLAYCOUNT = "trayrss.displaycount";
	
	// timerestrictions
	public static final String TIMERESTRICTION = "trayrss.timerestriction";
	
	public static final String TIMEFRAME = "trayrss.timeframe";
	
	public static final String TIME_MO = "trayrss.time.mo";
	public static final String TIME_TU = "trayrss.time.tu";
	public static final String TIME_WE = "trayrss.time.we";
	public static final String TIME_TH = "trayrss.time.th";
	public static final String TIME_FR = "trayrss.time.fr";
	public static final String TIME_SA = "trayrss.time.sa";
	public static final String TIME_SU = "trayrss.time.su";
	
	public static final String VACATION_START = "time.vacationstart";
	public static final String VACATION_END = "time.vacationend";

}
