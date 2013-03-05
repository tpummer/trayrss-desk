/*
 * TrayRSS - simply notification of feed information (c) 2009-2011 TrayRSS Developement Team visit the project at
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
package at.nullpointer.trayrss.notification.buttons;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.persistence.dao.NewsDAO;
import at.nullpointer.trayrss.persistence.dao.NewsDAOImpl;
import at.nullpointer.trayrss.persistence.model.NewsEntity;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;

public class Later
        implements ActionListener {

    private Logger log = Logger.getLogger( Later.class );

    JNotificationPopup popup;
    PopupManager manager;
    Long newsId;


    public Later( Component popup, PopupManager manager, Long newsId ) {

        super();
        this.popup = (JNotificationPopup)popup;
        this.manager = manager;
        this.newsId = newsId;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent e ) {

        manager.dequeuePopup( popup );
        NewsDAO nd = new NewsDAOImpl();
        NewsEntity test = nd.findNewsById( newsId );
        test.setReadCount( test.getReadCount() - 1 );
        try {
            nd.save( test );
        } catch ( SQLException se ) {
            log.error( se.getMessage() );
        }

    }

}