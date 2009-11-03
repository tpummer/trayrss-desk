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

import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import at.nullpointer.trayrss.configuration.feeds.db.News;

public class TrayNotifier {
	
	ArrayList<Notification> input = new ArrayList<Notification>();
	
	public void notify(News news, Feed feed){
		ReferenceCollection.LOG.debug("TrayNotifier: notify");
		
		String title = input.get(0).getNews().getTitle();
		String name = input.get(0).getFeed().getName();
		
		ReferenceCollection.TRAY_ICON.displayMessage(title, name, TrayIcon.MessageType.INFO);
		
		input.remove(0);
		
		ReferenceCollection.LOG.debug("-----------------------------------------------");
	}
	
	public void addToNotify(News news, Feed feed){
		ReferenceCollection.LOG.debug("TrayNotifier: addToNotify");
		
		Notification notifi = new Notification();
		notifi.setFeed(feed);
		notifi.setNews(news);
		
		input.add(notifi);
		ReferenceCollection.LOG.debug("TrayNotifier: " + notifi.getFeed().getName());
		ReferenceCollection.LOG.debug("TrayNotifier: " + notifi.getNews().getTitle());
		ReferenceCollection.LOG.debug("-----------------------------------------------");
	}
	
	public int getSize(){
		return input.size();
	}

}
