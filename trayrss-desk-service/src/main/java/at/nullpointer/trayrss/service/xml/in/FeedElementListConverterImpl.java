package at.nullpointer.trayrss.service.xml.in;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import at.nullpointer.trayrss.domain.Feed;

@Component
public class FeedElementListConverterImpl implements FeedElementListConverter {

    /* (non-Javadoc)
     * @see at.nullpointer.trayrss.service.xml.in.FeedElementListConverter#convertToFeedList(java.util.List)
     */
    @Override
    public List<Feed> convertToFeedList( List<FeedElement> listToCovert ) {

        List<Feed> result = new ArrayList<>();

        for ( FeedElement feedElement : listToCovert ) {
            Feed feed = feedElement.getFeed();
            result.add( feed );
        }

        return result;
    }

}