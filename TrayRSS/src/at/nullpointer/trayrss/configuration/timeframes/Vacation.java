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
