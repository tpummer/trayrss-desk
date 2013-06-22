package at.nullpointer.trayrss.service;

import org.springframework.stereotype.Component;

import at.nullpointer.trayrss.domain.News;

/**
 * @see NewsService
 * 
 * @author Thomas Pummer
 * @since 1.4
 * 
 */
@Component( "newsService" )
public class NewsServiceImpl
        implements NewsService {

    /**
     * @see NewsService#increaseReadCount(News, int)
     */
    @Override
    public void increaseReadCount( News news, int increaseNumber ) {

        news.setReadCount( news.getReadCount() + increaseNumber );

    }

}
