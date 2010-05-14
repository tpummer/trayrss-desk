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
package at.nullpointer.trayrss.monitor;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import at.nullpointer.trayrss.configuration.feeds.db.News;
import at.nullpointer.trayrss.monitor.notification.BrowserButton;
import at.nullpointer.trayrss.monitor.notification.Dispose;
import at.nullpointer.trayrss.monitor.notification.Later;
import de.jutzig.jnotification.Corner;
import de.jutzig.jnotification.JNotificationPopup;
import de.jutzig.jnotification.PopupManager;
import de.jutzig.jnotification.animation.FadeIn;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class TrayNotifier implements Runnable {

	ArrayList<Notification> input = new ArrayList<Notification>();
	private static PopupManager popman;
	private static JButton bread;
	private static JButton bstop;
	private static JButton bclose;

	public void notifyNews() {
		ReferenceCollection.LOG
		.debug("TrayNotifier: notify");

		if (getSize() > 0) {
			String title = input.get(0).getNews().getTitle();
			String name = input.get(0).getFeed().getName();
			String url = input.get(0).getNews().getUri();

            News node = input.get(0).getNews();
			
			JNotificationPopup popup = null;
			
			bread = new JButton("Read");
			
			bstop = new JButton("Stop");
			
			bclose = new JButton("Later");
			
			popup = new JNotificationPopup(createComponent(title, name, url));
			popup.setAnimator(new FadeIn(popup,2000));
			
			bread.addActionListener(new BrowserButton(popup,popman,url,node));
			bstop.addActionListener(new Dispose(popup,popman,node));
			bclose.addActionListener(new Later(popup,popman,node));
			
			popman.enqueuePopup(popup);

			input.remove(0);
			ReferenceCollection.LOG.debug("Title: "+title+" Name:"+name+" URI:"+url);
		} else {
			ReferenceCollection.LOG.debug("Nothing found to notify!");
		}
	}

	public void addToNotify(News news, Feed feed) {
		ReferenceCollection.LOG.debug("TrayNotifier: addToNotify");

		Notification notifi = new Notification();
		notifi.setFeed(feed);
		notifi.setNews(news);

		input.add(notifi);
		ReferenceCollection.LOG.debug("TrayNotifier: "
				+ notifi.getFeed().getName());
		ReferenceCollection.LOG.debug("TrayNotifier: "
				+ notifi.getNews().getTitle());
		ReferenceCollection.LOG.debug(getSize());
		ReferenceCollection.LOG
				.debug("-----------------------------------------------");
	}

	public int getSize() {
		return input.size();
	}

	public void run() {
		
		
		while (true) {
			popman = new PopupManager(ReferenceCollection.DISPLAY_SECONDS*1000, Corner.LOWER_RIGHT, new Point(30,100));
			try {
				Thread.sleep(ReferenceCollection.DISPLAY_SECONDS*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.notifyNews();
		}

	}
	
	
	private static Component createComponent(String title, String feedName, String url)
	{
		JPanel panel = new JPanel(new GridLayout(2,1));
		JLabel ltitel =new JLabel();
		ltitel.setText("<html><b>"+title+"</b></html>");
		panel.add(ltitel);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,3));
		
		buttonPanel.add(bread);
		buttonPanel.add(bstop);
		buttonPanel.add(bclose);
		
		panel.add(buttonPanel);
		
		panel.setBorder(new TitledBorder(feedName));
		return panel;
		
	}

}
