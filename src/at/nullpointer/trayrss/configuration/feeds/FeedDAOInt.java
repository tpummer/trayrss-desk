package at.nullpointer.trayrss.configuration.feeds;

import java.util.Collection;

import at.nullpointer.trayrss.configuration.feeds.db.Feed;

public interface FeedDAOInt {

	public Collection<Feed> getFeeds();

	public Feed findFeedById(Long id);

	public void save(Feed feed);

}