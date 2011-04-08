/**
    TrayRSS - simply alerting at new Feed Information
	visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss.configuration.timeframes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

public class TimeValidationImpl implements TimeValidation {
	
	private Logger log = Logger.getLogger(TimeValidationImpl.class);

	@Override
	public boolean isAllowed() {
		
		boolean allowed = true;
		
		Calendar now = new GregorianCalendar();
				
		Vacation vac = new Vacation(ReferenceCollection.CONFIG_VACATION_START_VALUE,
									ReferenceCollection.CONFIG_VACATION_END_VALUE);
		
		allowed = allowed && vac.isAllowed(now);
		
		log.debug("Allowed after Vacationcheck: "+ allowed);
		
		Timeframe frame = new Timeframe(ReferenceCollection.CONFIG_TIMEFRAMES_VALUE);
		
		allowed = allowed && frame.isAllowed(now);
		
		log.debug("Allowed after Timeframecheck: "+ allowed);
				
		Week week = new Week(ReferenceCollection.CONFIG_MONITORINGDAYSMO_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSTU_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSWE_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSTH_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSFR_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSSA_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSSU_VALUE);
		
		allowed = allowed && week.isAllowed(now);
		
		log.debug("Allowed after Weekcheck: "+ allowed);
		
		return allowed;
	}

}
