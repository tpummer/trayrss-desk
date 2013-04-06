package at.nullpointer.trayrss.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.persistence.model.FeedEntity;

/**
 * Class under Test {@link FeedEntityRepository}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@TransactionConfiguration( defaultRollback = true )
@ContextConfiguration
public class FeedEntityRepositoryTest
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * FeedEntityRepository
     */
    @Inject
    @Setter
    private FeedEntityRepository feedRepository;


    /**
     * Method under Test {@link FeedEntityRepository#save(FeedEntity)}
     */
    @Test( groups = "integration" )
    public void testSaveFeed() {

        FeedEntity feed = new FeedEntity();
        feed.setName( "test" );
        feed.setIntervall( 1L );
        feed.setLastAction( new Date() );
        feed.setMonitored( true );
        feed.setUrl( "testUrl" );

        feedRepository.save( feed );

        List<FeedEntity> findAll = feedRepository.findAll();
        FeedEntity matching = null;
        for ( FeedEntity f : findAll ) {
            if ( f.equals( feed ) ) {
                matching = f;
            }
        }
        Assert.assertNotNull( matching, "No matching news found" );

    }

}
