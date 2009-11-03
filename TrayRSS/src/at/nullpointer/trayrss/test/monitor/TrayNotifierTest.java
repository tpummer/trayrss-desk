package at.nullpointer.trayrss.test.monitor;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import at.nullpointer.trayrss.configuration.feeds.db.News;
import at.nullpointer.trayrss.gui.tray.TrayIconPOJO;
import at.nullpointer.trayrss.monitor.TrayNotifier;

public class TrayNotifierTest {
	TrayNotifier tn = new TrayNotifier();
	Feed testfeed;
	News testnews;
	static TrayIcon trayIcon;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SystemTray tray = SystemTray.getSystemTray();

		
		PopupMenu popup = new PopupMenu();
		Image image = Toolkit.getDefaultToolkit().getImage("tray.gif");
		 trayIcon = new TrayIcon(image, "Tray Demo", popup);

		 tray.add(trayIcon);
		 ReferenceCollection.TRAY_ICON = trayIcon;
		
		Logger log = Logger.getRootLogger();

		ReferenceCollection.LOG = log;

		String pattern = "%d{MM/dd/yyyy HH:mm:ss,SSSS}: %m %n";
		PatternLayout layout = new PatternLayout(pattern);
		ConsoleAppender consoleAppender = new ConsoleAppender(layout);
		log.addAppender(consoleAppender);
		// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
		log.setLevel(ReferenceCollection.LOG_LEVEL_DEBUG);
		
		ReferenceCollection.LOG = log;
	}

	@Before
	public void setUp() throws Exception {	    

		testfeed = new Feed();
		testfeed.setName("testname");
		testnews = new News();
		testnews.setTitle("testtitle");

	}

	@Test
	public void testNotifyNewsFeed() {
		tn.addToNotify(testnews, testfeed);
		int size = tn.getSize();
		ReferenceCollection.LOG.debug(size);
		tn.notify(null, null);
		assertEquals(size - 1, tn.getSize());
		ReferenceCollection.LOG.debug(tn.getSize());
		
	}

	@Test
	public void testAddToNotify() {
		int size = tn.getSize();
		ReferenceCollection.LOG.debug(size);
		tn.addToNotify(testnews, testfeed);
		ReferenceCollection.LOG.debug(tn.getSize());
		assertEquals(size + 1, tn.getSize());
		
	}

}
