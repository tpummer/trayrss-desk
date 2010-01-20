package at.nullpointer.trayrss.monitor.notification;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.NewsDAO;
import at.nullpointer.trayrss.configuration.feeds.NewsDAOImpl;
import at.nullpointer.trayrss.configuration.feeds.db.News;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;
import org.hibernate.Session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
    RSSTray - simply alerting at new Feed Information
    Copyright (C) 2009 Thomas Pummer
    conatct me fake (at) sprossenwanne.at

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
public class BrowserButton implements ActionListener {

	JNotificationPopup popup;
	PopupManager manager;
	String url;
    News node;

	public BrowserButton(Component popup, PopupManager manager, String url, News node) {
		super();
		this.popup = (JNotificationPopup) popup;
		this.manager=manager;
		this.url = url;
        this.node = node;
	}

	public void actionPerformed(ActionEvent e) {
		URI uri;
		try {
			uri = new URI(url);
			Desktop.getDesktop ().browse (uri);

            Session sess = ReferenceCollection.SESSION_FACTORY.openSession();

            NewsDAO nd = new NewsDAOImpl();
            News test = nd.getNewsByData(node, sess);
            test.setReadCount(new Long(ReferenceCollection.DISPLAY_COUNT));
            nd.save(test,sess);
                     sess.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		manager.dequeuePopup(popup);

	}

}
