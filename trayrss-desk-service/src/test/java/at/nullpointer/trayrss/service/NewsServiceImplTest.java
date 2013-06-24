package at.nullpointer.trayrss.service;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.domain.News;

/**
 * Class under test {@link NewsServiceImpl}
 * 
 * @author Thomas Pummer
 * 
 * @since 1.6
 * 
 */
@ContextConfiguration
public class NewsServiceImplTest
        extends AbstractTestNGSpringContextTests {

    /**
     * NewsService to test
     */
    @Inject
    @Setter
    private NewsService newsService;


    /**
     * Method under test {@link NewsService#increaseReadCount(at.nullpointer.trayrss.domain.News, int)}
     */
    @Test( groups = { "unit" } )
    public void testIncreaseNewsCount() {

        News news = new News();
        news.setReadCount( 2L );
        newsService.increaseReadCount( news, 3 );

        Assert.assertEquals( news.getReadCount(), Long.valueOf( 5L ),
                "IncreaseReadCount did not increase in a correct manner 2+3 has to be 5" );

    }

}
