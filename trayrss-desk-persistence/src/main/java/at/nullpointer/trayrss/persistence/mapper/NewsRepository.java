package at.nullpointer.trayrss.persistence.mapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import lombok.Setter;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.persistence.dao.FeedEntityRepository;
import at.nullpointer.trayrss.persistence.dao.NewsEntityRepository;
import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Provides functionality to access or store news data
 * 
 * @author Thomas Pummer
 * @since 1.4
 * 
 */
@Repository
public class NewsRepository {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( NewsRepository.class );

    /**
     * NewsEntityRepository
     */
    @Inject
    @Setter
    private NewsEntityRepository newsEntityRepository;

    /**
     * FeedEntityRepository
     */
    @Inject
    @Setter
    private FeedEntityRepository feedEntityRepository;

    /**
     * ConversionService
     */
    @Inject
    @Setter
    private ConversionService conversionService;


    @Transactional
    public News retrieveNews( String newsUri ) {

        NewsEntity findOne = newsEntityRepository.findByUri( newsUri );

        News result = conversionService.convert( findOne, News.class );

        return result;
    }


    @Transactional
    public void saveOrUpdate( News news ) {

        NewsEntity newsEntityToPersist = newsEntityRepository.findByUri( news.getUri() );

        if ( newsEntityToPersist == null ) {
            newsEntityToPersist = new NewsEntity();
        }

        newsEntityToPersist.setAuthor( news.getAuthor() );
        newsEntityToPersist.setLastRead( news.getLastRead() );
        newsEntityToPersist.setPublishedDate( news.getPublishedDate() );
        newsEntityToPersist.setReadCount( news.getReadCount() );
        newsEntityToPersist.setTitle( news.getTitle() );
        newsEntityToPersist.setUpdatedDate( news.getUpdatedDate() );
        newsEntityToPersist.setUri( news.getUri() );

        FeedEntity findByUrl = feedEntityRepository.findByUrl( news.getFeedUrl() );
        newsEntityToPersist.setFeed( findByUrl );

        newsEntityRepository.saveAndFlush( newsEntityToPersist );

    }


    /**
     * Deletes news entries of a feed older than the given days
     * 
     * @param feedUrl
     * @param days
     */
    @Transactional
    public void deleteOlderThan( String feedUrl, int days ) {

        Calendar now = GregorianCalendar.getInstance();
        now.add( Calendar.DATE, -days );
        Date deadline = now.getTime();

        FeedEntity feedEntity = feedEntityRepository.findByUrl( feedUrl );

        Set<NewsEntity> news = feedEntity.getNews();

        for ( Iterator<NewsEntity> newsIterator = news.iterator(); newsIterator.hasNext(); ) {
            NewsEntity newsEntity = newsIterator.next();
            if ( newsEntity.getUpdatedDate().before( deadline ) ) {
                newsEntity.getFeed().getNews().remove( newsEntity );
                newsEntityRepository.delete( newsEntity );
            }
        }
    }

}
