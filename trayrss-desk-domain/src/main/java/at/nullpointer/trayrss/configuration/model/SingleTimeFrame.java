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
package at.nullpointer.trayrss.configuration.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class SingleTimeFrame {
	
	private Logger log = Logger.getLogger(SingleTimeFrame.class);
	
	int startHour, startMin, endHour, endMin;	
	

	public SingleTimeFrame(String[] split) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		
		try {
			sdf.parse(split[0]);
			sdf.parse(split[1]);
		} catch (ParseException e) {
			log.error("Error parsing Timeframes on Check");
		}
		startHour = Integer.parseInt(split[0].substring(0, 2));
		startMin = Integer.parseInt(split[0].substring(2, 4));
		endHour = Integer.parseInt(split[1].substring(0, 2));
		endMin = Integer.parseInt(split[1].substring(2, 4));
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
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		if(startHour < 10)
			result.append(0);
		result.append(startHour);
		if(startMin < 10)
			result.append(0);
		result.append(startMin);
		
		result.append("-");
		
		if(endHour < 10)
			result.append(0);
		result.append(endHour);
		if(endMin < 10)
			result.append(0);
		result.append(endMin);
		
		return result.toString();
	}
	

}