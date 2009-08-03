package at.nullpointer.trayrss.test.gui.configframe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.nullpointer.trayrss.gui.configframe.FeedTableValidator;

public class FeedTableValidatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckURL() {
		FeedTableValidator testValidator = new FeedTableValidator(null);
		assertFalse(testValidator.checkURL("fehlerrrrrr"));
		assertTrue(testValidator.checkURL("http://trayrss.nullpointer.at"));
		assertTrue(testValidator.checkURL("http:///trayrss.nullpointer.at"));
		assertTrue(testValidator.checkURL("http://trayrss.*nullpointer.at"));
		assertTrue(testValidator.checkURL("http://trayrss.nullpointer"));
		assertFalse(testValidator.checkURL("trayrss.nullpointer.at"));
		
	}
}
