package at.nullpointer.trayrss.service.xml.in;

import java.util.List;

import at.nullpointer.trayrss.domain.Feed;

public interface FeedElementListConverter {

    public List<Feed> convertToFeedList( List<FeedElement> listToCovert );

}