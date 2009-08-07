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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

public class FeedTableValidator {

	public static boolean checkURL(String url) {
		
		URL test;

		try {
			test = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed;
			try {
				feed = input.build(new XmlReader(test));
			} catch (IllegalArgumentException e) {
				return false;
			} catch (FeedException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		
		return true;
	}

	public static boolean checkURL(Object[][] table, int urlColumn) {
		int rows = table.length;
		boolean valid = true;

		for (int i = 0; i < rows; i++) {
			String checkString = (String) (table[i][urlColumn]);
			valid = valid && checkURL(checkString);
			if(!valid)JOptionPane.showMessageDialog(null, String.format(
					ReferenceCollection.config_url_valid_text, checkString, i+1),
					ReferenceCollection.config_url_valid_title,
					JOptionPane.ERROR_MESSAGE);
		}

		return valid;

	}
}
