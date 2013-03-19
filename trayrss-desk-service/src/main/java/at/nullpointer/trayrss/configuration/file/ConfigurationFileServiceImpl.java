package at.nullpointer.trayrss.configuration.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

        final File defaultConfigFile = new File( ConfigurationConstants.CONFIG_STANDARD );
        final File userConfigFile = new File( ConfigurationConstants.CONFIG_USER );

        FileReader defaultFileReader;
        FileWriter userFileWriter;
        try {
            defaultFileReader = new FileReader( defaultConfigFile );
            userFileWriter = new FileWriter( userConfigFile );
            int character;

            while ( ( character = defaultFileReader.read() ) != -1 )
                userFileWriter.write( character );

            defaultFileReader.close();
            userFileWriter.close();
        } catch ( FileNotFoundException e ) {
            LOG.error( "Standard configuration not found!" );
            LOG.error( e.getMessage() );
        } catch ( IOException e ) {
            LOG.error( "Could not copy the configuration to the user directory!" );
            LOG.error( e.getMessage() );
        }

    }

}
