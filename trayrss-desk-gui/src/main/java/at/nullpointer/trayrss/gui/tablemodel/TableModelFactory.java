/*
    TrayRSS - simply notification of feed information
    (c) 2009-2011 TrayRSS Developement Team
    visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss.gui.tablemodel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import at.nullpointer.trayrss.dao.FeedDAO;
import at.nullpointer.trayrss.dao.FeedDAOImpl;
import at.nullpointer.trayrss.gui.ConfigurationMessages;
import at.nullpointer.trayrss.model.Feed;

public class TableModelFactory {

	public static DefaultTableModel getTableModel(Set<Feed> feeds){
		
		DefaultTableModel tableModel = new DefaultTableModel(getHeader(), 0){
				Class[] columnTypes = new Class[] {
					Long.class, String.class, String.class, Long.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};		
		
		for(Feed feed: feeds){
			tableModel.addRow(feedToObjectRow(feed));
		}
		
		return tableModel;
		
	}

	private static Object[] getHeader() {
		String[] result = new String[] {
				ConfigurationMessages.getString("config.feeds.table.column.id", "Id"),//"Id", 
				ConfigurationMessages.getString("config.feeds.table.column.feedname", "Feed Name"),//"Feed Name", 
				ConfigurationMessages.getString("config.feeds.table.column.feedurl", "Feed Url"),//"Feed Url", 
				ConfigurationMessages.getString("config.feeds.table.column.intervall", "Intervall"),//"Intervall", 
				ConfigurationMessages.getString("config.feeds.table.column.monitored", "Monitored")//"Monitored"
		};
		return result;
	}

	private static Object[] feedToObjectRow(Feed feed) {
		
		Object [] result = new Object[TableColumn.COLUMN_COUNT];
		
		result[TableColumn.ID] = feed.getId();
		result[TableColumn.FEED_NAME] = feed.getName();
		result[TableColumn.FEED_URL] = feed.getUrl();
		result[TableColumn.INTERVALL] = feed.getIntervall();
		result[TableColumn.MONITORED] = feed.getMonitored();
		
		return result;
	}

	public static Set<Feed> retrieveFeeds(TableModel model) {
		DefaultTableModel dtm = (DefaultTableModel) model;
		int rowCount = dtm.getRowCount();
		FeedDAO feedDao = new FeedDAOImpl();
		
		Set<Feed> result = new HashSet<Feed>();
		for(int row = 0; row < rowCount; row++){
			Feed erg = null;
			Long valueAt = (Long) dtm.getValueAt(row, TableColumn.ID);
			if(valueAt != 0)
				erg = feedDao.findFeedById(valueAt);
			else
				erg = new Feed();
			
			erg.setName((String) dtm.getValueAt(row, TableColumn.FEED_NAME));
			erg.setUrl((String) dtm.getValueAt(row, TableColumn.FEED_URL));
			erg.setIntervall((Long) dtm.getValueAt(row, TableColumn.INTERVALL));
			erg.setMonitored((Boolean) dtm.getValueAt(row, TableColumn.MONITORED));
			
			Date noAction = new Date(0);
			erg.setLastAction(noAction);
			
			result.add(erg);
		}
		
		return result;
	}
}
