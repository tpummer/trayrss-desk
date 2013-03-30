/*
 * TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
 * http://trayrss.nullpointer.at/
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package at.nullpointer.trayrss.gui;

import java.awt.SplashScreen;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Offers the management of the SplashScreen
 * 
 * @author thefake
 * 
 */
@Component
public class TrayRSSSplashScreen {

    private Logger log = Logger.getLogger( TrayRSSSplashScreen.class );

    private SplashScreen splash;
    private long start;
    private long end;


    /**
     * Creates a TrayRSSSplashScreen Object and retrieves a reference to the actual SplashScreen given with the java vm
     * parameter -splash. Also the splash screen timer is initiated.
     */
    public TrayRSSSplashScreen() {

        super();

        try {
            this.splash = SplashScreen.getSplashScreen();
        } catch ( NullPointerException e ) {
            log.warn( "No splash screen found!" );
            log.debug( "at TrayRSSSplashScreen#TrayRSSSplashScreen()" );
        }

        this.start = System.currentTimeMillis();
    }


    /**
     * Closes the SplashScreen after it has been displayed about x seconds
     * 
     * @param seconds Time the SplashScreen has to be displayed
     */
    public void endSplashAfterDisplaytime( int seconds ) {

        endTimer();
        long diff = end - start;
        long time = ( seconds * 1000 ) - ( end - start );
        try {
            show( time > 0 ? time : 0 );
            close();
            log.debug( "Startup time: " + diff + " Milliseconds" );
            log.debug( "Splash shown for " + seconds + " Seconds" );
        } catch ( NullPointerException e ) {
            log.warn( "No splash screen found!" );
            log.debug( "at TrayRSSSplashScreen#endSplashAfterDisplaytime" );
        }
    }


    private void endTimer() {

        this.end = System.currentTimeMillis();
    }


    private void show( long milliseconds )
            throws NullPointerException {

        if ( splash.isVisible() ) {
            try {
                Thread.sleep( milliseconds );
            } catch ( InterruptedException e ) {
                log.debug( "Splash screen not paused!" );
            }
        }
    }


    private void close()
            throws NullPointerException {

        if ( splash.isVisible() )
            splash.close();
        else
            log.debug( "No splash screen found to close!" );
    }

}
