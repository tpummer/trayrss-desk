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
package at.nullpointer.trayrss.messages;

import java.util.Locale;

import junit.framework.TestCase;

public class MessageResolverImplTest extends TestCase {

	public static MessageResolver mr;

	@Override
	protected void setUp() throws Exception {
		mr = new MessageResolverImpl("at.nullpointer.trayrss.messages.testmessages");
		super.setUp();
	}

	public void testGetString() {
		String erg = mr.getString("test", "testname");
		assertEquals("test eng", erg);
	}

	public void testChanceLocale() {
		mr.chanceLocale(new Locale("de"));
		String erg = mr.getString("test", "testname");
		assertEquals("test ger", erg);
	}

}
