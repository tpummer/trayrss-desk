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
package at.nullpointer.trayrss.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hibernate.PropertyNotFoundException;

import at.nullpointer.trayrss.configuration.ChangeListener;
import at.nullpointer.trayrss.configuration.ConfigurationControllerImpl;
import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.notification.TrayNotifier;
import at.nullpointer.trayrss.persistence.FeedRepository;

public final class Monitor
        implements ChangeListener {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( Monitor.class );

    private static Monitor instance;


    public static synchronized Monitor getInstance() {

        if ( trayNotifier == null )
            throw new PropertyNotFoundException( "No TrayNotifier was set!" );
        if ( instance == null ) {
            instance = new Monitor();
        }
        return instance;
    }

    LinkedList<FeedReaderThread> monitoredFeeds;
    ExecutorService threadExecutor;
    FeedRepository feedRepository;
    private static TrayNotifier trayNotifier;


    private Monitor() {

        monitoredFeeds = new LinkedList<FeedReaderThread>();
        threadExecutor = Executors.newFixedThreadPool( 20 );
        feedRepository = ReferenceCollection.getInstance().getContext()
                .getBean( "feedRepository", FeedRepository.class );

        loadFeeds();

    }


    public void loadFeeds() {

        List<Feed> feeds = (List<Feed>)feedRepository.retrieveFeeds();
        Integer displayCount = ConfigurationControllerImpl.getInstance().getConfigurationModel().getDisplayCount();

        for ( Feed feed : feeds ) {
            FeedReaderThread thread = new FeedReaderThread( feed, displayCount, this.trayNotifier );
            threadExecutor.execute( thread );
            monitoredFeeds.add( thread );
        }

    }


    public void feedChanged() {

        stopAll( 2 );
        loadFeeds();

    }


    public void stopAll( int seconds ) {

        try {
            threadExecutor.shutdown();
            if ( !threadExecutor.awaitTermination( seconds, TimeUnit.SECONDS ) ) {
                threadExecutor.shutdownNow(); // Cancel currently executing
                                              // tasks
                // Wait a while for tasks to respond to being cancelled
                if ( !threadExecutor.awaitTermination( seconds, TimeUnit.SECONDS ) )
                    LOG.error( "Pool did not terminate" );
            }
        } catch ( InterruptedException ie ) {
            // (Re-)Cancel if current thread also interrupted
            threadExecutor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

        threadExecutor = Executors.newFixedThreadPool( 20 );

    }


    public static void setTrayNotifier( TrayNotifier inputTrayNotifier ) {

        trayNotifier = inputTrayNotifier;

    }


    public void notifyChange() {

        feedChanged();

    }

}