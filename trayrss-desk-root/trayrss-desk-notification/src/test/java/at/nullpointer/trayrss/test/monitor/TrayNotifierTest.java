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
package at.nullpointer.trayrss.test.monitor;

import static org.junit.Assert.assertEquals;

import javax.swing.JPanel;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.nullpointer.trayrss.configuration.ConfigurationControllerImpl;
import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.messages.Messages;
import at.nullpointer.trayrss.notification.JNotificationPopupFactory;
import at.nullpointer.trayrss.notification.TrayNotifier;
import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;
import de.jutzig.jnotification.animation.Animator;
import de.jutzig.jnotification.renderer.RenderDelegate;

/**
 * Class under Test {@link TrayNotifier}
 * 
 * @author Thomas Pummer
 * 
 */
public class TrayNotifierTest {

    /**
     * TrayNotifier
     */
    private TrayNotifier tn;
    /**
     * Feed
     */
    private FeedEntity testfeed;
    /**
     * News-Items
     */
    private NewsEntity testnews;


    /**
     * Sets up the Message context
     */
    @BeforeClass
    public static void setUpBeforeClass() {

        Messages.setup( "en" );

    }


    /**
     * Setup the Notifier and Test News for each Test
     */
    @Before
    public void setUp() {

        JNotificationPopup popupMock = EasyMock.createMock( JNotificationPopup.class );
        popupMock.setDelegate( EasyMock.isA( RenderDelegate.class ) );
        popupMock.setAnimator( EasyMock.isA( Animator.class ) );
        EasyMock.expect( popupMock.getComponent() ).andReturn( EasyMock.createMock( JPanel.class ) );
        EasyMock.replay( popupMock );
        JNotificationPopupFactory mock = EasyMock.createMock( JNotificationPopupFactory.class );
        EasyMock.expect( mock.createPopup( "testtitle", "testname" ) ).andReturn( popupMock );
        EasyMock.replay( mock );

        tn = new TrayNotifier( mock );

        ConfigurationModel model = new ConfigurationModel();
        model.setDisplayCount( 3 );
        ConfigurationControllerImpl controller = (ConfigurationControllerImpl)ConfigurationControllerImpl.getInstance();
        controller.setConfigurationModel( model );

        testfeed = new FeedEntity();
        testfeed.setName( "testname" );
        testnews = new NewsEntity();
        testnews.setTitle( "testtitle" );

        PopupManager popupManger = EasyMock.createMock( PopupManager.class );
        tn.setPopupManager( popupManger );

    }


    /**
     * Tests if a Feed get notified and removed from queue
     */
    @Test
    public void testNotifyNewsFeed() {

        tn.addToNotify( testnews, testfeed );
        int size = tn.getInput().size();
        tn.notifyNews();
        assertEquals( size - 1, tn.getInput().size() );

    }


    /**
     * Tests if a Feed get edded to queue
     */
    @Test
    public void testAddToNotify() {

        int size = tn.getInput().size();
        tn.addToNotify( testnews, testfeed );
        assertEquals( size + 1, tn.getInput().size() );

    }

}
