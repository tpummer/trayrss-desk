package at.nullpointer.trayrss.service;

import at.nullpointer.trayrss.domain.News;

public interface NewsService {

    void increaseReadCount( News news, int i );

}
