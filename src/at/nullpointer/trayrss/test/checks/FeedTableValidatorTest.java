package at.nullpointer.trayrss.test.checks;

import at.nullpointer.trayrss.checks.FeedTableValidator;
import at.nullpointer.trayrss.configuration.ReferenceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeedTableValidatorTest {

	@Before
	public void setUp() throws Exception {
		ReferenceCollection.config_url_valid_title = "URL invalid!";
		ReferenceCollection.config_url_valid_text = "Die URL:\n   %s\nin Zeile %s ist kein gültiger Feed (RSS oder Atom)!\nBitte korrigieren Sie den Pfad.";
	}

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
