package at.nullpointer.trayrss.persistence.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Class under test {@link NewsEntityToNewsConverter}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@TransactionConfiguration( defaultRollback = true )
@ContextConfiguration
public class NewsEntityToNewsConverterTest
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * NewsEntityToNewsConverter
     */
    @Inject
    @Setter
    private NewsEntityToNewsConverter newsConverter;


    /**
     * Method under Test {@link NewsEntityToNewsConverter#convert(NewsEntity)}
     */
    @Test( groups = { "unit" } )
    public void testConvert() {

        Calendar now = GregorianCalendar.getInstance();
        Date updateDate = now.getTime();
        now.add( Calendar.DATE, -1 );
        Date publishedDate = now.getTime();
        now.add( Calendar.DATE, -1 );
        Date lastAction = new Date();

        FeedEntity feedEntity = new FeedEntity();
        feedEntity.setUrl( "testUrl" );

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setUri( "testUriNews" );
        newsEntity.setAuthor( "author" );
        newsEntity.setFeed( feedEntity );
        newsEntity.setId( 1L );
        newsEntity.setLastRead( lastAction );
        newsEntity.setPublishedDate( publishedDate );
        newsEntity.setReadCount( 20L );
        newsEntity.setTitle( "title" );
        newsEntity.setUpdatedDate( updateDate );

        News convert = newsConverter.convert( newsEntity );

        Assert.assertEquals( convert.getAuthor(), newsEntity.getAuthor(), "Author not equals" );
        Assert.assertEquals( convert.getUri(), newsEntity.getUri(), "Uri not equals" );
        Assert.assertEquals( convert.getFeedUrl(), newsEntity.getFeed().getUrl(), "FeedUrl not equals" );
        Assert.assertEquals( convert.getTitle(), newsEntity.getTitle(), "Title not equals" );
        Assert.assertEquals( convert.getLastRead(), newsEntity.getLastRead(), "LastRead not equals" );
        Assert.assertEquals( convert.getPublishedDate(), newsEntity.getPublishedDate(), "PublishedDate not equals" );
        Assert.assertEquals( convert.getReadCount(), newsEntity.getReadCount(), "ReadCount not equals" );
        Assert.assertEquals( convert.getUpdatedDate(), newsEntity.getUpdatedDate(), "UpdatedDate not equals" );
    }
}
