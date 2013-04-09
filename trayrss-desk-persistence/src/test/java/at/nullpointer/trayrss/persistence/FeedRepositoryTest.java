package at.nullpointer.trayrss.persistence;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.persistence.FeedRepository;
import at.nullpointer.trayrss.persistence.NewsRepository;
import at.nullpointer.trayrss.persistence.dao.FeedEntityRepository;
import at.nullpointer.trayrss.persistence.model.FeedEntity;

/**
 * Class under Test {@link FeedRepository}
 * 
 * @author Thomas Pummer
 * @since 1.4
 * 
 */
@TransactionConfiguration( defaultRollback = true )
@ContextConfiguration
public class FeedRepositoryTest
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * FeedRepository
     */
    @Inject
    @Setter
    private FeedRepository feedRepository;

    /**
     * NewsRepository
     */
    @Inject
    @Setter
    private NewsRepository newsRepository;

    /**
     * FeedEntityRepository
     */
    @Inject
    @Setter
    private FeedEntityRepository feedEntityRepository;


    /**
     * Method under Test {@link FeedRepository#delete(String)}
     */
    @Test( groups = { "integration" }, dependsOnMethods = { "testSave" } )
    public void testDelete() {

        Feed feed = new Feed();
        feed.setUrl( "test" );
        feed.setIntervall( 0L );
        feed.setLastAction( new Date() );
        feed.setMonitored( false );
        feed.setName( "testName" );
        feedRepository.saveOrUpdate( feed );

        List<FeedEntity> findAll = feedEntityRepository.findAll();
        if ( findAll == null ) {
            Assert.fail( "Nothing stored" );
        }

        boolean isStored = false;
        for ( FeedEntity feedEntity : findAll ) {
            if ( feedEntity.getUrl().equals( "test" ) ) {
                isStored = true;
            }
        }
        Assert.assertTrue( isStored, "No valid Feed stored" );

        feedRepository.delete( "test" );

        findAll = feedEntityRepository.findAll();

        boolean isNoLongerStored = true;
        for ( FeedEntity feedEntity : findAll ) {
            if ( feedEntity.getUrl().equals( "test" ) ) {
                isNoLongerStored = false;
            }
        }
        Assert.assertTrue( isNoLongerStored, "Feed stored" );
    }


    /**
     * Method under Test {@link FeedRepository#retrieveFeed(String)}
     */
    @Test( groups = { "integration" }, dependsOnMethods = { "testSave" } )
    public void testRetrieveFeed() {

        Feed feed = new Feed();
        feed.setUrl( "test" );
        feed.setIntervall( 0L );
        feed.setLastAction( new Date() );
        feed.setMonitored( false );
        feed.setName( "testName" );
        feedRepository.saveOrUpdate( feed );

        Feed feedRetrieved = feedRepository.retrieveFeed( "test" );
        boolean oneStored = false;

        if ( feedRetrieved.getUrl().equals( "test" ) ) {
            oneStored = true;
        }

        Assert.assertTrue( oneStored, "Nothing retrieved" );
    }


    /**
     * Method under Test {@link FeedRepository#retrieveFeeds()}
     */
    @Test( groups = { "integration" }, dependsOnMethods = { "testSave" } )
    public void testGetFeeds() {

        Feed feed = new Feed();
        feed.setUrl( "test" );
        feed.setIntervall( 0L );
        feed.setLastAction( new Date() );
        feed.setMonitored( false );
        feed.setName( "testName" );
        feedRepository.saveOrUpdate( feed );

        Feed feedTwo = new Feed();
        feedTwo.setUrl( "test2" );
        feedTwo.setIntervall( 0L );
        feedTwo.setLastAction( new Date() );
        feedTwo.setMonitored( false );
        feedTwo.setName( "testName2" );
        feedRepository.saveOrUpdate( feedTwo );

        Collection<Feed> feeds = feedRepository.retrieveFeeds();

        boolean oneStored = false;
        boolean twoStored = false;
        for ( Feed feedRetrieved : feeds ) {
            if ( feedRetrieved.getUrl().equals( "test" ) ) {
                oneStored = true;
            } else
                if ( feedRetrieved.getUrl().equals( "test2" ) ) {
                    twoStored = true;
                }
        }
        Assert.assertTrue( oneStored && twoStored, "Not all retrieved" );
    }


    /**
     * Method under Test {@link FeedRepository#saveOrUpdate(at.nullpointer.trayrss.domain.Feed)} Save it
     */
    @Test( groups = { "integration" } )
    public void testSave() {

        Feed feed = new Feed();
        feed.setUrl( "test" );
        feed.setIntervall( 0L );
        feed.setLastAction( new Date() );
        feed.setMonitored( false );
        feed.setName( "testName" );
        feedRepository.saveOrUpdate( feed );

        List<FeedEntity> findAll = feedEntityRepository.findAll();
        if ( findAll == null ) {
            Assert.fail( "Nothing stored" );
        }

        boolean isStored = false;
        for ( FeedEntity feedEntity : findAll ) {
            if ( feedEntity.getUrl().equals( "test" ) ) {
                isStored = true;
            }
        }
        Assert.assertTrue( isStored, "No valid Feed stored" );
    }


    /**
     * Method under Test {@link FeedRepository#saveOrUpdate(at.nullpointer.trayrss.domain.Feed)} Update it
     */
    @Test( groups = { "integration" }, dependsOnMethods = { "testSave" } )
    public void testUpdate() {

        Feed feed = new Feed();
        feed.setUrl( "test" );
        feed.setIntervall( 0L );
        feed.setLastAction( new Date() );
        feed.setMonitored( false );
        feed.setName( "testName" );
        feedRepository.saveOrUpdate( feed );

        List<FeedEntity> findAll = feedEntityRepository.findAll();
        boolean updateableFeedExists = false;
        if ( findAll != null ) {
            for ( FeedEntity feedEntity : findAll ) {
                if ( feedEntity.getUrl().equals( "test" ) && feedEntity.getName().equals( "testName" ) ) {
                    updateableFeedExists = true;
                }
            }
            Assert.assertTrue( updateableFeedExists, "Feed to update not stored" );
        }

        feed.setName( "testNameNew" );
        feedRepository.saveOrUpdate( feed );

        findAll = feedEntityRepository.findAll();
        if ( findAll == null ) {
            Assert.fail( "Nothing stored" );
        }

        boolean isStored = false;
        boolean oldNotFound = true;
        for ( FeedEntity feedEntity : findAll ) {
            if ( feedEntity.getUrl().equals( "test" ) && feedEntity.getName().equals( "testNameNew" ) ) {
                isStored = true;
            }
            if ( feedEntity.getUrl().equals( "test" ) && feedEntity.getName().equals( "testName" ) ) {
                oldNotFound = false;
            }
        }
        Assert.assertTrue( isStored && oldNotFound, "No valid Feed stored" );
    }


    /**
     * Method under test {@link FeedRepository#delete(String)} and check if the news items linked with the feed are
     * deleted too
     */
    @Test( groups = { "integration" }, dependsOnMethods = { "testSave", "testRetrieveFeed", "testDelete" } )
    public void testDeleteFeedWithNews() {

        Feed feed = new Feed();
        feed.setUrl( "testDeleteWith" );
        feed.setIntervall( 0L );
        feed.setLastAction( new Date() );
        feed.setMonitored( false );
        feed.setName( "testName" );
        feedRepository.saveOrUpdate( feed );

        News news = new News();
        news.setAuthor( "author" );
        news.setFeedUrl( "testDeleteWith" );
        news.setLastRead( new Date() );
        news.setPublishedDate( new Date() );
        news.setReadCount( 0L );
        news.setTitle( "newsTitle" );
        news.setUpdatedDate( new Date() );
        news.setUri( "newsUriWithFeed" );
        newsRepository.saveOrUpdate( news );

        List<FeedEntity> findAll = feedEntityRepository.findAll();
        if ( findAll == null ) {
            Assert.fail( "Nothing stored" );
        }

        boolean isFeedStored = false;
        for ( FeedEntity feedEntity : findAll ) {
            if ( feedEntity.getUrl().equals( "testDeleteWith" ) ) {
                isFeedStored = true;
            }
        }
        Assert.assertTrue( isFeedStored, "No valid Feed stored" );

        News retrieveNews = newsRepository.retrieveNews( "newsUriWithFeed" );
        if ( retrieveNews == null ) {
            Assert.fail( "No News stored" );
        }

        feedRepository.delete( "testDeleteWith" );

        findAll = feedEntityRepository.findAll();

        boolean isNoLongerStored = true;
        for ( FeedEntity feedEntity : findAll ) {
            if ( feedEntity.getUrl().equals( "testDeleteWith" ) ) {
                isNoLongerStored = false;
            }
        }
        Assert.assertTrue( isNoLongerStored, "Feed stored" );

        News retrieveNewsAfterDeletion = newsRepository.retrieveNews( "newsUriWithFeed" );
        if ( retrieveNewsAfterDeletion != null ) {
            Assert.fail( "News stored" );
        }
    }
}
