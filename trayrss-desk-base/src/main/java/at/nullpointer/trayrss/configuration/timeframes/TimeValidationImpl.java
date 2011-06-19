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
package at.nullpointer.trayrss.configuration.timeframes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class TimeValidationImpl implements TimeValidation {
	
	private Logger log = Logger.getLogger(TimeValidationImpl.class);

	@Override
	public boolean isAllowed() {
		
		boolean allowed = true;
		
		Calendar now = new GregorianCalendar();
				
		Vacation vac = new Vacation(null,null); //TODO Vacation rausholen aus dem model
		
		allowed = allowed && vac.isAllowed(now);
		
		log.debug("Allowed after Vacationcheck: "+ allowed);
		
		Timeframe frame = new Timeframe(null); //TODO Timeframe rausholen
		
		allowed = allowed && frame.isAllowed(now);
		
		log.debug("Allowed after Timeframecheck: "+ allowed);
				
		Week week = new Week(null, //TODO Woche rausholen
							 null,
							 null,
							 null,
							 null,
							 null,
							 null);
		
		allowed = allowed && week.isAllowed(now);
		
		log.debug("Allowed after Weekcheck: "+ allowed);
		
		return allowed;
	}

}
