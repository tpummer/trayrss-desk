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
package monitor;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import configuration.ReferenceCollection;
import configuration.feeds.db.Feed;

public class Monitor implements Runnable {
	
	public Monitor(){

	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ExecutorService threadExecutor = Executors.newFixedThreadPool(5);
//		for (Iterator<Feed> it = rssUrlSave.iterator(); it.hasNext();) {
//			FeedReaderThread feedReader = new FeedReaderThread(it.next(),
//					ReferenceCollection.TRAY_ICON);
//			// TODO Thread wo speichern zum interruppten
//			threadExecutor.execute(feedReader);
		while(true){ 
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("End");
				break;
			}
			System.out.println("Ping");
			
			Session session = ReferenceCollection.SESSION_FACTORY.openSession();
			
			Transaction tx = session.beginTransaction();
			Feed test = new Feed();
			test.setIntervall(new Long(2));
			test.setLastAction(new Date());
			test.setName("ha!");
			test.setUrl("das");
			
			session.save(test);
			tx.commit();
			session.close();

		}
//		}
	}
}