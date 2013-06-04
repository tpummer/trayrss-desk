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
package at.nullpointer.trayrss.checks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Validation of Time Frames
 * 
 * @author Thomas Pummer
 * 
 * @since 1.1
 * 
 */
public final class TimeFrameValidator {

    /**
     * Start index of minutes in a TimeFrame String
     */
    private static final int MIN_POS_START = 2;
    /**
     * End index of minutes in a TimeFrame String
     */
    private static final int MIN_POS_END = 4;

    /**
     * Start index of hours in a TimeFrame String
     */
    private static final int HOUR_POS_START = 0;
    /**
     * End index of hours in a TimeFrame String
     */
    private static final int HOUR_POS_END = 2;


    private TimeFrameValidator() {

    }


    /**
     * Checks a String if it contains valid time frames.
     * 
     * @param input
     * @return
     */
    public static boolean checkTimeFrames( String input ) {

        // Splitten
        String[] splitted = seperateTimeFrames( input );

        boolean result = true;

        for ( String timeframe : splitted ) {
            String[] time = seperateStartAndBegin( timeframe );

            result = result && time.length == 2;
            result = result && CheckLib.checkLong( time[ 0 ] ) && CheckLib.checkLong( time[ 1 ] );
            result = result && checkTime( time[ 0 ] ) && checkTime( time[ 1 ] );

        }
        return result;
    }


    private static String[] seperateStartAndBegin( String timeframe ) {

        return timeframe.split( "-" );
    }


    private static String[] seperateTimeFrames( String input ) {

        return input.trim().split( " " );
    }


    private static boolean checkTime( String input ) {

        boolean result = true;

        DateFormat sdf = new SimpleDateFormat( "HHmm" );
        try {
            sdf.parse( input );
        } catch ( ParseException e ) {
            result = false;
        }
        int hour = Integer.parseInt( input.substring( HOUR_POS_START, HOUR_POS_END ) );
        int min = Integer.parseInt( input.substring( MIN_POS_START, MIN_POS_END ) );

        return result && !( hour < 0 || hour > 24 || min < 0 || min > 60 );
    }

}
