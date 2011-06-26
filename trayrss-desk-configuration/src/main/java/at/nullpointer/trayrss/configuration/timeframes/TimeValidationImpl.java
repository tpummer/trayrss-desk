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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.configuration.model.SingleTimeFrame;
import at.nullpointer.trayrss.configuration.validators.SingleTimeFrameValidator;

/**
 * This Class is an implementation of @see TimeValidation, and allowes time 
 * validation to determinate if the configuration allowes monitoring at the 
 * current time
 * 
 * @author Thomas Pummer
 *
 */
public class TimeValidationImpl implements TimeValidation {

	private static Logger log = Logger.getLogger(TimeValidationImpl.class);

	/**
	 * This method is used to determinate if the configuration allowes monitoring at the current time
	 */
	@Override
	public boolean isAllowed(ConfigurationModel configModel) {

		Calendar now = new GregorianCalendar();

		// Checking wanted?
		boolean allowed = configModel.getIsTimeFrameActivated();
		
		log.debug("User wants to restrict the time: " + allowed);

		if (allowed) {
			// Time Check
			allowed = allowed
					&& isWithinTimeFrames(now, configModel.getTimeFrames());

			log.debug("Allowed after Timeframecheck: " + allowed);

			// Weekday check
			allowed = allowed && isAllowedToday(now, configModel.getIsMondayEnabled(), configModel.getIsTuesdayEnabled(),
					                                 configModel.getIsWednesdayEnabled(), configModel.getIsThursdayEnabled(),
					                                 configModel.getIsFridayEnabled(), configModel.getIsSaturdayEnabled(),
					                                 configModel.getIsSundayEnabled());

			log.debug("Allowed after Weekcheck: " + allowed);

			// Vacation Check
			allowed = allowed
					&& isNotAtVacation(now, configModel.getVacationStart(),
							configModel.getVacationEnd());

			log.debug("Allowed after Vacationcheck: " + allowed);
		}
		
		return allowed;
	}

	
	/***
	 * Determinates if the time given isn't within two dates
	 * 
	 * @param now
	 * @param start
	 * @param end
	 * @return
	 */
	private boolean isNotAtVacation(Calendar now, Date start, Date end) {
		return start.after(now.getTime()) || end.before(now.getTime());
	}
	
	/**
	 * Determinates if the the given date matches an allowed day
	 * 
	 * @param now
	 * @param mon
	 * @param tue
	 * @param wed
	 * @param thu
	 * @param fri
	 * @param sat
	 * @param sun
	 * @return
	 */
	private boolean isAllowedToday(Calendar now, Boolean mon, Boolean tue, Boolean wed, Boolean thu, Boolean fri, Boolean sat, Boolean sun) {
		int day = now.get(Calendar.DAY_OF_WEEK);

		boolean allowed = true;
		
		switch (day) {
		case Calendar.MONDAY:
			allowed = allowed && mon;
			break;
		case Calendar.TUESDAY:
			allowed = allowed && tue;
			break;
		case Calendar.WEDNESDAY:
			allowed = allowed && wed;
			break;
		case Calendar.THURSDAY:
			allowed = allowed && thu;
			break;
		case Calendar.FRIDAY:
			allowed = allowed && fri;
			break;
		case Calendar.SATURDAY:
			allowed = allowed && sat;
			break;
		case Calendar.SUNDAY:
			allowed = allowed && sun;
			break;
		default:
		}

		return allowed;
	}

	/**
	 * determinates if the given time is within the timeframes defined in the frameSet
	 * 
	 * @param now
	 * @param frameSet
	 * @return
	 */
	private boolean isWithinTimeFrames(Calendar now,
			Set<SingleTimeFrame> frameSet) {

		boolean allowed = true;

		for (SingleTimeFrame frame : frameSet) {
			allowed = allowed
					&& SingleTimeFrameValidator.validateTimeInSingleTimeFrame(
							frame, now);
		}

		return allowed;
	}

}
