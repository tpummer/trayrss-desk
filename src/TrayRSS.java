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

import gui.TrayRSSSplashScreen;
import gui.tray.TrayIconPOJO;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import monitor.FeedReaderThread;
import monitor.IconChanger;

import configuration.ReferenceCollection;
import configuration.StartUp;
import configuration.feeds.db.Feed;

public class TrayRSS {

	private static SystemTray tray = null;
	private static LinkedList<Feed> rssUrlSave = null;
	// nach den anderen Vairablen die befüllt werden
	private static TrayRSS instance = new TrayRSS();
	public static TrayIcon trayIcon;
	

	private TrayRSS() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//ReferenceCollection.TRAYRSS_APP_TITLE = TrayRSS.class.getPackage().getImplementationVersion();

		boolean debug = false;;
		
		if(args.length != 0){
			if(args[0].equals("-debug")){
				debug = true;
			} else {
				//TODO Parameterausgabe zur Hilfe
			}
		}

		TrayRSSSplashScreen splash = new TrayRSSSplashScreen();
		StartUp startup = new StartUp(debug);
		splash.endSplashAfterDisplaytime(3);

	}

	public void saveRssInfo() {
		try {
			FileOutputStream fileOS = new FileOutputStream(".feeds");
			ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
			objectOS.writeObject(rssUrlSave);
			objectOS.close();
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	public static LinkedList<Feed> loadRssInfo() {
		LinkedList<Feed> erg = new LinkedList<Feed>();
		File feeds = new File(".feeds");
		if (feeds.exists()) {
			try {
				FileInputStream fileIS = new FileInputStream(feeds);
				ObjectInputStream objectIS = new ObjectInputStream(fileIS);
				erg = (LinkedList<Feed>) objectIS.readObject();
				objectIS.close();
			} catch (IOException e) {
				// System.err.println(e);
			} catch (ClassNotFoundException e) {
				// System.err.println(e);
			} finally {
				if (erg == null)
					erg = new LinkedList<Feed>();
			}
		}
		return erg;
	}

	public static TrayRSS getInstance() {
		return instance;
	}
 
	public TrayIcon getTrayIcon(){
		return null;
	}
}
