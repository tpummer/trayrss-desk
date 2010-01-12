package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import org.hibernate.Session;

import java.util.Collection;

public interface FeedDAOInt {

	public Collection<Feed> getFeeds(Session session);

	public Feed findFeedById(Long id, Session session);

	public void save(Feed feed, Session session);
	
	public void deleteById(Long id, Session session);

}