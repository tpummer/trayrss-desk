package at.nullpointer.trayrss.configuration.feeds;

import java.util.Collection;

import org.hibernate.Session;

import at.nullpointer.trayrss.configuration.feeds.db.Feed;

public interface FeedDAOInt {

	public Collection<Feed> getFeeds(Session session);

	public Feed findFeedById(Long id, Session session);

	public void save(Feed feed, Session session);
	
	public void deleteById(Long id, Session session);

}