package at.nullpointer.trayrss.configuration.feeds;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;

public class FeedDAO implements FeedDAOInt {

	@Override
	public Feed findFeedById(Long id) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();

		Transaction tx = session.beginTransaction();
		Feed feed = (Feed) session.load(Feed.class, id);

		tx.commit();
		session.close();

		return feed;

	}

	@Override
	public Collection<Feed> getFeeds() {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();

		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select f from Feed f");
		List<Feed> feeds = (List<Feed>)query.list();

		tx.commit();
		session.close();

		return feeds;
	}

	@Override
	public void save(Feed feed) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();

		Transaction tx = session.beginTransaction();
		session.save(feed);

		tx.commit();
		session.close();

	}

}
