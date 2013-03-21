/*
 * TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
 * http://trayrss.nullpointer.at/
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package at.nullpointer.trayrss.gui.tablemodel;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * This class holds the references for the column index of the feed table model
 * 
 * @author Thomas Pummer
 * 
 */
public class TableColumnUtil {

    public static final int COLUMN_COUNT = 5;

    public final static int ID = 0;
    public final static int FEED_NAME = 1;
    public final static int FEED_URL = 2;
    public final static int INTERVALL = 3;
    public final static int MONITORED = 4;

    // the sum of the column sizes has to be 100 percent
    public final static int COLUMN_SIZE_PERCENT_ID = 5;
    public final static int COLUMN_SIZE_PERCENT_FEED_NAME = 33;
    public final static int COLUMN_SIZE_PERCENT_FEED_URL = 45;
    public final static int COLUMN_SIZE_PERCENT_INTERVALL = 10;
    public final static int COLUMN_SIZE_PERCENT_MONITORED = 7;


    public static void setColumnsSize( TableColumnModel cm, int tablesize ) {

        boolean resizeable = false;

        setColumnSize( cm, ID, COLUMN_SIZE_PERCENT_ID, tablesize, resizeable );
        setColumnSize( cm, FEED_NAME, COLUMN_SIZE_PERCENT_FEED_NAME, tablesize, resizeable );
        setColumnSize( cm, FEED_URL, COLUMN_SIZE_PERCENT_FEED_URL, tablesize, resizeable );
        setColumnSize( cm, INTERVALL, COLUMN_SIZE_PERCENT_INTERVALL, tablesize, resizeable );
        setColumnSize( cm, MONITORED, COLUMN_SIZE_PERCENT_MONITORED, tablesize, resizeable );
    }


    private static void setColumnSize( TableColumnModel cm, int id, int percent, int tablesize, boolean resizeable ) {

        TableColumn column = cm.getColumn( id );
        column.setMinWidth( 1 );
        column.setPreferredWidth( tablesize * percent / 100 );
        column.setMaxWidth( tablesize );

    }

}
