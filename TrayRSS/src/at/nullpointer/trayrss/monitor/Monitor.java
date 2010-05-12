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

import at.nullpointer.trayrss.configuration.feeds.FeedDAOImpl;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Monitor{
	
	LinkedList<FeedReaderThread> monitoredFeeds;
	ExecutorService threadExecutor;
	FeedDAOImpl feedDao;
	
	public Monitor(){
		
		monitoredFeeds = new LinkedList<FeedReaderThread>();
		threadExecutor = Executors.newFixedThreadPool(20);
		feedDao = new FeedDAOImpl();

		loadFeeds();

	}
	
	public void loadFeeds(){
		
		List<Feed> feeds = (List<Feed>) feedDao.getFeeds();
        for (Feed feed : feeds) {
            FeedReaderThread thread = new FeedReaderThread(feed);
            threadExecutor.execute(thread);
            monitoredFeeds.add(thread);
        }
		
	}
	
	public void feedChanged() {

		stopAll(60);
		loadFeeds();

	}

	public void stopAll(int seconds) {
		try {
			if (!threadExecutor.awaitTermination(seconds, TimeUnit.SECONDS)) {
				threadExecutor.shutdownNow(); // Cancel currently executing
												// tasks
				// Wait a while for tasks to respond to being cancelled
				if (!threadExecutor.awaitTermination(seconds, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			threadExecutor.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
		
		threadExecutor = Executors.newFixedThreadPool(20);
		
	}
	
}