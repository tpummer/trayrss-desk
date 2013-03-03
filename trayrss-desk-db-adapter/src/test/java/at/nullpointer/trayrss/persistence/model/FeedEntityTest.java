package at.nullpointer.trayrss.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Class under Test {@link FeedEntity}
 * 
 * @author Thomas Pummer
 * 
 */
public class FeedEntityTest {

    /**
     * The Entity under test
     */
    private transient FeedEntity firstEntity;
    /**
     * A reference to the same entity as firstEntity
     */
    private transient FeedEntity reference;
    /**
     * An Entity with the same values as firstEntity
     */
    private transient FeedEntity sameValues;
    /**
     * An Entity with a different intervall than firstEntity
     */
    private transient FeedEntity differenIntervallValues;
    /**
     * An Entity with a different action than firstEntity
     */
    private transient FeedEntity differenActionValues;
    /**
     * An Entity with a different name than firstEntity
     */
    private transient FeedEntity differenNameValues;
    /**
     * An Entity with a different url than firstEntity
     */
    private transient FeedEntity differenUrlValues;
    /**
     * An Entity with a different id than firstEntity
     */
    private transient FeedEntity sameKeyValuesButNotId;
    /**
     * An Entity with a different monitored than firstEntity
     */
    private transient FeedEntity sameKeyValuesButNotMonitored;
    /**
     * An Entity with different news than firstEntity
     */
    private transient FeedEntity sameKeyValuesButNotNews;
    /**
     * An Entity different than firstEntity
     */
    private transient FeedEntity secondEntity;

    /**
     * The name of firstEntity
     */
    private static final String NAMEA = "testA";
    /**
     * The different name to firstEntity
     */
    private static final String NAMEB = "testB";
    /**
     * The url of firstEntity
     */
    private static final String URLA = "noUrl";
    /**
     * The different url to firstEntity
     */
    private static final String URLB = "noUrlB";
    /**
     * Milliseconds of a day
     */
    private static final Long MILLI_OF_A_DAY = 86400000L;


    /**
     * Creates the Entitys for the Tests
     */
    @BeforeTest
    public void createFeedEntries() {

        final NewsEntity news = new NewsEntity();
        final List<NewsEntity> newsList = new ArrayList<NewsEntity>();
        newsList.add( news );

        firstEntity = createFeedEntity( 1L, 1L, new Date( System.currentTimeMillis() ), true, NAMEA, URLA, newsList );
        reference = firstEntity;
        sameValues = createFeedEntity( 1L, 1L, firstEntity.getLastAction(), true, NAMEA, URLA, newsList );
        differenIntervallValues = createFeedEntity( 1L, 2L, firstEntity.getLastAction(), true, NAMEA, URLA, newsList );
        differenActionValues = createFeedEntity( 1L, 1L, new Date( System.currentTimeMillis() - MILLI_OF_A_DAY ), true,
                NAMEA, URLA, newsList );
        differenNameValues = createFeedEntity( 1L, 1L, firstEntity.getLastAction(), true, NAMEB, URLA, newsList );
        differenUrlValues = createFeedEntity( 1L, 1L, firstEntity.getLastAction(), true, NAMEA, URLB, newsList );
        sameKeyValuesButNotId = createFeedEntity( 2L, 1L, firstEntity.getLastAction(), true, NAMEA, URLA, newsList );
        sameKeyValuesButNotMonitored = createFeedEntity( 1L, 1L, firstEntity.getLastAction(), false, NAMEA, URLA,
                newsList );
        sameKeyValuesButNotNews = createFeedEntity( 1L, 1L, firstEntity.getLastAction(), false, NAMEA, URLA, null );
        secondEntity = createFeedEntity( 2L, 2L, new Date(), false, NAMEB, URLB, null );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the same Entity
     */
    @Test( groups = { "unit" } )
    public void testSameEntitiyEquals() {

        Assert.assertTrue( firstEntity.equals( firstEntity ), "An Feed Entity is equals with itself!" );

    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the same Entity referenced on 2 places
     */
    @Test( groups = { "unit" } )
    public void testSameEntitiyReferencedEquals() {

        Assert.assertTrue( reference.equals( firstEntity ), "An Reference to the same Feed Entity is equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with the same values
     */
    @Test( groups = { "unit" } )
    public void testSameValuesEquals() {

        Assert.assertTrue( sameValues.equals( firstEntity ), "An Feed Entity with the same values equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with a different Interfall
     */
    @Test( groups = { "unit" } )
    public void testDifferentIntervallEquals() {

        Assert.assertFalse( differenIntervallValues.equals( firstEntity ),
                "An Feed Entity with a different Intervall is not equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with a different LastAction
     */
    @Test( groups = { "unit" } )
    public void testDifferentActionEquals() {

        Assert.assertFalse( differenActionValues.equals( firstEntity ),
                "An Feed Entity with a different Action Date is not equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with a different name
     */
    @Test( groups = { "unit" } )
    public void testDifferentNameEquals() {

        Assert.assertFalse( differenNameValues.equals( firstEntity ),
                "An Feed Entity with a different Name is not equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with a different url
     */
    @Test( groups = { "unit" } )
    public void testDifferentUrlEquals() {

        Assert.assertFalse( differenUrlValues.equals( firstEntity ),
                "An Feed Entity with a different Url is not equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with a different id
     */
    @Test( groups = { "unit" } )
    public void testDifferentIdEquals() {

        Assert.assertTrue( sameKeyValuesButNotId.equals( firstEntity ),
                "An Feed Entity with a different id is equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with a different monitored
     */
    @Test( groups = { "unit" } )
    public void testDifferentMonitoredEquals() {

        Assert.assertTrue( sameKeyValuesButNotMonitored.equals( firstEntity ),
                "An Feed Entity with a different monitored is equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the an Entity with different news
     */
    @Test( groups = { "unit" } )
    public void testDifferentNewsEquals() {

        Assert.assertTrue( sameKeyValuesButNotNews.equals( firstEntity ),
                "An Feed Entity with a different News is equals with it!" );
    }


    /**
     * Tests {@link FeedEntity#equals(Object)} with the a different entity
     */
    @Test( groups = { "unit" } )
    public void testDifferentEntitiyEquals() {

        Assert.assertFalse( secondEntity.equals( firstEntity ), "An different Feed Entity is not equals with it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the same Entity
     */
    @Test( groups = { "unit" } )
    public void testSameEntitiyHashCode() {

        Assert.assertEquals( firstEntity.hashCode(), firstEntity.hashCode(),
                "An Feed Entity has the same hashCode as itself!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the same Entity referenced on 2 places
     */
    @Test( groups = { "unit" } )
    public void testSameEntitiyReferencedHashCode() {

        Assert.assertEquals( reference.hashCode(), firstEntity.hashCode(),
                "An Reference to the same Feed Entity has the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with the same values
     */
    @Test( groups = { "unit" } )
    public void testSameValuesHashCode() {

        Assert.assertEquals( sameValues.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with the same values has the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with a different intervall
     */
    @Test( groups = { "unit" } )
    public void testDifferentIntervallHashCode() {

        Assert.assertNotEquals( differenIntervallValues.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with a different Intervall hasn't the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with a different lastaction
     */
    @Test( groups = { "unit" } )
    public void testDifferentActionHashCode() {

        Assert.assertNotEquals( differenActionValues.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with a different Action Date hasn't the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with a different name
     */
    @Test( groups = { "unit" } )
    public void testDifferentNameHashCode() {

        Assert.assertNotEquals( differenNameValues.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with a different Name hasn't the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with a different url
     */
    @Test( groups = { "unit" } )
    public void testDifferentUrlHashCode() {

        Assert.assertNotEquals( differenUrlValues.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with a different Url hasn't the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with a different id
     */
    @Test( groups = { "unit" } )
    public void testDifferentIdHashCode() {

        Assert.assertEquals( sameKeyValuesButNotId.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with a different id has the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with a different monitored
     */
    @Test( groups = { "unit" } )
    public void testDifferentMonitoredHashCode() {

        Assert.assertEquals( sameKeyValuesButNotMonitored.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with a different monitored has the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the an Entity with different news
     */
    @Test( groups = { "unit" } )
    public void testDifferentNewsHashCode() {

        Assert.assertEquals( sameKeyValuesButNotNews.hashCode(), firstEntity.hashCode(),
                "An Feed Entity with a different News has the same hashCode as it!" );

    }


    /**
     * Tests {@link FeedEntity#hashCode()} with the a different entity
     */
    @Test( groups = { "unit" } )
    public void testDifferentEntitiyHashCode() {

        Assert.assertNotEquals( secondEntity.hashCode(), firstEntity.hashCode(),
                "An different Feed Entity hasn't the same hashCode as it!" );

    }


    private FeedEntity createFeedEntity( final Long id, final Long intervall, final Date action,
            final Boolean monitored, final String name, final String url, final List<NewsEntity> news ) {

        final FeedEntity result = new FeedEntity();
        result.setId( id );
        result.setIntervall( intervall );
        result.setLastAction( action );
        result.setMonitored( monitored );
        result.setName( name );
        result.setUrl( url );
        result.setNews( news );
        return result;
    }

}
