package at.nullpointer.trayrss.test.checks;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.nullpointer.trayrss.checks.TimeFrameValidator;
import at.nullpointer.trayrss.configuration.ReferenceCollection;

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
