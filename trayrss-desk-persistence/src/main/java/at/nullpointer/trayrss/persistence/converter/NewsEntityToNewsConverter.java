package at.nullpointer.trayrss.persistence.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
// TODO tests
@Component( "newsEntityToNewsConverter" )
public class NewsEntityToNewsConverter
        implements Converter<NewsEntity, News> {

    /**
     * Converts an {@link NewsEntity} to {@link News}
     */
    @Override
    @Transactional
    public News convert( NewsEntity source ) {

        final News target = new News();

        target.setAuthor( source.getAuthor() );
        target.setLastRead( source.getLastRead() );
        target.setPublishedDate( source.getPublishedDate() );
        target.setReadCount( source.getReadCount() );
        target.setTitle( source.getTitle() );
        target.setUpdatedDate( source.getUpdatedDate() );
        target.setUri( source.getUri() );

        final FeedEntity feed = source.getFeed();
        if ( feed != null ) {
            target.setFeedUrl( feed.getUrl() );
        }
        return target;
    }

}
