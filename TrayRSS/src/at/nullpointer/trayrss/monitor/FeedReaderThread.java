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
import at.nullpointer.trayrss.configuration.feeds.NewsDAO;
import at.nullpointer.trayrss.configuration.feeds.NewsDAOImpl;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import at.nullpointer.trayrss.configuration.feeds.db.News;
import at.nullpointer.trayrss.configuration.timeframes.TimeValidation;
import at.nullpointer.trayrss.configuration.timeframes.TimeValidationImpl;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Monitors a feed
 * 
 * @author thefake
 * 
 */
public class FeedReaderThread implements Runnable {
	private Feed feedInfo = null;
	private Long id = null;

	public FeedReaderThread(Feed feedInfo) {
		this.feedInfo = feedInfo;
		this.id = feedInfo.getId();
	}

	public Long getId() {
		return this.id;
	}

	public void run() {
		
		TimeValidation timeValidation = new TimeValidationImpl();

		while (true) {

			if(timeValidation.isAllowed()){
				
				boolean ok = false;
							
				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed;
				List<SyndEntryImpl> content = null;
				try {
					feed = input.build(new XmlReader(new URL(feedInfo
							.getUrl())));
					content = (List<SyndEntryImpl>) feed.getEntries(); 
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FeedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                for (SyndEntryImpl node : (Iterable<SyndEntryImpl>) content) {
                	
                	News news = prepareNode(node);

                    NewsDAO newsDao = new NewsDAOImpl();
                    
                    News test = newsDao.getNewsByData(news);

                    if(test != null && test.equals(news)){
                        news = test;
                        news.increaseReadCount(1);
                        ReferenceCollection.LOG
                                    .debug("Feed "+getId()+": News Eintrag "+news.getTitle()+" von "+node.getUri()+" wurden aktualisiert!");
                    } else {

                        ReferenceCollection.LOG
                                    .debug("Feed "+getId()+": Neuer Newseintrag "+news.getTitle()+" von "+node.getUri());
                    }

                    if(news.getReadCount() < ReferenceCollection.DISPLAY_COUNT){
                        ReferenceCollection.TRAYNOTIFIER
                                .addToNotify(news, feedInfo);
                        news.setLastRead(new Date());
                    }
                    newsDao.save(news);
                }

				ok = true;
				if (!ok) {
					ReferenceCollection.LOG
	                .debug("FeedReader reads and prints any RSS/Atom feed type.");
					ReferenceCollection.LOG
	                .debug("The first parameter must be the URL of the feed to read.");
				}
			} else {
				ReferenceCollection.LOG.debug("Not within an allowded Time!");
			}
			
			deleteOldNews();

			try {
				Thread.sleep(feedInfo.getIntervall() * 1000 * 60);
			} catch (InterruptedException e) {
				ReferenceCollection.LOG.debug("FeedReaderThread interrupted!");
			}
		}
	}

	private void deleteOldNews() {
		NewsDAO newsDao = new NewsDAOImpl();
		
		newsDao.deleteOlderThanTwoMonth(feedInfo.getId());
		
	}

	private News prepareNode(SyndEntryImpl node) {
		
		News news = new News();
        news.setAuthor(node.getAuthor());
        news.setTitle(node.getTitle());
        news.setPublishedDate(node.getPublishedDate());
        news.setUpdatedDate(node.getUpdatedDate());
        news.setUri(node.getUri());
        news.setFeed(feedInfo);

        news.setUpdatedDate(new Date());
		return news;
	}

}
