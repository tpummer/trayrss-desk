package at.nullpointer.trayrss.configuration.timeframes;

import java.util.ArrayList;
import java.util.Calendar;

public class Timeframe {
	
	ArrayList<SingleTimeFrame> frameList;

	public Timeframe(String inputFrame) {
		String[] splitted = inputFrame.trim().split(" ");
		
		frameList = new ArrayList<SingleTimeFrame>();
		
		for (String timeframe : splitted) {
			SingleTimeFrame frame = new SingleTimeFrame(timeframe.split("-"));
			frameList.add(frame);
		}
	}

	public boolean isAllowed(Calendar now) {
		
		boolean allowed = true;
		
		for (SingleTimeFrame frame : frameList){
			allowed = allowed && frame.isAllowed(now);
		}

		return true;
	}

}
