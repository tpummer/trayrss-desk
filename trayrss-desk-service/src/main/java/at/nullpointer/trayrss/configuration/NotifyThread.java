package at.nullpointer.trayrss.configuration;

import java.util.Set;

import lombok.Setter;

/**
 * Thread handles the notification of several listeners
 * 
 * @author Thomas Pummer
 * 
 */
class NotifyThread
        extends Thread {

    /**
     * Listeners to be notified
     */
    @Setter
    private Set<ChangeListener> changeListener;


    /**
     * Notifies the Listeners that a change has happend
     */
    @Override
    public void run() {

        for ( ChangeListener singleListener : this.changeListener ) {
            singleListener.notifyChange();
        }
    }
}