/*
 * TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
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
import org.testng.annotations.Test;

import at.nullpointer.trayrss.checks.CheckLib;

/**
 * Class under Test: {@link CheckLib}
 * 
 * @author Thomas Pummer
 * 
 */
public class CheckLibTest {

    /**
     * Tests Number {@link CheckLib#checkLong(String)}
     */
    @Test( groups = { "unit" } )
    public void testCheckLongOk() {

        Assert.assertTrue( CheckLib.checkLong( "1235343" ), "Checks if '1235343' can be parsed as long" );
    }


    /**
     * Error Test String {@link CheckLib#checkLong(String)}
     */
    @Test( groups = { "unit" } )
    public void testCheckLongString() {

        Assert.assertFalse( CheckLib.checkLong( "1a235343" ), "Checks if '1a235343' can not be parsed as long" );
    }


    /**
     * Error Test Dezimal {@link CheckLib#checkLong(String)}
     */
    @Test( groups = { "unit" } )
    public void testCheckLongDouble() {

        Assert.assertFalse( CheckLib.checkLong( "1235343.23" ), "Checks if '1235343.23' can not be parsed as long" );
        Assert.assertFalse( CheckLib.checkLong( "1235343,23" ), "Checks if '1235343,23' can not be parsed as long" );
    }
}
