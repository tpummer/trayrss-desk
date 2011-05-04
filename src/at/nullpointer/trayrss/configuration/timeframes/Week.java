/**
    RSSTray - simply alerting at new Feed Information
    Copyright (C) 2009 Thomas Pummer
    conatct me fake (at) sprossenwanne.at

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

/**
 * Represents the days TrayRSS should monitor the feeds
 * 
 * @author thefake
 * 
 */
public class Week {
	private boolean mon, tue, wed, thu, fri, sat, sun;

	public Week(String mon, String tue, String wed, String thu, String fri,
			String sat, String sun) {

		this.mon = new Boolean(mon);
		this.tue = new Boolean(tue);
		this.wed = new Boolean(wed);
		this.thu = new Boolean(thu);
		this.fri = new Boolean(fri);
		this.sat = new Boolean(sat);
		this.sun = new Boolean(sun);

	}

	public void setMon(boolean mon) {
		this.mon = mon;
	}

	public boolean isMon() {
		return this.mon;
	}

	public void setTue(boolean tue) {
		this.tue = tue;
	}

	public boolean isTue() {
		return this.tue;
	}

	public void setWed(boolean wed) {
		this.wed = wed;
	}

	public boolean isWed() {
		return this.wed;
	}

	public void setThu(boolean thu) {
		this.thu = thu;
	}

	public boolean isThu() {
		return this.thu;
	}

	public void setFri(boolean fri) {
		this.fri = fri;
	}

	public boolean isFri() {
		return this.fri;
	}

	public void setSat(boolean sat) {
		this.sat = sat;
	}

	public boolean isSat() {
		return this.sat;
	}

	public void setSun(boolean sun) {
		this.sun = sun;
	}

	public boolean isSun() {
		return this.sun;
	}

	public boolean isAllowed(Calendar now) {
		int day = now.get(Calendar.DAY_OF_WEEK);

		boolean erlaubt = true;
		
		switch (day) {
		case Calendar.MONDAY:
			erlaubt = erlaubt && isMon();
			break;
		case Calendar.TUESDAY:
			erlaubt = erlaubt && isTue();
			break;
		case Calendar.WEDNESDAY:
			erlaubt = erlaubt && isWed();
			break;
		case Calendar.THURSDAY:
			erlaubt = erlaubt && isThu();
			break;
		case Calendar.FRIDAY:
			erlaubt = erlaubt && isFri();
			break;
		case Calendar.SATURDAY:
			erlaubt = erlaubt && isSat();
			break;
		case Calendar.SUNDAY:
			erlaubt = erlaubt && isSun();
			break;
		default:
		}

		return erlaubt;
	}

}