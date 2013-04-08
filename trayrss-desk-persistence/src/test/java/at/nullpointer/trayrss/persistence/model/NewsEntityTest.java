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
}
