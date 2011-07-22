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
package at.nullpointer.trayrss.gui.tablemodel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedTableValidator {

	public static boolean checkURL(String url) {

		URL test;

		try {
			test = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}

		SyndFeedInput input = new SyndFeedInput();
		try {
			input.build(new XmlReader(test));
		} catch (IllegalArgumentException e) {
			return false;
		} catch (FeedException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

		return true;
	}
}
