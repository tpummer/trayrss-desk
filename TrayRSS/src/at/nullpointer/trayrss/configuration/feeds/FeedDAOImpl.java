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
		Feed feed = (Feed) session.load(Feed.class, id);

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
		
		session.save(feed);

		tx.commit();
		session.close();

	}

	public void deleteById(Long id) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		
		
		Transaction tx = session.beginTransaction();
		
		session.delete(session.load(Feed.class, id));
		
		tx.commit();
		session.close();
		
	}


	
	

}
