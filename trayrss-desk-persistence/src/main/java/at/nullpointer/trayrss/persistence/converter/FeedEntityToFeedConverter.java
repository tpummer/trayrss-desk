package at.nullpointer.trayrss.persistence.converter;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Converter for {@link FeedEntity} to {@link Feed}
 * 
 * @author Thomas Pummer
 * @since 1.4
 * 
 */
@Component( "feedEntityToFeedConverter" )
public class FeedEntityToFeedConverter
        implements Converter<FeedEntity, Feed> {

    // /**
    // * ConversionService
    // */
    // @Inject
    // @Setter
    // private ConversionService conversionService;

    @Inject
    @Setter
    private NewsEntityToNewsConverter newsEntityToNewsConverter;


    /**
     * Converts an {@link FeedEntity} to {@link Feed} TODO Sync (maybe within Feed?)
     */
    @Override
    @Transactional
    public Feed convert( FeedEntity source ) {

        final Feed target = new Feed();

        target.setIntervall( source.getIntervall() );
        target.setLastAction( source.getLastAction() );
        target.setMonitored( source.getMonitored() );
        target.setName( source.getName() );
        target.setUrl( source.getUrl() );
        final List<News> newsList = new LinkedList<>();
        for ( NewsEntity newsEntity : source.getNews() ) {
            newsList.add( newsEntityToNewsConverter.convert( newsEntity ) );
        }
        target.setNews( newsList );

        return target;
    }

}
