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
package at.nullpointer.trayrss.gui.tray;

import java.util.LinkedList;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;

public class FeedTable {
	int tableColumns = 5;
	
	LinkedList<Object[]> table;
	
	public FeedTable(){
		this.table = new LinkedList<Object[]>();
	}

	public Object[][] getTable() {
		Object[][] erg = new Object[table.size()][tableColumns];
		
		for(int i = table.size()-1; i >= 0; i--){
			erg[i] = table.get(i);
		}
		
		return erg;
	}

	public void addEmptyRow(){
		Object[][] emptyRow = {null,null,null,null};
		table.add(emptyRow);
	}
	
	public void addRow(Feed feed){
		
		Object[] newRow = new Object[tableColumns];
		
		newRow[0] = feed.getId();
		newRow[1] = feed.getName();
		newRow[2] = feed.getUrl();
		newRow[3] = feed.getIntervall();
		newRow[4] = feed.isMonitored();
		
		table.add(newRow);
	}
	
	public void deleteRow(int row){
		table.remove(row);
	}

	/**
	 * Stores the feed information in the database
	 */
	public void store() {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		
		Transaction tx = session.beginTransaction();
		
		int feedcount = ReferenceCollection.FEED_TABLE.getTable().length;
		
		for(int i = 0; i < feedcount; i++){
			Feed inputfeed = new Feed();
			//TODO Feed mit ID lesen
			//TODO Feed mit ID um neue Daten updaten
		}
		
		tx.commit();
		
		session.close();
		
	}

}
