package at.nullpointer.trayrss.monitor;

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

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.FeedDAO;
import at.nullpointer.trayrss.configuration.feeds.NewsDAO;
import at.nullpointer.trayrss.configuration.feeds.NewsDAOImpl;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import at.nullpointer.trayrss.configuration.feeds.db.News;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.hibernate.Session;

import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Monitors a feed
 * 
 * @author thefake
 * 
 */
public class FeedReaderThread implements Runnable {
	private Feed feedInfo = null;
	private Session session = null;
	private Long id = null;

	public FeedReaderThread(Feed feedInfo, Session session) {
		this.feedInfo = feedInfo;
		this.id = feedInfo.getId();
		this.session = session;
	}

	public Long getId() {
		return this.id;
	}

	public void run() {

		while (true) {
			boolean ok = false;
			try {

				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed = input.build(new XmlReader(new URL(feedInfo
						.getUrl())));

				List<SyndEntryImpl> content = feed.getEntries();

				for (Iterator<SyndEntryImpl> it = content.iterator(); it
						.hasNext();) {
					SyndEntryImpl node = it.next();

					News news = new News();
					news.setAuthor(node.getAuthor());
					news.setTitle(node.getTitle());
					news.setPublishedDate(node.getPublishedDate());
					news.setUpdatedDate(node.getUpdatedDate());
					news.setUri(node.getUri());
					news.setFeed(feedInfo);

					news.setUpdatedDate(new Date());

					NewsDAO newsDao = new NewsDAOImpl();
					News test = newsDao.getNewsByData(news, session);

					newsDao.save(news, session);

					if (test != null) {

						ReferenceCollection.LOG.debug("News "
								+ news.getPublishedDate() + " Test "
								+ test.getPublishedDate());
						if (news.getPublishedDate().equals(test.getPublishedDate())) {
							ReferenceCollection.LOG
									.debug("News Einträge wurden aktualisiert!");
						} else {
							ReferenceCollection.LOG
									.debug("News Einträge wurden NICHT aktualisiert!");
						}
					}
					// TODO DB Zugriff

					ReferenceCollection.TRAYNOTIFIER
							.addToNotify(news, feedInfo);
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

			// reload Feed - empty session
			session.clear();

			FeedDAO feeddao = new FeedDAO();
			long id = feedInfo.getId();
			feedInfo = null;
			feedInfo = feeddao.findFeedById(id, session);

			try {
				Thread.sleep(feedInfo.getIntervall() * 1000 * 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
