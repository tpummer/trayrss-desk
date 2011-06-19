/*
    TrayRSS - simply notification of feed information
    (c) 2009-2011 TrayRSS Developement Team
    visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss.test.checks;

import at.nullpointer.trayrss.checks.CheckLib;
import at.nullpointer.trayrss.configuration.ReferenceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckLibTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckLongOk() {
		assertTrue(CheckLib.checkLong("1235343"));
	}
	
	@Test
	public void testCheckLongString() {
		assertFalse(CheckLib.checkLong("1a235343"));
	}
	
	@Test
	public void testCheckLongDouble() {
		assertFalse(CheckLib.checkLong("1235343.23"));
	}
}
