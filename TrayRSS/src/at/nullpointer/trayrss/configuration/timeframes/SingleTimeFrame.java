package at.nullpointer.trayrss.configuration.timeframes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

public class SingleTimeFrame {
	
	int startHour, startMin, endHour, endMin;	
	

	public SingleTimeFrame(String[] split) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		
		try {
			sdf.parse(split[0]);
			sdf.parse(split[1]);
		} catch (ParseException e) {
			ReferenceCollection.LOG.error("Error parsing Timeframes on Check");
		}
		startHour = Integer.parseInt(split[0].substring(0, 2));
		startMin = Integer.parseInt(split[0].substring(2, 4));
		endHour = Integer.parseInt(split[0].substring(0, 2));
		endMin = Integer.parseInt(split[0].substring(2, 4));
	}


	public int getStartHour() {
		return startHour;
	}


	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}


	public int getStartMin() {
		return startMin;
	}


	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}


	public int getEndHour() {
		return endHour;
	}


	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}


	public int getEndMin() {
		return endMin;
	}


	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}


	public boolean isAllowed(Calendar now) {
		
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int min = now.get(Calendar.MINUTE);
		
		return startHour < hour && startMin < min && endHour > hour && endMin > min;
	}
	
	

}
