package at.nullpointer.trayrss.service.xml.in;

import java.util.List;

import at.nullpointer.trayrss.domain.Feed;

/**
 * Convertig FeedElement Lists
 * 
 * @author Thomas Pummer
 * 
 * @since 1.6
 * 
 */
public interface FeedElementListConverter {

    /**
     * Transform an List of type FeedElement to type Feed
     * 
     * @param listToCovert
     * @return List<Feed>
     */
    List<Feed> convertToFeedList( List<FeedElement> listToCovert );

}