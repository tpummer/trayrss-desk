package at.nullpointer.trayrss.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.nullpointer.trayrss.persistence.model.FeedEntity;

/**
 * Provides Accessors and Persistence Functions for {@link FeedEntity}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@Repository
public interface FeedEntityRepository
        extends JpaRepository<FeedEntity, Long> {

    /**
     * Retrieves an News Entity by it's unique url
     * 
     * @param url
     * @return FeedEntity
     */
    FeedEntity findByUrl( String url );

}
