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
 * Function under Test: {@link FeedTableValidator#checkName(String)}
 * 
 * @author Thomas Pummer
 * 
 */
@RunWith( Parameterized.class )
public class FeedTableValidatorCheckNameTest {

    /**
     * Description of the Testcase
     */
    private String description;
    /**
     * name under test
     */
    private String name;
    /**
     * Expected result
     */
    private Boolean expectedResult;


    /**
     * Makes a parameterized test possible for {@link FeedTableValidator#checkName(String)}
     * 
     * @param description
     * @param name
     * @param expectedResult
     */
    public FeedTableValidatorCheckNameTest( String description, String name, Boolean expectedResult ) {

        super();
        this.description = description;
        this.name = name;
        this.expectedResult = expectedResult;
    }


    /**
     * Parameters for the test of {@link FeedTableValidator#checkName(String)}
     * 
     * @return
     */
    @Parameters
    public static Collection<Object[]> data() {

        Object[][] data = new Object[][] { { "Valid", "t\\est", Boolean.TRUE },
                { "Valid with space", "tes t", Boolean.TRUE }, { "Invalid 0 size string", "", Boolean.FALSE },
                { "Invalid space string", "    ", Boolean.FALSE }, { "Invalid null string", null, Boolean.FALSE } };
        return Arrays.asList( data );
    }


    /**
     * Tests {@link FeedTableValidator#checkName(String)}
     */
    @Test
    public void testCheckURL() {

        assertEquals( description, FeedTableValidator.checkName( name ), expectedResult );

    }
}
