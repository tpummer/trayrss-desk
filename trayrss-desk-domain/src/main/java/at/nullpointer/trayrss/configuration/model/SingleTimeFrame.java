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
package at.nullpointer.trayrss.configuration.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.Data;

import org.apache.log4j.Logger;

/**
 * Representation of a TimeFrame
 * 
 * @author Thomas Pummer
 * 
 */
@Data
public class SingleTimeFrame {

    /**
     * The first number with 2 digits
     */
    private static final int FIRST_TWO_DIGIT_NUMBER = 10;

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( SingleTimeFrame.class );

    /**
     * Start Hour of the Timeframe
     */
    private int startHour;
    /**
     * Start Minute of the Timeframe
     */
    private int startMin;
    /**
     * TimeFrame ends in this hour
     */
    private int endHour;
    /**
     * End Minute of the Timeframe
     */
    private int endMin;


    /**
     * Default Constructor of {@link SingleTimeFrame}. Checks if each part of the timeframe is a valid Date 'HHmm' and
     * sets field. returns an IllegalArgumentException if split is null or split is not of size 2
     * 
     * @param split
     */
    public SingleTimeFrame( final String[] split ) {

        if ( split == null ) {
            throw new IllegalArgumentException( "Argument split in SingleTimeFrame was null." );
        }
        if ( split.length != 2 ) {
            throw new IllegalArgumentException( "Argument split didn't match the valid size of 2." );
        }

        SimpleDateFormat sdf = new SimpleDateFormat( "HHmm" );

        try {
            sdf.parse( split[ 0 ] );
            sdf.parse( split[ 1 ] );
        } catch ( ParseException e ) {
            LOG.error( "Error parsing Timeframes on Check" );
            throw new IllegalArgumentException( e );
        }
        this.startHour = Integer.parseInt( split[ 0 ].substring( 0, 2 ) );
        this.startMin = Integer.parseInt( split[ 0 ].substring( 2, 4 ) );
        this.endHour = Integer.parseInt( split[ 1 ].substring( 0, 2 ) );
        this.endMin = Integer.parseInt( split[ 1 ].substring( 2, 4 ) );
    }


    /**
     * Prints the SingleTimeFrame in HHmm-HHmm
     */
    public String toString() {

        StringBuilder result = new StringBuilder();
        if ( this.startHour < FIRST_TWO_DIGIT_NUMBER ) {
            result.append( 0 );
        }
        result.append( this.startHour );
        if ( this.startMin < FIRST_TWO_DIGIT_NUMBER ) {
            result.append( 0 );
        }
        result.append( this.startMin );

        result.append( '-' );

        if ( this.endHour < FIRST_TWO_DIGIT_NUMBER ) {
            result.append( 0 );
        }
        result.append( this.endHour );
        if ( this.endMin < FIRST_TWO_DIGIT_NUMBER ) {
            result.append( 0 );
        }
        result.append( this.endMin );

        return result.toString();
    }

}
