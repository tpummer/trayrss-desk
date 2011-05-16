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
package at.nullpointer.trayrss.checks;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

import javax.swing.*;

public class CheckLib {
	
	public static boolean checkLong(String input){
		
		try {
			Long.parseLong(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean longMessage(boolean checkLong, String field) {
		
		if(!checkLong){
			JOptionPane.showMessageDialog(null,
					String.format(ReferenceCollection.config_long_valid_text, field),
					ReferenceCollection.config_long_valid_title,
					JOptionPane.ERROR_MESSAGE);
		}
		
		return checkLong;
	}

}
