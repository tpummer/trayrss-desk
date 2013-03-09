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

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.gui.tablemodel.FeedTableValidator;

/**
 * Function under Test: {@link FeedTableValidator#checkUrl(String)}
 * 
 * @author Thomas Pummer
 * 
 */
public class FeedTableValidatorCheckUrlTest {

    /**
     * Tests {@link FeedTableValidator#checkUrl(String)}
     * 
     * @param description
     * @param url
     * @param expectedResult
     */
    @Test( groups = { "unit" }, dataProvider = "checkUrlParameter" )
    public void testCheckURL( final String description, final String url, final Boolean expectedResult ) {

        Assert.assertEquals( Boolean.valueOf( FeedTableValidator.checkUrl( url ) ), expectedResult, description );

    }


    /**
     * Parameters for the test of {@link FeedTableValidator#checkUrl(String)}
     * 
     * @return parameters of test: Object[][] of String description, String Url, Boolean result
     */
    @DataProvider( name = "checkUrlParameter" )
    public Object[][] data() {

        final Object[][] data = new Object[][] { { "URL should be wrong", "t\\est", Boolean.FALSE },
                { "Valid URL should not be detected as a Feed", "http://www.google.com", Boolean.FALSE },
                { "Feed-URL should be valid", "http://www.nullpointer.at/feed/", Boolean.TRUE } };
        return data;
    }
}
