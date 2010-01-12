/**
    RSSTray - simply alerting at new Feed Information
    Copyright (C) 2009 Thomas Pummer
    conatct me fake (at) sprossenwanne.at

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

import at.nullpointer.trayrss.checks.DateValidator;
import at.nullpointer.trayrss.configuration.ReferenceCollection;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DateValidatorTest {

	@Before
	public void setUp() throws Exception {
		ReferenceCollection.config_vacation_valid_title = "Enddate before startdate";
		ReferenceCollection.config_vacation_valid_text = "The end of your vacation is before the start of the vacation! This is not possible.";
	}

	@Test
	public void testCheckDatesStartNull(){
		assertTrue(DateValidator.checkDates(null, new Date()));
		
	}
	
	@Test
	public void testCheckDatesEndNull(){
		assertTrue(DateValidator.checkDates(new Date(), null));
	}
	
	@Test
	public void testCheckDatesBothNull(){
		assertTrue(DateValidator.checkDates(null, null));
	}
	
	@Test
	public void testCheckDatesWrong(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		Date end = null;
		Date start = null;
		try {
			end = sdf.parse("01.01.1901");
			start = sdf.parse("01.01.2009");
		} catch (ParseException e) {
			fail("ParseError");
		}
		assertFalse(DateValidator.checkDates(start, end));
	}
	
	@Test
	public void testCheckDatesEqual(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		Date end = null;
		Date start = null;
		try {
			end = sdf.parse("01.01.1901");
			start = end;
		} catch (ParseException e) {
			fail("ParseError");
		}
		assertFalse(DateValidator.checkDates(start, end));
	}
	
	@Test
	public void testCheckDatesOk(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		Date end = null;
		Date start = null;
		try {
			end = sdf.parse("01.01.2001");
			start = sdf.parse("01.01.2000");
		} catch (ParseException e) {
			fail("ParseError");
		}
		assertTrue(DateValidator.checkDates(start, end));
	}

}
