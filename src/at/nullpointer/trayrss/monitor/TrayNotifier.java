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
import java.util.Date;
import java.util.HashMap;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;
import at.nullpointer.trayrss.configuration.feeds.db.News;

public class TrayNotifier {
	
	HashMap<Date,Notification> input; 
	
	public void notify(News news, Feed feed){
		ReferenceCollection.TRAY_ICON.displayMessage(news.getTitle(), feed.getName(), TrayIcon.MessageType.INFO);
	}
	
	public void addToNotify(News news, Feed feed){
		ReferenceCollection.TRAY_ICON.displayMessage(news.getTitle(), feed.getName(), TrayIcon.MessageType.INFO);
	}

}
