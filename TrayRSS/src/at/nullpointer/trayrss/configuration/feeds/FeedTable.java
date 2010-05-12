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
package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import org.hibernate.classic.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

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
		ReferenceCollection.LOG.debug("Delete Row: "+row);
		if(row != -1){
			table.remove(row);
			ReferenceCollection.LOG.debug("Succesfully deleted Row");
		} else {
			ReferenceCollection.LOG.debug("No row selected.");
		}
	}

	/**
	 * Stores the feed information in the database
	 */
	public void store() {
		
		int feedcount = ReferenceCollection.FEED_TABLE.getTable().length;
		
		FeedDAOImpl feeddao = new FeedDAOImpl();
		
		ArrayList<Feed> feeds = (ArrayList<Feed>) feeddao.getFeeds();
		
		for (int i = 0; i < feedcount; i++) {

			if (ReferenceCollection.FEED_TABLE.getTable()[i][1] == null) {

			} else {

				Feed change = null;

				if (ReferenceCollection.FEED_TABLE.getTable()[i][0] != null) {
					change = feeddao
							.findFeedById((Long) ReferenceCollection.FEED_TABLE
									.getTable()[i][0]);
				} else {
					change = new Feed();
				}
				change.setName((String) ReferenceCollection.FEED_TABLE
						.getTable()[i][1]);
				change.setUrl((String) ReferenceCollection.FEED_TABLE
						.getTable()[i][2]);

				if (ReferenceCollection.FEED_TABLE.getTable()[i][3] instanceof Long) {
					change.setIntervall((Long) ReferenceCollection.FEED_TABLE
							.getTable()[i][3]);
				} else {
					change.setIntervall(Long
							.parseLong((String) ReferenceCollection.FEED_TABLE
									.getTable()[i][3]));
				}
				// TODO monitored
				if (ReferenceCollection.FEED_TABLE.getTable()[i][4] instanceof Boolean) {
					change
							.setMonitored((Boolean) ReferenceCollection.FEED_TABLE
									.getTable()[i][4]);
				} else {
					change
							.setMonitored(Boolean
									.parseBoolean((String) ReferenceCollection.FEED_TABLE
											.getTable()[i][4]));
				}

				change.setLastAction(new Date());

				feeddao.save(change);

				feeds.remove(change);

			}

		}
		
		for(Feed o: feeds){
			feeddao.deleteById(o.getId());
		}
		
	}

}
