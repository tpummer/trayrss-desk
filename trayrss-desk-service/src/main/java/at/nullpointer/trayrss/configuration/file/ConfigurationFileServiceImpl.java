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
package at.nullpointer.trayrss.configuration.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import at.nullpointer.trayrss.configuration.ConfigurationConstants;
import at.nullpointer.trayrss.configuration.ConfigurationControllerImpl;

/**
 * @see ConfigurationFileService
 * 
 * @author Thomas Pummer
 * 
 */
public class ConfigurationFileServiceImpl
        implements ConfigurationFileService {

    /**
     * Logging instance
     */
    private static final Logger LOG = Logger.getLogger( ConfigurationControllerImpl.class );


    /**
     * @see ConfigurationFileService#isConfigInUserDir()
     */
    public boolean isConfigInUserDir() {

        boolean result = false;

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "User Home Directory: " + ConfigurationConstants.CONFIG_USER );
        }

        final File configFile = new File( ConfigurationConstants.CONFIG_USER );

        if ( configFile.exists() ) {
            result = true;
        }

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Config File exists: " + result );
        }

        return result;
    }


    /**
     * @see ConfigurationFileService#copyConfigToUserDir()
     */
    public void copyConfigToUserDir() {

        LOG.debug( "Start to Copy the config File into the User directory" );

        InputStreamReader defaultStreamReader;
        OutputStreamWriter userStreamWriter;
        try {
            defaultStreamReader = new InputStreamReader( new FileInputStream( ConfigurationConstants.CONFIG_STANDARD ),
                    "UTF-8" );
            userStreamWriter = new OutputStreamWriter( new FileOutputStream( ConfigurationConstants.CONFIG_USER ),
                    "UTF-8" );
            int character;

            while ( ( character = defaultStreamReader.read() ) != -1 )
                userStreamWriter.write( character );

            defaultStreamReader.close();
            userStreamWriter.close();
        } catch ( FileNotFoundException e ) {
            LOG.error( "Standard configuration not found!" );
            LOG.error( e.getMessage() );
        } catch ( IOException e ) {
            LOG.error( "Could not copy the configuration to the user directory!" );
            LOG.error( e.getMessage() );
        }

    }

}
