package at.nullpointer.trayrss.persistence.converter;

import java.util.Calendar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Converter for {@link NewsEntity} to {@link News}
 * 
 * @author Thomas Pummer
 * @since 1.4
 * 
 */
@Component( "newsEntityToNewsConverter" )
public class NewsEntityToNewsConverter
        implements Converter<NewsEntity, News> {

    /**
     * Converts an {@link NewsEntity} to {@link News}
     */
    @Override
    public News convert( NewsEntity source ) {

        final News target = new News();

        target.setAuthor( source.getAuthor() );

        final Calendar lastRead = Calendar.getInstance();
        lastRead.setTimeInMillis( source.getLastRead().getTime() );
        target.setLastRead( lastRead.getTime() );

        final Calendar publishedDate = Calendar.getInstance();
        publishedDate.setTimeInMillis( source.getPublishedDate().getTime() );
        target.setPublishedDate( publishedDate.getTime() );

        target.setReadCount( source.getReadCount() );
        target.setTitle( source.getTitle() );

        final Calendar updatedDate = Calendar.getInstance();
        updatedDate.setTimeInMillis( source.getUpdatedDate().getTime() );
        target.setUpdatedDate( updatedDate.getTime() );

        target.setUri( source.getUri() );

        final FeedEntity feed = source.getFeed();
        if ( feed != null ) {
            target.setFeedUrl( feed.getUrl() );
        }
        return target;
    }

}
