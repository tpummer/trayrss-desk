package at.nullpointer.trayrss.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Provides Accessors and Persistence Functions for {@link NewsEntity}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@Repository
public interface NewsEntityRepository
        extends JpaRepository<NewsEntity, Long> {

    /**
     * Retrieves an News Entity by it's unique uri
     * 
     * @param uri
     * @return NewsEntity
     */
    NewsEntity findByUri( String uri );

}
