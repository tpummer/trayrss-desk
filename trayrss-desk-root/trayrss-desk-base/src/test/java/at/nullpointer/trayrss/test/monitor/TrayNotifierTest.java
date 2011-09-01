/*
    TrayRSS - simply notification of feed information
    (c) 2009-2011 TrayRSS Developement Team
    visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss.test.monitor;

import static org.junit.Assert.assertEquals;

import java.awt.Image;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.nullpointer.trayrss.configuration.ConfigurationControllerImpl;
import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.model.ConfigurationModel;
import at.nullpointer.trayrss.model.Feed;
import at.nullpointer.trayrss.model.News;
import at.nullpointer.trayrss.notification.TrayNotifier;
import de.jutzig.jnotification.Corner;
import de.jutzig.jnotification.PopupManager;

public class TrayNotifierTest {
	private Logger log = Logger.getLogger(TrayNotifierTest.class);
	
	final TrayNotifier tn = new TrayNotifier();
	Feed testfeed;
	News testnews, testNewsBadUri;
	static TrayIcon trayIcon;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SystemTray tray = SystemTray.getSystemTray();

		
		PopupMenu popup = new PopupMenu();
		Image image = Toolkit.getDefaultToolkit().getImage("tray.gif");
		 trayIcon = new TrayIcon(image, "Tray Demo", popup);

		 tray.add(trayIcon);
		 ReferenceCollection.TRAY_ICON = trayIcon;
	}

	@Before
	public void setUp() throws Exception {	 
		
		ConfigurationModel model = new ConfigurationModel();
		model.setDisplayCount(3);
		ConfigurationControllerImpl controller = (ConfigurationControllerImpl) ConfigurationControllerImpl.getInstance();
		controller.setConfigurationModel(model);

		testfeed = new Feed();
		testfeed.setName("testname");
		testnews = new News();
		testnews.setTitle("testtitle");
		testNewsBadUri = new News();
		testNewsBadUri.setTitle("Bad URL");
		testNewsBadUri.setUri("asd");
		
		tn.setPopupManager(new PopupManager(
				3 * 1000, Corner.LOWER_RIGHT,
				new Point(30, 100)));

	}

	@Test
	public void testNotifyNewsFeed() throws InterruptedException {
		tn.addToNotify(testnews, testfeed);
		int size = tn.getSize();
		log.debug(size);
		tn.notifyNews();
		assertEquals(size - 1, tn.getSize());
		log.debug(tn.getSize());
		Thread.sleep(10000);
		
	}

	@Test
	public void testAddToNotify() {
		int size = tn.getSize();
		log.debug(size);
		tn.addToNotify(testnews, testfeed);
		log.debug(tn.getSize());
		assertEquals(size + 1, tn.getSize());
		
	}
	
	@Test
	public void testNotifyNewsFeedBadUri() throws InterruptedException{
		tn.addToNotify(testNewsBadUri, testfeed);
		tn.notifyNews();
		Thread.sleep(10000);
	}

}
