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
package at.nullpointer.trayrss.configuration;



/**
 * <p>Stores the constants for retrieving the configuration, e.g. filepath
 * and variable names in the porperties file</p>
 * 
 * @author Thomas Pummer
 */
public class ConfigurationConstants {
	
	/***********************************************
	 * File Reference
	 ***********************************************/
	
	protected static final String CONFIG = "./config.ini";
	
	/***********************************************
	 * property name
	 ***********************************************/
	
	
	//general
	protected static final String LANGUAGE = "trayrss.lang";
	
	protected static final String DISPLAYSECOND = "trayrss.displaysecond";
	protected static final String DISPLAYCOUNT = "trayrss.displaycount";
	
	// timerestrictions
	protected static final String TIMERESTRICTION = "trayrss.timerestriction";
	
	protected static final String TIMEFRAME = "trayrss.time.timeframe";
	
	protected static final String TIME_MO = "trayrss.time.mo";
	protected static final String TIME_TU = "trayrss.time.tu";
	protected static final String TIME_WE = "trayrss.time.we";
	protected static final String TIME_TH = "trayrss.time.th";
	protected static final String TIME_FR = "trayrss.time.fr";
	protected static final String TIME_SA = "trayrss.time.sa";
	protected static final String TIME_SU = "trayrss.time.su";
	
	protected static final String VACATION_START = "trayrss.time.vacationstart";
	protected static final String VACATION_END = "trayrss.time.vacationend";

}