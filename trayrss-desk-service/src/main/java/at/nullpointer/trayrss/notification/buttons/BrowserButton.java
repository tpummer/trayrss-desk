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
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.persistence.NewsRepository;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;

public class BrowserButton
        implements ActionListener {

    private static final String ERROR_BUTTON_URI = "error.button.notification_read_error_uri";

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( BrowserButton.class );

    private JNotificationPopup popup;
    private PopupManager manager;
    private String newsUrl;
    private Integer displayCount;


    /**
     * This is a button on the notification where the user can choose to open the original news entry in a browser
     * 
     * @param popup
     * @param manager
     * @param url
     * @param node
     * @param displayCount
     */
    public BrowserButton( Component popup, PopupManager manager, String newsUrl, Integer displayCount ) {

        super();
        this.popup = (JNotificationPopup)popup;
        this.manager = manager;
        this.newsUrl = newsUrl;
        this.displayCount = displayCount;
    }


    /**
     * Opens the url of an news entry in the browser
     */
    public void actionPerformed( ActionEvent e ) {

        URI uri = null;

        if ( newsUrl == null ) {
            LOG.error( "Pressing [Read] Button but the url is empty!" );
        } else {

            try {
                uri = new URI( newsUrl );
                LOG.debug( "Pressing [Read] Button at uri: " + uri );
                if ( Desktop.getDesktop() == null ) {
                    LOG.warn( "Pressing [Read] Button, Desktop not found!" );
                }
                try {
                    Desktop.getDesktop().browse( uri );
                    LOG.debug( "Pressing [Read] Button, uri should be open now." );
                } catch ( IOException e2 ) {
                    JOptionPane.showMessageDialog(
                            null,
                            Messages.getMessageResolver( Messages.ERROR ).getString( ERROR_BUTTON_URI,
                                    "Error reading Feed" ),
                            Messages.getMessageResolver( Messages.ERROR ).getString( ERROR_BUTTON_URI,
                                    "Error reading Feed" ), JOptionPane.ERROR_MESSAGE );
                    LOG.debug( "Pressing [Read] Button IOException" );
                    LOG.error( e2.getMessage() );
                    e2.printStackTrace();
                }

                NewsRepository newsRepository = ReferenceCollection.getInstance().getContext()
                        .getBean( "newsRepository", NewsRepository.class );

                News test = newsRepository.retrieveNews( newsUrl );
                test.setReadCount( new Long( this.displayCount ) );

                newsRepository.saveOrUpdate( test );

                manager.dequeuePopup( popup );

            } catch ( URISyntaxException e1 ) {
                // TODO setting new error concept with new notification
                JOptionPane.showMessageDialog( null,
                        Messages.getMessageResolver( Messages.ERROR )
                                .getString( ERROR_BUTTON_URI, "Error reading Feed" ),
                        Messages.getMessageResolver( Messages.ERROR )
                                .getString( ERROR_BUTTON_URI, "Error reading Feed" ), JOptionPane.ERROR_MESSAGE );
                LOG.debug( "Pressing [Read] Button URISyntaxException" );
                LOG.error( e1.getMessage() );
                e1.printStackTrace();
            }

        }

    }

}
