package at.nullpointer.trayrss.configuration.file;

/**
 * Provides Services for the configuration file
 * 
 * @author Thomas Pummer
 * 
 */
public interface ConfigurationFileService {

    /**
     * Checks if there is a config file in the user directory
     * 
     * @return boolean true/false
     */
    public boolean isConfigInUserDir();


    /**
     * Copys the User configuration into the user directory
     */
    public void copyConfigToUserDir();

}
