package at.nullpointer.trayrss.gui.tray;

import java.util.LinkedList;

import at.nullpointer.trayrss.configuration.feeds.db.Feed;

public class FeedTable {
	
	LinkedList<Object[]> table;
	
	public FeedTable(){
		this.table = new LinkedList<Object[]>();
	}

	public Object[][] getTable() {
		Object[][] erg = new Object[table.size()][4];
		
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
		
		Object[] newRow = new Object[4];
		
		newRow[0] = feed.getName();
		newRow[1] = feed.getUrl();
		newRow[2] = feed.getIntervall();
		newRow[3] = feed.isMonitored();
		
		table.add(newRow);
	}
	
	public void deleteRow(int row){
		table.remove(row);
	}

}
