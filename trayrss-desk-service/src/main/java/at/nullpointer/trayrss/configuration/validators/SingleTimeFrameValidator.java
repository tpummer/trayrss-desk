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
package at.nullpointer.trayrss.configuration.validators;

import java.util.Calendar;

import at.nullpointer.trayrss.configuration.model.SingleTimeFrame;

/**
 * <p>This class contains methods to validate a {@link SingleTimeFrame}</p> 
 * @author Thomas Pummer
 */
public class SingleTimeFrameValidator{
	
	/**
	 * validateSingleTimeFrame is used to validate if a date matches a {@link SingleTimeFrame}</p>
	 * @param stf
	 * @param now
	 * @return
	 */
	public static Boolean validateTimeInSingleTimeFrame(SingleTimeFrame stf, Calendar now){
		Boolean result = true;
		
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int min = now.get(Calendar.MINUTE);
		
		result = result && stf.getStartHour() < hour;
		
		if(stf.getStartHour() == hour){
			result = result && stf.getStartMin() <= min;
		}
		
		result = result && stf.getEndHour() > hour;
		
		if(stf.getEndHour() == hour){
			result = result && stf.getEndMin() >= min;
		}
		
		return result;
	}

}
