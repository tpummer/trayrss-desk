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
 * Function under Test: {@link FeedTableValidator#checkName(String)}
 * 
 * @author Thomas Pummer
 * 
 */
public class FeedTableValidatorCheckNameTest {

    /**
     * Parameters for the test of {@link FeedTableValidator#checkName(String)}
     * 
     * @return parameters of test: Object[][] of String description, String Name, Boolean result
     */
    @DataProvider( name = "checkNameDataProvider" )
    public Object[][] data() {

        final Object[][] data = new Object[][] { { "Valid", "t\\est", Boolean.TRUE },
                { "Valid with space", "tes t", Boolean.TRUE }, { "Invalid 0 size string", "", Boolean.FALSE },
                { "Invalid space string", "    ", Boolean.FALSE }, { "Invalid null string", null, Boolean.FALSE } };
        return data;
    }


    /**
     * Tests {@link FeedTableValidator#checkName(String)}
     * 
     * @param description
     * @param name
     * @param expectedResult
     */
    @Test( groups = { "unit" }, dataProvider = "checkNameDataProvider" )
    public void testCheckName( final String description, final String name, final Boolean expectedResult ) {

        Assert.assertEquals( Boolean.valueOf( FeedTableValidator.checkName( name ) ), expectedResult, description );

    }
}
