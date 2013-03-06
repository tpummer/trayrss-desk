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
package at.nullpointer.trayrss.error;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class JOptionPaneErrorListener implements ErrorListener {

	public void addError(String title, String text) {
		addError(title, text, ErrorType.ERROR_MESSAGE);
	}

	public void addError(String title, String text, int messageType) {
		addError(null, title, text, messageType);

	}

	public void addError(JComponent where, String title, String text,
			int messageType) {
		addError(where, title, text, messageType, null);

	}

	public void addError(JComponent where, String title, String text,
			int messageType, Icon icon) {
		if (icon != null) {
			JOptionPane
					.showMessageDialog(where, text, title, messageType, icon);
		} else {
			JOptionPane
					.showMessageDialog(where, text, title, messageType);
		}

	}

}
