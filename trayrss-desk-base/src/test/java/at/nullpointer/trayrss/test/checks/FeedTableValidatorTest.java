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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import at.nullpointer.trayrss.checks.FeedTableValidator;

public class FeedTableValidatorTest {

	@Test
	public void testCheckURLFalse() {
		Object[] eins = {null, null, "test", null, null};
		
		Object[][] test = {eins};
		assertFalse(FeedTableValidator.checkURL(test, 2));
		
		
	}
	
	@Test
	public void testCheckURLValidUrl() {
		Object[] eins = {null, null, "http://god.sprossenwanne.at", null, null};
		
		Object[][] test = {eins};
		assertFalse(FeedTableValidator.checkURL(test, 2));
		
		
	}
	
	@Test
	public void testCheckURLValidFeed() {
		Object[] eins = {null, null, "http://www.nullpointer.at/feed/", null, null};
		
		Object[][] test = {eins};
		assertTrue(FeedTableValidator.checkURL(test, 2));
		
	}
}
