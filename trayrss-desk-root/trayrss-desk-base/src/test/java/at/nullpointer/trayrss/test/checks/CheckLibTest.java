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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
    @Test
    public void testCheckLongOk() {

        assertTrue( "Checks if '1235343' can be parsed as long", CheckLib.checkLong( "1235343" ) );
    }


    /**
     * Error Test String {@link CheckLib#checkLong(String)}
     */
    @Test
    public void testCheckLongString() {

        assertFalse( "Checks if '1a235343' can not be parsed as long", CheckLib.checkLong( "1a235343" ) );
    }


    /**
     * Error Test Dezimal {@link CheckLib#checkLong(String)}
     */
    @Test
    public void testCheckLongDouble() {

        assertFalse( "Checks if '1235343.23' can not be parsed as long", CheckLib.checkLong( "1235343.23" ) );
        assertFalse( "Checks if '1235343,23' can not be parsed as long", CheckLib.checkLong( "1235343,23" ) );
    }
}
