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

import at.nullpointer.trayrss.gui.tablemodel.FeedTableValidator;

/**
 * Function under Test: {@link FeedTableValidator#checkUrl(String)}
 * 
 * @author Thomas Pummer
 * 
 */
@RunWith( Parameterized.class )
public class FeedTableValidatorCheckUrlTest {

    /**
     * Description of the Testcase
     */
    private String description;
    /**
     * URL under test
     */
    private String url;
    /**
     * Expected result
     */
    private Boolean expectedResult;


    /**
     * Makes a parameterized test possible for {@link FeedTableValidator#checkUrl(String)}
     * 
     * @param description
     * @param url
     * @param expectedResult
     */
    public FeedTableValidatorCheckUrlTest( String description, String url, Boolean expectedResult ) {

        super();
        this.description = description;
        this.url = url;
        this.expectedResult = expectedResult;
    }


    /**
     * Parameters for the test of {@link FeedTableValidator#checkUrl(String)}
     * 
     * @return
     */
    @Parameters
    public static Collection<Object[]> data() {

        Object[][] data = new Object[][] { { "URL should be wrong", "t\\est", Boolean.FALSE },
                { "Valid URL should not be detected as a Feed", "http://www.google.com", Boolean.FALSE },
                { "Feed-URL should be valid", "http://www.nullpointer.at/feed/", Boolean.TRUE } };
        return Arrays.asList( data );
    }


    /**
     * Tests {@link FeedTableValidator#checkUrl(String)}
     */
    @Test
    public void testCheckURL() {

        assertEquals( description, FeedTableValidator.checkUrl( url ), expectedResult );

    }
}
