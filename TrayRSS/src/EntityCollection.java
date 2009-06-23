/**
 * Obsolet
 */

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
