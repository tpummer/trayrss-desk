package at.nullpointer.trayrss.persistence.dao;

import java.util.Date;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Class under Test {@link NewsEntityRepository}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@TransactionConfiguration( defaultRollback = true )
@ContextConfiguration
public class NewsEntityRepositoryTest
        extends AbstractTransactionalTestNGSpringContextTests {

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
     * Method under Test {@link NewsEntityRepository#saveAndFlush(NewsEntity)} with a {@link NewsEntity} linked to a
     * {@link FeedEntity}
     */
    @Test( groups = { "integration" } )
    public void testSaveWithFeed() {

        final FeedEntity feedEntity = new FeedEntity();
        feedEntity.setUrl( "delete" );
        feedEntity.setIntervall( 0L );
        feedEntity.setLastAction( new Date() );
        feedEntity.setMonitored( false );
        feedEntity.setName( "testName" );

        final NewsEntity newsEntity = new NewsEntity();
        newsEntity.setAuthor( "author" );
        newsEntity.setLastRead( new Date() );
        newsEntity.setPublishedDate( new Date() );
        newsEntity.setReadCount( 0L );
        newsEntity.setTitle( "newsTitle" );
        newsEntity.setUpdatedDate( new Date() );
        newsEntity.setUri( "newsUri4" );

        feedEntity.addNews( newsEntity );

        feedEntityRepository.saveAndFlush( feedEntity );

        final FeedEntity findByUrl = feedEntityRepository.findByUrl( "delete" );
        Assert.assertEquals( findByUrl.getNews().size(), 1, "News not found" );

    }

}
