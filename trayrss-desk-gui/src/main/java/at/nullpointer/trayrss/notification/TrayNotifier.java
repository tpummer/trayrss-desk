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
package at.nullpointer.trayrss.notification;

import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.ChangeListener;
import at.nullpointer.trayrss.configuration.ConfigurationControllerImpl;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.notification.buttons.BrowserButton;
import at.nullpointer.trayrss.notification.buttons.Dispose;
import at.nullpointer.trayrss.notification.buttons.Later;
import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;
import de.jutzig.jnotification.Corner;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;
import de.jutzig.jnotification.animation.FadeIn;

public class TrayNotifier
        implements Runnable, ChangeListener {

    private Logger log = Logger.getLogger( TrayNotifier.class );

    @Getter
    ArrayList<Notification> input = new ArrayList<Notification>();

    /**
     * Instance of {@link JNotificationPopupFactory}
     */
    private JNotificationPopupFactory jNotificationPopupFactory;

    @Setter
    private static PopupManager popupManager;
    private static JButton bread;
    private static JButton bstop;
    private static JButton bclose;


    /**
     * Creates an Instance of {@link TrayNotifier}
     * 
     * @param jNotificationPopupFactory sets the Factory that creates {@link JNotificationPopup}
     */
    public TrayNotifier( JNotificationPopupFactory jNotificationPopupFactory ) {

        this.jNotificationPopupFactory = jNotificationPopupFactory;
    }


    public void notifyNews() {

        synchronized ( input ) {

            log.debug( "TrayNotifier: notify" );

            if ( !input.isEmpty() ) {

                Integer displayCount = ConfigurationControllerImpl.getInstance().getConfigurationModel()
                        .getDisplayCount();

                String title = input.get( 0 ).getNews().getTitle();
                String name = input.get( 0 ).getFeed().getName();
                String url = input.get( 0 ).getNews().getUri();

                NewsEntity node = input.get( 0 ).getNews();

                JNotificationPopup popup = null;

                bread = new JButton( Messages.getMessageResolver( Messages.NOTIFICATION ).getString(
                        "notification.button.read", "Read" ) );

                bstop = new JButton( Messages.getMessageResolver( Messages.NOTIFICATION ).getString(
                        "notification.button.stop", "Stop" ) );

                bclose = new JButton( Messages.getMessageResolver( Messages.NOTIFICATION ).getString(
                        "notification.button.later", "Later" ) );

                popup = jNotificationPopupFactory.createPopup( title, name );
                popup.setAnimator( new FadeIn( popup, 2000 ) );

                JPanel buttonPanel = new JPanel( new GridLayout( 1, 3 ) );

                buttonPanel.add( bread );
                buttonPanel.add( bstop );
                buttonPanel.add( bclose );

                ( (JPanel)popup.getComponent() ).add( buttonPanel );

                if ( url != null ) {
                    bread.addActionListener( new BrowserButton( popup, popupManager, url, node, displayCount ) );
                } else {
                    bread.setEnabled( false );
                }

                bstop.addActionListener( new Dispose( popup, popupManager, node, displayCount ) );
                bclose.addActionListener( new Later( popup, popupManager, node ) );

                popupManager.enqueuePopup( popup );

                input.remove( 0 );
                log.debug( "Title: " + title + " Name:" + name + " URI:" + url );
            } else {
                log.debug( "Nothing found to notify!" );
            }
        }
    }


    public void addToNotify( NewsEntity news, FeedEntity feed ) {

        log.debug( "TrayNotifier: addToNotify" );

        Notification notifi = new Notification();
        notifi.setFeed( feed );
        notifi.setNews( news );

        input.add( notifi );
        log.debug( "TrayNotifier: " + notifi.getFeed().getName() );
        log.debug( "TrayNotifier: " + notifi.getNews().getTitle() );
        log.debug( "TrayNotifier: Size " + input.size() );
        log.debug( "-----------------------------------------------" );
    }


    public void run() {

        while ( true ) {
            Integer displaySeconds = ConfigurationControllerImpl.getInstance().getConfigurationModel().getDisplayTime();

            popupManager = new PopupManager( displaySeconds * 1000, Corner.LOWER_RIGHT, new Point( 30, 100 ) );
            try {
                Thread.sleep( displaySeconds * 1000 );
            } catch ( InterruptedException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.notifyNews();
        }

    }


    /**
     * Setzt den {@link TrayNotifier} zurück
     */
    public void notifyChange() {

        synchronized ( input ) {
            this.input.clear();
        }

    }

}
