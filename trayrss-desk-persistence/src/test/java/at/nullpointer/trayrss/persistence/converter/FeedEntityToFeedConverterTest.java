package at.nullpointer.trayrss.persistence.converter;

import java.util.Date;

import javax.inject.Inject;

import lombok.Setter;

import org.easymock.EasyMock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Class under Test {@link FeedEntityToFeedConverter}
 * 
 * @author Thomas Pummer
 * @since 1.4
 */
@TransactionConfiguration( defaultRollback = true )
@ContextConfiguration
public class FeedEntityToFeedConverterTest
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * FeedEntityToFeedConverter under Test
     */
    @Inject
    @Setter
    private FeedEntityToFeedConverter feedConverter;

    /**
     * Mocked NewsConverter
     */
    private NewsEntityToNewsConverter newsConverterMock;


    @BeforeMethod( groups = { "unit" } )
    public void before() {

        newsConverterMock = EasyMock.createMock( NewsEntityToNewsConverter.class );
        feedConverter.setNewsEntityToNewsConverter( newsConverterMock );

    }


    /**
     * Method under Test {@link FeedEntityToFeedConverter#convert(at.nullpointer.trayrss.persistence.model.FeedEntity)}
     */
    @Test( groups = { "unit" } )
    public void testConvert() {

        Date lastAction = new Date();

        FeedEntity feedEntity = new FeedEntity();
        feedEntity.setId( 1L );
        feedEntity.setIntervall( 3L );
        feedEntity.setLastAction( lastAction );
        feedEntity.setMonitored( true );
        feedEntity.setName( "testEntity" );
        feedEntity.setUrl( "testUrl" );

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setUri( "testUriNews" );
        feedEntity.addNews( newsEntity );

        News newsConverted = new News();
        newsConverted.setUri( "testUriNews" );

        EasyMock.expect( newsConverterMock.convert( newsEntity ) ).andReturn( newsConverted );
        EasyMock.replay( newsConverterMock );

        Feed convert = feedConverter.convert( feedEntity );

        Assert.assertEquals( convert.getName(), feedEntity.getName(), "Feedname not equals" );
        Assert.assertEquals( convert.getUrl(), feedEntity.getUrl(), "Url not equals" );
        Assert.assertEquals( convert.getIntervall(), feedEntity.getIntervall(), "Intervall not equals" );
        Assert.assertEquals( convert.getLastAction(), feedEntity.getLastAction(), "LastAction not equals" );
        Assert.assertEquals( convert.getMonitored(), feedEntity.getMonitored(), "Monitored not equals" );
        Assert.assertEquals( convert.getNews().get( 0 ).getUri(), newsEntity.getUri(), "News not added" );

        EasyMock.verify( newsConverterMock );

    }

}
