package at.nullpointer.trayrss.test.checks;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.nullpointer.trayrss.checks.CheckLib;
import at.nullpointer.trayrss.configuration.ReferenceCollection;

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

	@Test
	public void testLongMessageOk(){
		ReferenceCollection.config_long_valid_title = "titel";
		ReferenceCollection.config_long_valid_text = "Keine valide Nummer im Feld: %s";
		assertTrue(CheckLib.longMessage(CheckLib.checkLong("1235343"), "field"));
	}
	
	@Test
	public void testLongMessageDouble(){
		ReferenceCollection.config_long_valid_title = "titel";
		ReferenceCollection.config_long_valid_text = "Keine valide Nummer im Feld: %s";
		assertFalse(CheckLib.longMessage(CheckLib.checkLong("12353.43"), "field"));
	}
	
	@Test
	public void testLongMessageString(){
		ReferenceCollection.config_long_valid_title = "titel";
		ReferenceCollection.config_long_valid_text = "Keine valide Nummer im Feld: %s";
		assertFalse(CheckLib.longMessage(CheckLib.checkLong("12a35343"), "field"));
	}
}
