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
package at.nullpointer.trayrss.checks;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeFrameValidator {

	public static boolean checkTimeFrames(String input) {

		// Splitten
		String[] splitted = input.trim().split(" ");

		for (String timeframe : splitted) {
			String[] time = timeframe.split("-");
			if (time.length != 2) {
				return false;
			}
			if (!CheckLib.checkLong(time[0]) || !CheckLib.checkLong(time[1])) {
				return false;
			}
			if (!checkTime(time[0]) || !checkTime(time[1])) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkTime(String input) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		try {
			sdf.parse(input);
		} catch (ParseException e) {
			return false;
		}
		int hour = Integer.parseInt(input.substring(0, 2));
		int min = Integer.parseInt(input.substring(2, 4));

        return !(hour < 0 || hour > 24 || min < 0 || min > 60);
		}

	public static boolean timeFramesMessage(boolean checkTimeFrames,
			String field) {

		if (!checkTimeFrames) {
			JOptionPane.showMessageDialog(null, String.format(
					ReferenceCollection.config_timeframe_valid_text, field),
					ReferenceCollection.config_timeframe_valid_title,
					JOptionPane.ERROR_MESSAGE);
		}

		return checkTimeFrames;
	}

}
