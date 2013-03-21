/*
 * w TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
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
package at.nullpointer.trayrss;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.StartUp;
import at.nullpointer.trayrss.gui.TrayRSSSplashScreen;

/**
 * Startclass of TrayRSS
 * 
 * @author Thomas Pummer
 * 
 */
public final class TrayRSS {

    /**
     * Time the splash is shown
     */
    private static final int THREE_SECONDS = 3;
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( TrayRSS.class );


    private TrayRSS() {

    }


    /**
     * Startup of TrayRSS
     * 
     * @param args
     */
    public static void main( final String[] args ) {

        final Package trayRssPackage = TrayRSS.class.getPackage();
        ReferenceCollection.TRAYRSS_APP_TITLE = "TrayRSS " + trayRssPackage.getImplementationVersion();

        boolean debug = false;

        if ( args.length != 0 ) {
            if ( args[ 0 ].equals( "-debug" ) ) {
                debug = true;
            } else {
                System.out.println( "You may start the program within the "
                        + "debug mode by using -debug as parameter." );
            }
        }

        LOG.debug( "Starting splashscreen" );
        final TrayRSSSplashScreen splash = new TrayRSSSplashScreen();
        LOG.debug( "Initializing startup routine" );
        new StartUp( debug );
        splash.endSplashAfterDisplaytime( THREE_SECONDS );

    }

}
