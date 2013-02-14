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

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import lombok.Setter;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.ChangeListener;
import at.nullpointer.trayrss.configuration.ConfigurationControllerImpl;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.model.Feed;
import at.nullpointer.trayrss.model.News;
import at.nullpointer.trayrss.notification.buttons.BrowserButton;
import at.nullpointer.trayrss.notification.buttons.Dispose;
import at.nullpointer.trayrss.notification.buttons.Later;
import de.jutzig.jnotification.Corner;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;
import de.jutzig.jnotification.animation.FadeIn;

public class TrayNotifier
        implements Runnable, ChangeListener {

    private Logger log = Logger.getLogger( TrayNotifier.class );

    ArrayList<Notification> input = new ArrayList<Notification>();

    @Setter
    private static PopupManager popupManager;
    private static JButton bread;
    private static JButton bstop;
    private static JButton bclose;


    public void notifyNews() {

        synchronized ( input ) {

            log.debug( "TrayNotifier: notify" );

            if ( input.size() > 0 ) {

                Integer displayCount = ConfigurationControllerImpl.getInstance().getConfigurationModel()
                        .getDisplayCount();

                String title = input.get( 0 ).getNews().getTitle();
                String name = input.get( 0 ).getFeed().getName();
                String url = input.get( 0 ).getNews().getUri();

                News node = input.get( 0 ).getNews();

                JNotificationPopup popup = null;

                bread = new JButton( Messages.getMessageResolver( Messages.NOTIFICATION ).getString(
                        "notification.button.read", "Read" ) );

                bstop = new JButton( Messages.getMessageResolver( Messages.NOTIFICATION ).getString(
                        "notification.button.stop", "Stop" ) );

                bclose = new JButton( Messages.getMessageResolver( Messages.NOTIFICATION ).getString(
                        "notification.button.later", "Later" ) );

                popup = new JNotificationPopup( createComponent( title, name, url ) );
                popup.setAnimator( new FadeIn( popup, 2000 ) );

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


    public void addToNotify( News news, Feed feed ) {

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


    private static Component createComponent( String title, String feedName, String url ) {

        JPanel panel = new JPanel( new GridLayout( 2, 1 ) );
        JLabel ltitel = new JLabel();
        ltitel.setText( "<html><b>" + title + "</b></html>" );
        panel.add( ltitel );

        JPanel buttonPanel = new JPanel( new GridLayout( 1, 3 ) );

        buttonPanel.add( bread );
        buttonPanel.add( bstop );
        buttonPanel.add( bclose );

        panel.add( buttonPanel );

        panel.setBorder( new TitledBorder( feedName ) );
        return panel;

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
