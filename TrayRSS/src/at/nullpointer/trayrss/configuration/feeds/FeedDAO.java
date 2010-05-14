package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.feeds.db.Feed;

import java.util.Collection;

public interface FeedDAO {

	public Collection<Feed> getFeeds();

	public Feed findFeedById(Long id);

	public void save(Feed feed);
	
	public void deleteById(Long id);

}