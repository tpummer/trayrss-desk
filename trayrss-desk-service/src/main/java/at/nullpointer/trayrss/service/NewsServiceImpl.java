package at.nullpointer.trayrss.service;

import org.springframework.stereotype.Component;

import at.nullpointer.trayrss.domain.News;

@Component( "newsService" )
public class NewsServiceImpl
        implements NewsService {

    @Override
    public void increaseReadCount( News news, int i ) {

        // TODO Auto-generated method stub

    }

}
