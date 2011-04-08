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
package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;

public class FeedTable {
	private Logger log = Logger.getLogger(FeedTable.class);
	
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
		Object[] emptyRow = {null,null,null,null,null};
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
		log.debug("Delete Row: "+row);
		if(row != -1){
			table.remove(row);
			log.debug("Succesfully deleted Row");
		} else {
			log.debug("No row selected.");
		}
	}

	/**
	 * Stores the feed information in the database
	 */
	public void store() {
		
		ReferenceCollection.MONITOR.stopAll(2);
		
		int feedcount = ReferenceCollection.FEED_TABLE.getTable().length;
		
		FeedDAOImpl feeddao = new FeedDAOImpl();
		
		ArrayList<Feed> feeds = (ArrayList<Feed>) feeddao.getFeeds();
		
		for (int i = 0; i < feedcount; i++) {
			
			Object[][] readTable = ReferenceCollection.FEED_TABLE.getTable();

			if (readTable[i][1] != null) {

				Feed change = null;

				if (readTable[i][0] != null) {
					change = feeddao
							.findFeedById((Long) readTable[i][0]);
				} else {
					change = new Feed();
				}
				change.setName((String) readTable[i][1]);
				change.setUrl((String) readTable[i][2]);

				if (ReferenceCollection.FEED_TABLE.getTable()[i][3] instanceof Long) {
					change.setIntervall((Long) readTable[i][3]);
				} else {
					change.setIntervall(Long
							.parseLong((String) readTable[i][3]));
				}
				// TODO monitored
				if (readTable[i][4] instanceof Boolean) {
					change
							.setMonitored((Boolean) readTable[i][4]);
				} else {
					change
							.setMonitored(Boolean
									.parseBoolean((String) readTable[i][4]));
				}

				change.setLastAction(new Date());

				feeddao.save(change);

				Feed merken = null;
				for(Iterator<Feed> it = feeds.iterator(); it.hasNext(); ){
					Feed helper = it.next();
					if (helper.getId().equals(change.getId())){
						merken = helper;
					}
				}
				if(merken != null)
				feeds.remove(merken);

			}
			
			ReferenceCollection.MONITOR.loadFeeds();

		}
		
		for(Feed o: feeds){
			feeddao.deleteById(o.getId());
		}
		
	}

}
