package at.nullpointer.trayrss.persistence;

/**
 * The {@link PersistenceAdapter} encapsulates the Persistence
 * 
 * @author Thomas Pummer
 * 
 */
public interface PersistenceAdapter {

    /**
     * Starts the Persistence
     * 
     * @return true/false
     */
    boolean start();


    /**
     * Shutdown of the Persistence
     * 
     * @return true/false
     */
    boolean shutdown();
}
