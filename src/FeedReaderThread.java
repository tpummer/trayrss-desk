/**
 * Obsolet
 */
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

import java.util.Iterator;
import java.util.List;
import java.awt.TrayIcon;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


public class FeedReaderThread implements Runnable {
	private FeedInfo feedInfo = null;
	private TrayIcon icon = null;

	public FeedReaderThread(FeedInfo feedInfo, TrayIcon icon) {
		this.feedInfo = feedInfo;
		this.icon = icon;
		
	}

	@Override
	public void run() {
		
		while (true) {
			boolean ok = false;
			try {

				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed = input.build(new XmlReader(feedInfo.getUrl()));

				List<SyndEntryImpl> content = feed.getEntries();
				
				for(Iterator<SyndEntryImpl> it = content.iterator(); it.hasNext();){
					SyndEntryImpl node = it.next();
					System.out.println(node.getAuthor());
					System.out.println(node.getTitle());
					System.out.println(node.getPublishedDate());
					System.out.println(node.getUpdatedDate());
					System.out.println(node.getUri());
					System.out.println("-------------");
					
					icon.displayMessage(node.getPublishedDate().toString(), node.getTitle(), 
							TrayIcon.MessageType.INFO);
					//TODO wieder entfernen
					Thread.sleep(1000);
				}

				ok = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("ERROR: " + ex.getMessage());
			}
			if (!ok) {
				System.out.println();
				System.out
						.println("FeedReader reads and prints any RSS/Atom feed type.");
				System.out
						.println("The first parameter must be the URL of the feed to read.");
				System.out.println();
			}
			try {
				Thread.sleep(feedInfo.getIntervall());
			} catch (InterruptedException e) {

			}
		}
	}

}
