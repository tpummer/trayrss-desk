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
package EntityStorage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testclass JUnit 4.0 for the EntityCollection
 * @author Thomas Pummer
 *
 */
public class TestEntityCollection {
	EntityCollection col;
	EntityInformation a;
	EntityInformation b;
	EntityInformation c;

	@Before
	public void setUp() throws Exception {
		a = new EntityInformation();
		a.setTest("A");
		b = new EntityInformation();
		b.setTest("B");
		c = new EntityInformation();
		c.setTest("C");
		col = new EntityCollection();
	}

	@Test
	public void testSaveEntity() {
		assertEquals(col.size(), 0);
		col.saveEntity("A", a);
		assertEquals(col.getEntityByUniqueKey("A"), a);
		assertEquals(col.size(), 1);
		col.saveEntity("B", b);
		assertEquals(col.getEntityByUniqueKey("B"), b);
		assertEquals(col.size(), 2);
		col.saveEntity("C", c);
		assertEquals(col.getEntityByUniqueKey("C"), c);
		assertEquals(col.size(), 3);
	}

	@Test
	public void testGetEntityByUniqueKey() {
		col.saveEntity("A", a);
		assertEquals(col.getEntityByUniqueKey("A"), a);
		col.saveEntity("B", b);
		assertEquals(col.getEntityByUniqueKey("B"), b);
		col.saveEntity("C", c);
		assertEquals(col.getEntityByUniqueKey("C"), c);
		EntityInformation d = col.getEntityByUniqueKey("B");
		d.setTest("D");
		assertEquals(col.getEntityByUniqueKey("B").getTest(), d.getTest());
		assertEquals(col.getEntityByUniqueKey("A").getTest(), "A");
	}

	@Test
	public void testBuildEntityInformation() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testSize(){
		assertEquals(col.size(), 0);
		col.saveEntity("A", a);
		col.saveEntity("B", b);
		col.saveEntity("C", c);
		assertEquals(col.size(), 3);
		col.getEntityByUniqueKey("B");
		assertEquals(col.size(), 3);
		col.deleteEntityByUniqueKey("C");
		assertEquals(col.size(), 2);		
	}
	
	@Test
	public void testDeleteEntityByUniqueKey(){
		assertEquals(col.size(), 0);
		col.saveEntity("A", a);
		col.saveEntity("B", b);
		col.saveEntity("C", c);
		assertEquals(col.size(), 3);
		col.deleteEntityByUniqueKey("C");
		assertEquals(col.size(), 2);
		assertEquals(col.getEntityByUniqueKey("C"), null);
	}

}
