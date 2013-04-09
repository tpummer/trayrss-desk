package at.nullpointer.trayrss.persistence.model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class under Test {@link NewsEntity}
 * 
 * @author Thomas Pummer
 * @since 1.4
 * 
 */
public class NewsEntityTest {

    /**
     * Long value 10
     */
    private static final long LONG_10 = 10L;
    /**
     * Long value 20
     */
    private static final long LONG_20 = 20L;
    /**
     * Long value 30
     */
    private static final long LONG_30 = 30L;


    /**
     * Method under test {@link NewsEntity#increaseReadCount(long)}
     */
    @Test( groups = { "unit" } )
    public void testIncreaseReadCount() {

        final NewsEntity news = new NewsEntity();
        Assert.assertEquals( Long.valueOf( 0L ), news.getReadCount(), "ReadCount not correct initialized" );
        news.increaseReadCount( LONG_30 );
        Assert.assertEquals( Long.valueOf( LONG_30 ), news.getReadCount(), "ReadCount not increased by 30" );
        news.increaseReadCount( -LONG_10 );
        Assert.assertEquals( Long.valueOf( LONG_20 ), news.getReadCount(), "ReadCount not increased by -10" );

    }


    /**
     * Method under Test {@link NewsEntity#setFeed(FeedEntity)}
     */
    @Test( groups = { "unit" } )
    public void testSetFeed() {

        FeedEntity feed = new FeedEntity();
        feed.setUrl( "testFeed" );

        NewsEntity newsOne = new NewsEntity();
        newsOne.setUri( "newsOne" );

        NewsEntity newsTwo = new NewsEntity();
        newsTwo.setUri( "newsTwo" );

        NewsEntity newsLikeOne = new NewsEntity();
        newsLikeOne.setUri( "newsOne" );

        newsOne.setFeed( feed );
        Assert.assertEquals( 1, feed.getNews().size(), "No Feed liked" );
        newsTwo.setFeed( feed );
        Assert.assertEquals( 2, feed.getNews().size(), "No additional Feed liked" );
        newsLikeOne.setFeed( feed );
        Assert.assertEquals( 2, feed.getNews().size(), "False Feed liked" );
    }
}
