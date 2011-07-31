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
package at.nullpointer.trayrss.notification.buttons;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.dao.NewsDAO;
import at.nullpointer.trayrss.dao.NewsDAOImpl;
import at.nullpointer.trayrss.model.News;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;

public class BrowserButton implements ActionListener {

	public static final String notification_read_error_uri = "Error reading URL";

	private Logger log = Logger.getLogger(BrowserButton.class);

	private JNotificationPopup popup;
	private PopupManager manager;
	private String url;
	private News node;
	private Integer displayCount;

	/**
	 * This is a button on the notification where the user can choose to open
	 * the original news entry in a browser
	 * 
	 * @param popup
	 * @param manager
	 * @param url
	 * @param node
	 * @param displayCount
	 */
	public BrowserButton(Component popup, PopupManager manager, String url,
			News node, Integer displayCount) {
		super();
		this.popup = (JNotificationPopup) popup;
		this.manager = manager;
		this.url = url;
		this.node = node;
		this.displayCount = displayCount;
	}

	/**
	 * Opens the url of an news entry in the browser
	 */
	public void actionPerformed(ActionEvent e) {
		URI uri = null;

		if (url == null) {
			log.error("Pressing [Read] Button but the url is empty!");
		} else {

			try {
				uri = new URI(url);
				log.debug("Pressing [Read] Button at uri: " + uri);
				if (Desktop.getDesktop() == null) {
					log.warn("Pressing [Read] Button, Desktop not found!");
				}
				try {
					Desktop.getDesktop().browse(uri);
					log.debug("Pressing [Read] Button, uri should be open now.");
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null,
							notification_read_error_uri,
							notification_read_error_uri,
							JOptionPane.ERROR_MESSAGE);
					log.debug("Pressing [Read] Button IOException");
					log.error(e2.getMessage());
					e2.printStackTrace();
				}

				NewsDAO nd = new NewsDAOImpl();
				News test = nd.getNewsByData(node);
				test.setReadCount(new Long(this.displayCount));
				try {
					nd.save(test);
				} catch (SQLException se) {
					log.error(se.getMessage());
				}

				manager.dequeuePopup(popup);

			} catch (URISyntaxException e1) {
				//TODO setting new error concept with new notification
				JOptionPane.showMessageDialog(null,
						notification_read_error_uri,
						notification_read_error_uri, JOptionPane.ERROR_MESSAGE);
				log.debug("Pressing [Read] Button URISyntaxException");
				log.error(e1.getMessage());
				e1.printStackTrace();
			}

		}

	}

}
