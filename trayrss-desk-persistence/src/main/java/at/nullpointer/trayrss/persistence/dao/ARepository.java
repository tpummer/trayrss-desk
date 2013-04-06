package at.nullpointer.trayrss.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.nullpointer.trayrss.persistence.model.A;

/**
 * Provides Accessors and Persistence Functions for {@link A}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@Repository
public interface ARepository
        extends JpaRepository<A, Long> {

}
