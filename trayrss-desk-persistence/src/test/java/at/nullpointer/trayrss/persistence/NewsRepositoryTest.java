package at.nullpointer.trayrss.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import at.nullpointer.trayrss.persistence.dao.NewsEntityRepository;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Class under Test {@link NewsRepository}
 * 
 * @author Thomas Pummer
 * @since 1.4
 * 
 */
@TransactionConfiguration( defaultRollback = true )
@ContextConfiguration
public class NewsRepositoryTest
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * NewsEntityRepository
     */
    @Inject
    @Setter
    private NewsEntityRepository newsEntityRepository;

    /**
     * NewsRepository
     */
    @Inject
    @Setter
    private NewsRepository newsRepository;

    /**
     * FeedRepository
     */
    @Inject
    @Setter
    private FeedRepository feedRepository;


    /**
	 * Method under Test {@link NewsRepository#saveOrUpdate(at.nullpointer.trayrss.domain.News)} Save it
	 */
	@Test( groups = { "integration" } )
	public void testSave() {
	
	    News news = new News();
	    news.setAuthor( "author" );
	    news.setFeedUrl( "test" ); // no valid feed stored before, will be saved without link to news
	    news.setLastRead( new Date() );
	    news.setPublishedDate( new Date() );
	    news.setReadCount( 0L );
	    news.setTitle( "newsTitle" );
	    news.setUpdatedDate( new Date() );
	    news.setUri( "newsUri" );
	    newsRepository.saveOrUpdate( news );
	
	    List<NewsEntity> findAll = newsEntityRepository.findAll();
	    if ( findAll == null ) {
	        Assert.fail( "Nothing stored" );
	    }
	
	    boolean isStored = false;
	    for ( NewsEntity newsEntity : findAll ) {
	        if ( newsEntity.getUri().equals( "newsUri" ) ) {
	            isStored = true;
	        }
	    }
	    Assert.assertTrue( isStored, "No valid News stored" );
	}


	/**
	 * Method under Test {@link NewsRepository#saveOrUpdate(News)} Saving a news entity with feed
	 */
	@Test( groups = { "integration" }, dependsOnMethods = { "testSave" } )
	public void testSaveWithFeed() {
	
	    Feed feed = new Feed();
	    feed.setUrl( "saveWith" );
	    feed.setIntervall( 0L );
	    feed.setLastAction( new Date() );
	    feed.setMonitored( false );
	    feed.setName( "testName" );
	    feedRepository.saveOrUpdate( feed );
	
	    News news = new News();
	    news.setAuthor( "author" );
	    news.setFeedUrl( "saveWith" );
	    news.setLastRead( new Date() );
	    news.setPublishedDate( new Date() );
	    news.setReadCount( 0L );
	    news.setTitle( "newsTitle" );
	    news.setUpdatedDate( new Date() );
	    news.setUri( "newsUriWith" );
	    newsRepository.saveOrUpdate( news );
	
	    News retrieveNews = newsRepository.retrieveNews( "newsUriWith" );
	    Assert.assertEquals( retrieveNews.getFeedUrl(), "saveWith", "Not an identical feed" );
	
	    Feed retrieveFeed = feedRepository.retrieveFeed( "saveWith" );
	    List<News> newsList = retrieveFeed.getNews();
	    Assert.assertEquals( newsList.size(), 1 );
	    News newsFromFeed = newsList.get( 0 );
	    Assert.assertEquals( newsFromFeed.getFeedUrl(), "saveWith", "Not an identical feed at news from feed" );
	
	}


	/**
	 * Method under Test {@link NewsRepository#saveOrUpdate(at.nullpointer.trayrss.domain.News)} Update it
	 */
	@Test( groups = { "integration" }, dependsOnMethods = { "testSaveWithFeed" } )
	public void testUpdate() {
	
	    News news = new News();
	    news.setAuthor( "author" );
	    news.setFeedUrl( "test" ); // no valid feed stored before, will be saved without link to news
	    news.setLastRead( new Date() );
	    news.setPublishedDate( new Date() );
	    news.setReadCount( 0L );
	    news.setTitle( "newsTitle" );
	    news.setUpdatedDate( new Date() );
	    news.setUri( "newsUri2" );
	    newsRepository.saveOrUpdate( news );
	
	    news.setTitle( "newsTitleNew" );
	    newsRepository.saveOrUpdate( news );
	
	    List<NewsEntity> findAll = newsEntityRepository.findAll();
	
	    boolean isStored = false;
	    boolean oldNotFound = true;
	    for ( NewsEntity newsEntity : findAll ) {
	        if ( newsEntity.getUri().equals( "newsUri2" ) && newsEntity.getTitle().equals( "newsTitleNew" ) ) {
	            isStored = true;
	        }
	        if ( newsEntity.getUri().equals( "newsUri2" ) && newsEntity.getTitle().equals( "newsTitle" ) ) {
	            oldNotFound = false;
	        }
	    }
	    Assert.assertTrue( isStored && oldNotFound, "No valid Feed stored" );
	}


	/**
	 * Method under Test {@link NewsRepository#retrieveNews(String)}
	 */
	@Test( groups = { "integration" }, dependsOnMethods = { "testUpdate" } )
	public void testRetrieveNews() {
	
	    News news = new News();
	    news.setAuthor( "author" );
	    news.setFeedUrl( "test" ); // no valid feed stored before, will be saved without link to news
	    news.setLastRead( new Date() );
	    news.setPublishedDate( new Date() );
	    news.setReadCount( 0L );
	    news.setTitle( "newsTitle" );
	    news.setUpdatedDate( new Date() );
	    news.setUri( "newsUri3" );
	    newsRepository.saveOrUpdate( news );
	
	    News newsRetrieved = newsRepository.retrieveNews( "newsUri3" );
	    boolean oneStored = false;
	
	    if ( newsRetrieved.getUri().equals( "newsUri3" ) ) {
	        oneStored = true;
	    }
	
	    Assert.assertTrue( oneStored, "Nothing retrieved" );
	}


	/**
     * Method under Test {@link NewsRepository#deleteOlderThan(String, int)}
     */
    @Test( groups = { "integration" }, dependsOnMethods = { "testDeleteOlderThan" } )
    public void testDeleteOlderThan() {

        Feed feed = new Feed();
        feed.setUrl( "delete" );
        feed.setIntervall( 0L );
        feed.setLastAction( new Date() );
        feed.setMonitored( false );
        feed.setName( "testName" );
        feedRepository.saveOrUpdate( feed );

        Calendar now = GregorianCalendar.getInstance();
        now.add( Calendar.DATE, -15 );

        News news = new News();
        news.setAuthor( "author" );
        news.setFeedUrl( "delete" );
        news.setLastRead( new Date() );
        news.setPublishedDate( new Date() );
        news.setReadCount( 0L );
        news.setTitle( "newsTitle" );
        news.setUpdatedDate( now.getTime() );
        news.setUri( "newsUri4" );
        newsRepository.saveOrUpdate( news );

        News newsRetrieved = newsRepository.retrieveNews( "newsUri4" );
        boolean oneStored = false;

        if ( newsRetrieved.getUri().equals( "newsUri4" ) ) {
            oneStored = true;
        }

        Feed retrieveFeed = feedRepository.retrieveFeed( "delete" );

        Assert.assertEquals( retrieveFeed.getNews().size(), 1, "No News Stored" );

        Assert.assertTrue( oneStored, "Nothing retrieved" );

        newsRepository.deleteOlderThan( "delete", 30 );

        News newsRetrievedAfterDelete30 = newsRepository.retrieveNews( "newsUri4" );
        boolean oneStoredAfterDelete30 = false;

        if ( newsRetrievedAfterDelete30 != null ) {
            oneStoredAfterDelete30 = true;
        }

        Assert.assertTrue( oneStoredAfterDelete30, "Nothing retrieved" );

        newsRepository.deleteOlderThan( "delete", 10 );

        News newsRetrievedAfterDelete10 = newsRepository.retrieveNews( "newsUri4" );
        boolean oneStoredAfterDelete10 = false;

        if ( newsRetrievedAfterDelete10 != null ) {
            oneStoredAfterDelete10 = true;
        }

        Assert.assertTrue( !oneStoredAfterDelete10, "Something retrieved" );

    }

}
