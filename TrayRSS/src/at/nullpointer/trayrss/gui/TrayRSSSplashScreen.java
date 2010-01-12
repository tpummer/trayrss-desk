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
package at.nullpointer.trayrss.gui;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

import java.awt.*;


/**
 * Offers the management of the SplashScreen
 * 
 * @author thefake
 *
 */
public class TrayRSSSplashScreen {
	
	private SplashScreen splash;
	private long start;
	private long end;
	
	/**
	 * Creates a TrayRSSSplashScreen Object and retrieves a
	 * reference to the actual SplashScreen given with the
	 * java vm parameter -splash. Also the splash screen timer
	 * is initiated. 
	 */
	public TrayRSSSplashScreen() {
		super();
		
		try {
			this.splash = SplashScreen.getSplashScreen();
		} catch (NullPointerException e) {
			ReferenceCollection.LOG.warn("No splash screen found!");
			ReferenceCollection.LOG.debug("at TrayRSSSplashScreen#TrayRSSSplashScreen()");
		}
		
		this.start = System.currentTimeMillis(); 
	}

	/**
	 * Closes the SplashScreen after it has been displayed
	 * about x seconds
	 * 
	 * @param seconds Time the SplashScreen has to be displayed
	 */
	public void endSplashAfterDisplaytime(int seconds){
		endTimer();
		long diff = end - start;
		long time = (seconds * 1000) - (end-start);
		try {
			show(time > 0 ? time : 0);
			close();
			ReferenceCollection.LOG.debug("Startup time: " + diff + " Milliseconds");
			ReferenceCollection.LOG.debug("Splash shown for " + seconds + " Seconds");
		} catch (NullPointerException e) {
			ReferenceCollection.LOG.warn("No splash screen found!");
			ReferenceCollection.LOG.debug("at TrayRSSSplashScreen#endSplashAfterDisplaytime");
		}		
	}

	private void endTimer(){
		this.end = System.currentTimeMillis(); 
	}
	
	private void show (long milliseconds)throws NullPointerException{
		if (splash.isVisible()) {
			try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
	
			}
		}
	}

	private void close() throws NullPointerException{
		splash.close();
	}

}
