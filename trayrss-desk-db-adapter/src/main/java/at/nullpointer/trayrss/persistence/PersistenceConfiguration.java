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
