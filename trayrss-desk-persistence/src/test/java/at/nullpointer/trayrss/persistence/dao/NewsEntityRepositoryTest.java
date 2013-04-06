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

    @Inject
    @Setter
    private NewsEntityRepository newsEntityRepository;

    @Inject
    @Setter
    private FeedEntityRepository feedEntityRepository;


    @Test( groups = { "integration" } )
    public void testSaveWithFeed() {

        FeedEntity feedEntity = new FeedEntity();
        feedEntity.setUrl( "delete" );
        feedEntity.setIntervall( 0L );
        feedEntity.setLastAction( new Date() );
        feedEntity.setMonitored( false );
        feedEntity.setName( "testName" );
        feedEntityRepository.saveAndFlush( feedEntity );

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setAuthor( "author" );
        newsEntity.setFeed( feedEntity );
        newsEntity.setLastRead( new Date() );
        newsEntity.setPublishedDate( new Date() );
        newsEntity.setReadCount( 0L );
        newsEntity.setTitle( "newsTitle" );
        newsEntity.setUpdatedDate( new Date() );
        newsEntity.setUri( "newsUri4" );
        newsEntityRepository.saveAndFlush( newsEntity );

        FeedEntity findByUrl = feedEntityRepository.findByUrl( "delete" );
        Assert.assertEquals( findByUrl.getNews().size(), 1, "News not found" );

    }

}
