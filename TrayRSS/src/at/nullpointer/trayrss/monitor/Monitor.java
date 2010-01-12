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
package at.nullpointer.trayrss.monitor;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.FeedDAO;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Monitor{
	
	LinkedList<FeedReaderThread> monitoredFeeds;
	ExecutorService threadExecutor;
	FeedDAO feedDao;
	Session session;
	
	public Monitor(){
		
		monitoredFeeds = new LinkedList<FeedReaderThread>();
		threadExecutor = Executors.newFixedThreadPool(20);
		feedDao = new FeedDAO();

		session = ReferenceCollection.SESSION_FACTORY.openSession();
		
		loadFeeds();

	}
	
	private void loadFeeds(){
		
		List<Feed> feeds = (List<Feed>) feedDao.getFeeds(session);
		for(Iterator<Feed> it = feeds.iterator(); it.hasNext();){
			Feed feed = it.next();
			FeedReaderThread thread = new FeedReaderThread(feed, session);
			threadExecutor.execute(thread);
			monitoredFeeds.add(thread);
		}
		
	}
	
	public void feedChanged(){
		
	}
	
}