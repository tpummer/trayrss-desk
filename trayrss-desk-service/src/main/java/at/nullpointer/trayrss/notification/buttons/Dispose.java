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
package at.nullpointer.trayrss.notification.buttons;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.persistence.NewsRepository;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;

public class Dispose
        implements ActionListener {

    private Logger log = Logger.getLogger( Dispose.class );

    private JNotificationPopup popup;
    private PopupManager manager;
    private String newsUri;
    private Integer displayCount;


    public Dispose( Component popup, PopupManager manager, String newsUri, Integer displayCount ) {

        super();
        this.popup = (JNotificationPopup)popup;
        this.manager = manager;
        this.newsUri = newsUri;
        this.displayCount = displayCount;
    }


    public void actionPerformed( ActionEvent e ) {

        manager.dequeuePopup( popup );
        NewsRepository newsRepository = ReferenceCollection.context.getBean( "newsRepository", NewsRepository.class );
        News test = newsRepository.retrieveNews( newsUri );
        test.setReadCount( new Long( this.displayCount ) );

        newsRepository.saveOrUpdate( test );

    }

}
