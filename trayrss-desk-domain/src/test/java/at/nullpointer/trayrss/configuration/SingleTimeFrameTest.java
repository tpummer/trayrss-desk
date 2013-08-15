package at.nullpointer.trayrss.configuration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.configuration.model.SingleTimeFrame;

/**
 * Class under test {@link SingleTimeFrame}
 * 
 * @author Thomas Pummer
 * @since 1.5
 * 
 */
public class SingleTimeFrameTest {

    /**
     * Method under test {@link SingleTimeFrame#SingleTimeFrame(String[])} with null arg
     */
    @Test( groups = { "unit" }, expectedExceptions = { IllegalArgumentException.class } )
    public void testSingleTimeFrameConstructorNullArg() {

        new SingleTimeFrame( null );

    }


    /**
     * Method under test {@link SingleTimeFrame#SingleTimeFrame(String[])} with arg with to small size
     */
    @Test( groups = { "unit" }, expectedExceptions = { IllegalArgumentException.class } )
    public void testSingleTimeFrameConstructorNotEnoughEntries() {

        String[] input = { "0101" };
        new SingleTimeFrame( input );

    }


    /**
     * Method under test {@link SingleTimeFrame#SingleTimeFrame(String[])} with arg with to big size
     */
    @Test( groups = { "unit" }, expectedExceptions = { IllegalArgumentException.class } )
    public void testSingleTimeFrameConstructorToMuchEntries() {

        String[] input = { "0101", "0102", "0103" };
        new SingleTimeFrame( input );

    }


    /**
     * Method under test {@link SingleTimeFrame#SingleTimeFrame(String[])} with arg with no valid data
     */
    @Test( groups = { "unit" }, expectedExceptions = { IllegalArgumentException.class } )
    public void testSingleTimeFrameConstructorInvalidEntries() {

        String[] input = { "dudu", "dada" };
        new SingleTimeFrame( input );

    }


    /**
     * Method under test {@link SingleTimeFrame#SingleTimeFrame(String[])}
     */
    @Test( groups = { "unit" } )
    public void testSingleTimeFrameConstructorValidEntries() {

        String[] input = { "0100", "0200" };
        SingleTimeFrame singleTimeFrame = new SingleTimeFrame( input );
        Assert.assertEquals(singleTimeFrame.getStartHour(), 1);
        Assert.assertEquals(singleTimeFrame.getStartMin(), 0);
        Assert.assertEquals(singleTimeFrame.getEndHour(), 2);
        Assert.assertEquals(singleTimeFrame.getEndMin(), 0);

    }


    /**
     * Parameters for the test of {@link SingleTimeFrame#toString()}
     * 
     * @return parameters of test: Object[][] of String description, String Name, Boolean result
     */
    @DataProvider( name = "toStringDataProvider" )
    public Object[][] testToStringData() {

        final Object[][] data = new Object[][] { { "0101-0202", "Fail: all values should have one digit" },
                { "1101-0202", "Fail: one values should had more than one digit" },
                { "1111-0202", "Fail: two values should had more than one digit" },
                { "1111-1202", "Fail: three values should had more than one digit" },
                { "1111-1212", "Fail: all values should had more than one digit" } };
        return data;
    }


    /**
     * Method under test {@link SingleTimeFrame#toString()}
     * 
     * @param toSplit
     * @param message
     */
    @Test( groups = { "unit" }, dataProvider = "toStringDataProvider" )
    public void testToString( String toSplit, String message ) {

        SingleTimeFrame singleTimeFrame = new SingleTimeFrame( toSplit.split( "-" ) );
        Assert.assertEquals( singleTimeFrame.toString(), toSplit, message );

    }

}
