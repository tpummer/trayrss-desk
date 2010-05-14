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
package at.nullpointer.trayrss.gui.configframe;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.FeedTable;

import javax.swing.table.AbstractTableModel;

public class FeedTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7696255321306199812L;

	FeedTable table = ReferenceCollection.FEED_TABLE;

	Object[] columnNames = ReferenceCollection.CONFIG_TABLE_HEADER;

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return table.getTable().length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return table.getTable()[rowIndex][columnIndex];
	}
	
	public boolean isCellEditable(int row, int column) {
        return (column != 0);
      }

	public String getColumnName(int column) {
		return (String)columnNames[column];
	}

	public Class<?> getColumnClass(int column) {
		Class<?> erg = null;
		switch (column){
		case 0: 
			erg = Long.class;
			break;
		case 1:
			erg = String.class;
			break;
		case 2:
			erg = String.class;
			break;
		case 3:
			erg = Long.class;
			break;
		case 4:
			erg = Boolean.class;
			break;
		default:
			erg = String.class;
		}
		return erg;
	}

	public void setValueAt(Object value, int row, int column) {
		table.getTable()[row][column] = value;
	}

}
