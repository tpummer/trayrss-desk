package at.nullpointer.trayrss.gui.configframe;

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
