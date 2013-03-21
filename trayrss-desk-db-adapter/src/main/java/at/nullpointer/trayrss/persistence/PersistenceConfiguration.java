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
package at.nullpointer.trayrss.persistence;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Offers an accessor to the configuration of the persistence
 * 
 * @author Thomas Pummer
 * 
 */
public class PersistenceConfiguration {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( PersistenceConfiguration.class );

    /**
     * Store of the properties
     */
    private Properties properties;


    /**
     * Constructor loads properites
     */
    public PersistenceConfiguration() {

        super();
        loadConfig();
    }


    /**
     * Load a property from the PersistenceConfiguration
     * 
     * @param key
     * @param defaultValue
     * @return String property
     */
    public String getProperty( final String key, final String defaultValue ) {

        return properties.getProperty( key, defaultValue );
    }


    private final void loadConfig() {

        properties = new Properties();
        try {
            final BufferedInputStream stream = new BufferedInputStream( new FileInputStream( "persistence.properties" ) );
            properties.load( stream );
            stream.close();
        } catch ( FileNotFoundException e ) {
            LOG.error( "Could not open persistence property file", e );
        } catch ( IOException e ) {
            LOG.error( "Could not open persistence property file", e );
        }

    }

}
