/*
 * TrayRSS - simply notification of feed information (c) 2009-2011 TrayRSS Developement Team visit the project at
 * http://trayrss.nullpointer.at/
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package at.nullpointer.trayrss.test.checks;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import at.nullpointer.trayrss.checks.TimeFrameValidator;

/**
 * Function under Test: {@link TimeFrameValidatorCheckTimeframesTest#checkTimeFrames(String)}
 * 
 * @author Thomas Pummer
 * 
 */
@RunWith( Parameterized.class )
public class TimeFrameValidatorCheckTimeframesTest {

    /**
     * Description of the Testcase
     */
    private String description;
    /**
     * URL under test
     */
    private String timeframes;
    /**
     * Expected result
     */
    private Boolean expectedResult;


    /**
     * Makes a parameterized test possible for {@link TimeFrameValidatorCheckTimeframesTest#checkTimeFrames(String)}
     * 
     * @param description
     * @param timeframes
     * @param expectedResult
     */
    public TimeFrameValidatorCheckTimeframesTest( String description, String timeframes, Boolean expectedResult ) {

        super();
        this.description = description;
        this.timeframes = timeframes;
        this.expectedResult = expectedResult;
    }


    /**
     * Tests {@link TimeFrameValidatorCheckTimeframesTest#checkTimeFrames(String)}
     */
    @Test
    public void testCheckURL() {

        assertEquals( description, TimeFrameValidator.checkTimeFrames( timeframes ), expectedResult );

    }


    /**
     * Parameters for the test of {@link TimeFrameValidatorCheckTimeframesTest#checkTimeFrames(String)}
     * 
     * @return
     */
    @Parameters
    public static Collection<Object[]> data() {

        Object[][] data = new Object[][] { { "One valid Timeframe", "1000-1600", Boolean.TRUE },
                { "Two valid Timeframes", "1000-1600 1730-2059", Boolean.TRUE },
                { "Timeframe with invalid letter", "10a00-1600", Boolean.FALSE },
                { "Timeframe with invalid first number", "2500-1600", Boolean.FALSE },
                { "Two Timeframes with invalid second number", "1000-0000 2500-1600", Boolean.FALSE },
                { "Two Timeframes with missing seperating space", "1000-11001500-1600", Boolean.FALSE },
                { "Two Timeframes with wrong seperating letter", "100a0-1600a1730-2059", Boolean.FALSE },
                { "Two Timeframes seperated with timeframe seperator", "100a0-1600-1730-2059", Boolean.FALSE },
                { "Two Timeframes with invalid letter in first frame", "100a0-1600 1730-2059", Boolean.FALSE },
                { "Two Timeframes with invalid letter in second frame", "1000-1600 1730-20a59", Boolean.FALSE },
                { "Timeframe with invalid seperator", "1000a1600", Boolean.FALSE },
                { "Timeframe with missing seperator", "10001600", Boolean.FALSE },
                { "Two Timeframe with missing seperator in the second frame", "0900-1200 10001600", Boolean.FALSE } };
        return Arrays.asList( data );
    }

}
