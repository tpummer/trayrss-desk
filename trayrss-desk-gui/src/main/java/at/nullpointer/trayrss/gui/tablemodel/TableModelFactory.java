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

import java.util.Date;
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
		
		Object [] result = new Object[TableColumnUtil.COLUMN_COUNT];
		
		result[TableColumnUtil.ID] = feed.getId();
		result[TableColumnUtil.FEED_NAME] = feed.getName();
		result[TableColumnUtil.FEED_URL] = feed.getUrl();
		result[TableColumnUtil.INTERVALL] = feed.getIntervall();
		result[TableColumnUtil.MONITORED] = feed.getMonitored();
		
		return result;
	}

	public static Set<Feed> retrieveFeeds(TableModel model) {
		DefaultTableModel dtm = (DefaultTableModel) model;
		int rowCount = dtm.getRowCount();
		FeedDAO feedDao = new FeedDAOImpl();
		
		Set<Feed> result = new HashSet<Feed>();
		for(int row = 0; row < rowCount; row++){
			Feed erg = null;
			Long valueAt = (Long) dtm.getValueAt(row, TableColumnUtil.ID);
			if(valueAt != 0)
				erg = feedDao.findFeedById(valueAt);
			else
				erg = new Feed();
			
			erg.setName((String) dtm.getValueAt(row, TableColumnUtil.FEED_NAME));
			erg.setUrl((String) dtm.getValueAt(row, TableColumnUtil.FEED_URL));
			erg.setIntervall((Long) dtm.getValueAt(row, TableColumnUtil.INTERVALL));
			erg.setMonitored((Boolean) dtm.getValueAt(row, TableColumnUtil.MONITORED));
			
			Date noAction = new Date(0);
			erg.setLastAction(noAction);
			
			result.add(erg);
		}
		
		return result;
	}
}
