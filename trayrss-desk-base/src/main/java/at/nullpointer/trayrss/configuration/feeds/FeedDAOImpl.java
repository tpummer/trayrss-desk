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
package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class FeedDAOImpl implements FeedDAO {

	public Feed findFeedById(Long id) {
		
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		
		Transaction tx = session.beginTransaction();
		Feed feed = (Feed) session.get(Feed.class, id);

		tx.commit();
		session.close();

		return feed;

	}

	public Collection<Feed> getFeeds() {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select f from Feed f");
		List<Feed> feeds = (List<Feed>)query.list();

		tx.commit();
		session.close();

		return feeds;
	}

	public void save(Feed feed) {
		
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		

		Transaction tx = session.beginTransaction();
		
		if(feed.getId() != null && session.load(Feed.class, feed.getId()) != null){
			session.update(feed);
		} else {
			session.save(feed);
	
		}

		tx.commit();
		session.close();

	}

	public void deleteById(Long id) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		
		
		Transaction tx = session.beginTransaction();
		
		//Object deletedFeed = session.load(Feed.class, id);
		
		//session.delete(deletedFeed);
		//session.delete("select f from Feed f where id = "+id.longValue());
		
		String hqlN = "delete from News n where feed_id = "+id.longValue();
		Query queryN = session.createQuery(hqlN);
		queryN.executeUpdate();
		
		String hql = "delete from Feed f where id = "+id.longValue();
		Query query = session.createQuery(hql);
		query.executeUpdate();
		
		tx.commit();
		session.close();
		
	}


	
	

}
