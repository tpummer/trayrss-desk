/**
    TrayRSS - simply alerting at new Feed Information
	visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss.test.checks;

import at.nullpointer.trayrss.checks.TimeFrameValidator;
import at.nullpointer.trayrss.configuration.ReferenceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeFrameValidatorTest {

	@Before
	public void setUp() throws Exception {
		ReferenceCollection.config_timeframe_valid_title = "titel";
		ReferenceCollection.config_timeframe_valid_text = "Unkorrektes Zeitfenster in %s";
	}

	@Test
	public void testCheckTimeFramesOkEins() {
		String input = "1000-1600";
		assertTrue(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesOkZwei() {
		String input = "1000-1600 1730-2059";
		assertTrue(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailEinsText() {
		String input = "10a00-1600";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailEinsZahl() {
		String input = "2500-1600";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailZweiZahl() {
		String input = "1000-00002500-1600";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailZweiTextImErsten() {
		String input = "100a0-1600 1730-2059";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailZweiTextImZweiten() {
		String input = "1000-1600 1730-20a59";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailUebergangString() {
		String input = "100a0-1600a1730-2059";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailUebergangStrich() {
		String input = "100a0-1600-1730-2059";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailUebergangKeinh() {
		String input = "100a0-16001730-2059";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailZeittrennerEins() {
		String input = "1000a1600";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailZeittrennerEinsFehlt() {
		String input = "10001600";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}
	
	@Test
	public void testCheckTimeFramesFailZeittrennerZweiFehlt() {
		String input = "0900-1200 10001600";
		assertFalse(TimeFrameValidator.checkTimeFrames(input));
	}

	@Test
	public void testTimeFramesMessageOK() {
		assertTrue(TimeFrameValidator.timeFramesMessage(TimeFrameValidator.checkTimeFrames("2000-2100"), "feld"));
	}
	
	@Test
	public void testTimeFramesMessageFail() {
		assertFalse(TimeFrameValidator.timeFramesMessage(TimeFrameValidator.checkTimeFrames("2000-21a00"), "feld"));
	}

}
