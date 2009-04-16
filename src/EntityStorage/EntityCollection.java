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

import java.util.HashMap;

/**
 * Collection which contains the single Feed Entries
 * @author Thomas Pummer
 *
 */
public class EntityCollection {
	private HashMap<String, EntityInformation> collection = new HashMap<String, EntityInformation>();
	
	/**
	 * Stores a EntityInformation in the collection
	 * @param keyDateAndTitle
	 * @param value
	 * @return
	 */
	public boolean saveEntity(String keyDateAndTitle, EntityInformation value){
		this.collection.put(keyDateAndTitle, value);
		return true;
	}
	
	/**
	 * Retrieves an EntityInformation from the collection
	 * @param keyDateAndTitle
	 * @return
	 */
	public EntityInformation getEntityByUniqueKey (String keyDateAndTitle){
		return this.collection.get(keyDateAndTitle);
	}
	
	/**
	 * Generates an EntityInformation from an feed entry
	 * @return
	 */
	public EntityInformation buildEntityInformation(){
		//TODO
		return null;
	}
	
	/**
	 * Returns the size of the collection
	 * @return
	 */
	public int size(){
		return collection.size();
	}

	/**
	 * ermoves an entity from the collection
	 * @param string
	 */
	public void deleteEntityByUniqueKey(String keyDateAndTitle) {
		this.collection.remove(keyDateAndTitle);	
	}
}
