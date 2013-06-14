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

/**
 * Button to open the feed url
 * 
 * @author Thomas Pummer
 * 
 */
public class BrowserButton
        implements ActionListener {

    /**
     * Standard String for the caption of an error on the cation of the browserbutton
     */
    private static final String DEFAULT_MSG_ERROR_READING_FEED = "Error reading Feed";

    /**
     * key for the caption of an error on the cation of the browserbutton
     */
    private static final String ERROR_BUTTON_URI = "error.button.notification_read_error_uri";

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( BrowserButton.class );

    /**
     * {@link JNotificationPopup} the button references to
     */
    private JNotificationPopup popup;
    /**
     * {@link PopupManager} managing the popup the button references to
     */
    private PopupManager manager;
    /**
     * url of the feed news
     */
    private String newsUrl;
    /**
     * displaycount of the feed item
     */
    private Integer displayCount;


    /**
     * This is a button on the notification where the user can choose to open the original news entry in a browser
     * 
     * @param popup
     * @param manager
     * @param newsUrl
     * @param displayCount
     */
    public BrowserButton( final Component popup, final PopupManager manager, final String newsUrl,
            final Integer displayCount ) {

        super();
        this.popup = (JNotificationPopup)popup;
        this.manager = manager;
        this.newsUrl = newsUrl;
        this.displayCount = displayCount;
    }


    /**
     * Opens the url of an news entry in the browser
     */
    public void actionPerformed( ActionEvent event ) {

        URI uri = null;

        if ( this.newsUrl == null ) {
            LOG.error( "Pressing [Read] Button but the url is empty!" );
        } else {

            try {
                uri = new URI( this.newsUrl );
                LOG.debug( "Pressing [Read] Button at uri: " + uri );
                if ( Desktop.isDesktopSupported() ) {
                    try {
                        Desktop.getDesktop().browse( uri );
                        LOG.debug( "Pressing [Read] Button, uri should be open now." );
                    } catch ( IOException e ) {
                        printErrorMessage( e );

                        NewsRepository newsRepository = ReferenceCollection.getInstance().getContext()
                                .getBean( "newsRepository", NewsRepository.class );

                        News test = newsRepository.retrieveNews( this.newsUrl );
                        test.setReadCount( new Long( this.displayCount ) );

                        newsRepository.saveOrUpdate( test );
                    }
                } else {
                    LOG.error( "Pressing [Read] Button, Desktop not found!" );
                    printErrorMessage( new UnsupportedOperationException(
                            "Could not access the desktop on the current plattform" ) );
                }

                this.manager.dequeuePopup( this.popup );

            } catch ( URISyntaxException e ) {
                printErrorMessage( e );
            }

        }

    }


    private void printErrorMessage( Exception e ) {

        JOptionPane.showMessageDialog(
                null,
                Messages.getMessageResolver( Messages.ERROR ).getString( ERROR_BUTTON_URI,
                        DEFAULT_MSG_ERROR_READING_FEED ),
                Messages.getMessageResolver( Messages.ERROR ).getString( ERROR_BUTTON_URI,
                        DEFAULT_MSG_ERROR_READING_FEED ), JOptionPane.ERROR_MESSAGE );
        LOG.debug( "Pressing [Read] Button " + e.getClass().getSimpleName() );
        LOG.error( e.getMessage() );
        e.printStackTrace();
    }

}
