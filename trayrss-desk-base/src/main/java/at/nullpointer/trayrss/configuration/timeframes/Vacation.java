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

public class Vacation {
	
	Date start;
	Date end;

	public Vacation(String start,
			String end) {
		
		if (start.equals("")) start = "0";
		if (end.equals("")) end  = "1";
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong(start));
		this.start = cal.getTime();
		
		cal.setTimeInMillis(Long.parseLong(end));
		this.end = cal.getTime();
	}

	public boolean isAllowed(Calendar now) {
		return start.after(now.getTime()) || end.before(now.getTime());
	}

}
