package at.nullpointer.trayrss.service.xml.in;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

import org.jdom2.Element;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class under test {@link FeedElementFactory}
 * 
 * @author Thomas Pummer
 * 
 * @since 1.6
 * 
 */
@ContextConfiguration
public class FeedElementFactoryTest
        extends AbstractTestNGSpringContextTests {

    /**
     * Class under test
     */
    @Inject
    @Setter
    private FeedElementFactory feedElementFactory;


    /**
     * Method under test {@link FeedElementFactory#createList(List)}
     * 
     */
    @Test( groups = { "unit" } )
    public void testCreatonWithNull() {

        List<FeedElement> createList = this.feedElementFactory.createList( null );

        Assert.assertEquals( createList.size(), 0, "List not correctly initialized" );

    }


    /**
     * Method under test {@link FeedElementFactory#createList(List)}
     * 
     */
    @Test( groups = { "unit" } )
    public void testCreatonWithListSizeNull() {

        List<FeedElement> createList = this.feedElementFactory.createList( new ArrayList<Element>() );

        Assert.assertEquals( createList.size(), 0, "List not correctly initialized" );

    }


    /**
     * Method under test {@link FeedElementFactory#createList(List)}
     * 
     */
    @Test( groups = { "unit" }, expectedExceptions = { IllegalArgumentException.class } )
    public void testCreatonWithWrongElementName() {

        ArrayList<Element> listToConvert = new ArrayList<>();

        listToConvert.add( new Element( "test" ) );

        this.feedElementFactory.createList( listToConvert );

        Assert.fail( "No Exception occured" );

    }


    /**
     * Method under test {@link FeedElementFactory#createList(List)}
     * 
     */
    @Test( groups = { "unit" } )
    public void testCreation() {

        ArrayList<Element> listToConvert = new ArrayList<>();

        listToConvert.add( new Element( "outline" ) );

        List<FeedElement> createList = this.feedElementFactory.createList( listToConvert );

        Assert.assertEquals( createList.size(), 1, "List not correctly created" );

    }

}
