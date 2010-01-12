package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class FeedDAO implements FeedDAOInt {

	public Feed findFeedById(Long id, Session session) {

		Transaction tx = session.beginTransaction();
		Feed feed = (Feed) session.load(Feed.class, id);

		tx.commit();

		return feed;

	}

	public Collection<Feed> getFeeds(Session session) {
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select f from Feed f");
		List<Feed> feeds = (List<Feed>)query.list();

		tx.commit();

		return feeds;
	}

	public void save(Feed feed, Session session) {

		Transaction tx = session.beginTransaction();
		
		session.save(feed);

		tx.commit();

	}

	public void deleteById(Long id, Session session) {
		
		Transaction tx = session.beginTransaction();
		
		session.delete(session.load(Feed.class, id));
		
		tx.commit();
		
	}


	
	

}
