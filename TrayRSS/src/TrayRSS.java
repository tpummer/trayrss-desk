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

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SplashScreen;
import java.awt.SystemTray;
import java.awt.Toolkit;
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
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JOptionPane;

public class TrayRSS {

	private static SystemTray tray = null;
	private static LinkedList<FeedInfo> rssUrlSave = null;
	//nach den anderen Vairablen die befüllt werden
	private static TrayRSS instance = new TrayRSS();

	private TrayRSS() {
		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			final TrayIcon trayIcon;
			TrayRSS.rssUrlSave = loadRssInfo();

			//Image image = Toolkit.getDefaultToolkit().getImage(
			//		getClass().getResource("/img/rsstrayicon.PNG"));
			 Image image =
			 Toolkit.getDefaultToolkit().getImage("./img/rsstrayicon.PNG");			

			MouseListener mouseListener = new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse clicked!");
				}

				public void mouseEntered(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse entered!");
				}

				public void mouseExited(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse exited!");
				}

				public void mousePressed(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse pressed!");
				}

				public void mouseReleased(MouseEvent e) {
					// System.out.println("Tray Icon - Mouse released!");
				}
			};

			PopupMenu popup = new PopupMenu();

			ActionListener ReadListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String allFeeds = "";
					for (Iterator<FeedInfo> it = rssUrlSave.iterator(); it
							.hasNext();) {
						FeedInfo help = it.next();
						String lastuse = "-";
						if (help.getLastAction() != null) {
							SimpleDateFormat format = new SimpleDateFormat(
									"YYYY-MM-DD HH:mm:ss");
							lastuse = format.format(help.getLastAction());
						}
						allFeeds = allFeeds + help.getUrl()
								+ " im Intervall von " + help.getIntervall()
								/ 1000 / 60 + " min zuletzt am: " + lastuse
								+ "\n";
					}
					JOptionPane.showMessageDialog(null, allFeeds);
				}
			};

			MenuItem listRss = new MenuItem("List RSS");
			listRss.addActionListener(ReadListener);
			popup.add(listRss);

			ActionListener addListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					URL url = null;
					long checktime = 0;
					try {
						url = new URL(JOptionPane.showInputDialog(
								"Bitte geben Sie einen Text ein", "http://"));
						checktime = 60 * 1000 * Long
								.parseLong(JOptionPane
										.showInputDialog(
												"Geben die den Prüfintervall in Minuten ein!",
												"30"));
						rssUrlSave.add(new FeedInfo(url, checktime, null));
					} catch (HeadlessException e1) {
						JOptionPane
								.showMessageDialog(
										null,
										"Sie haben keine gültige URL eingegeben!\nGeben sie die URL mitsamt \"http://\" ein");
					} catch (MalformedURLException e1) {
						JOptionPane
								.showMessageDialog(
										null,
										"Sie haben keine gültige URL eingegeben!\nGeben sie die URL mitsamt \"http://\" ein");
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,
								"Sie haben keine gültige Zahl eingegeben!");
					}
				}
			};

			MenuItem addRss = new MenuItem("Add RSS");
			addRss.addActionListener(addListener);
			popup.add(addRss);

			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO alle threads beenden
					getInstance().saveRssInfo();
					System.exit(0);
				}
			};

			MenuItem exitItem = new MenuItem("Exit");
			exitItem.addActionListener(exitListener);
			popup.add(exitItem);

			trayIcon = new TrayIcon(image, "RSSTRAY", popup);

			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					trayIcon.displayMessage("Action Event",
							"An Action Event Has Been Performed!",
							TrayIcon.MessageType.INFO);
				}
			};

			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(actionListener);
			trayIcon.addMouseListener(mouseListener);

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println("TrayIcon could not be added.");
			}
			
			ExecutorService threadExecutor = Executors.newFixedThreadPool( 5 );
			for(Iterator<FeedInfo> it = rssUrlSave.iterator(); it.hasNext();){
				FeedReaderThread feedReader = new FeedReaderThread(it.next(), trayIcon);
				//TODO Thread wo speichern zum interruppten
				threadExecutor.execute(feedReader);
			}
			
		}

		else {
			System.err.println("Systray not supported!");
			System.exit(1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TrayRSSSplashScreen splash = new TrayRSSSplashScreen();
		splash.show(3);
		splash.close();

		// TODO Feed auslesen
		// TODO neuer Feedinhalt zwischenpuffern
		// TODO Icon verändern
		// TODO Artikel im browser öffnen
		// TODO Meldung an besitzer
		// TODO feed löschen
		// TODO intervall anpassen
		// TODO Drag n Drop einer FeedURL auf das Icon

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

	public static LinkedList<FeedInfo> loadRssInfo() {
		LinkedList<FeedInfo> erg = new LinkedList<FeedInfo>();
		File feeds = new File(".feeds");
		if (feeds.exists()) {
			try {
				FileInputStream fileIS = new FileInputStream(feeds);
				ObjectInputStream objectIS = new ObjectInputStream(fileIS);
				erg = (LinkedList<FeedInfo>) objectIS.readObject();
				objectIS.close();
			} catch (IOException e) {
				//System.err.println(e);
			} catch (ClassNotFoundException e) {
				//System.err.println(e);
			} finally {
				if (erg == null)
					erg = new LinkedList<FeedInfo>();
			}
		}
		return erg;
	}

	public static TrayRSS getInstance() {
		return instance;
	}

}
