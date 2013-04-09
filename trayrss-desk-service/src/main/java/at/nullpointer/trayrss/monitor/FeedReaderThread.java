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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import at.nullpointer.trayrss.configuration.ConfigurationControllerImpl;
import at.nullpointer.trayrss.configuration.timeframes.TimeValidation;
import at.nullpointer.trayrss.configuration.timeframes.TimeValidationImpl;
import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.domain.News;
import at.nullpointer.trayrss.notification.TrayNotifier;
import at.nullpointer.trayrss.persistence.FeedRepository;
import at.nullpointer.trayrss.persistence.NewsRepository;
import at.nullpointer.trayrss.service.NewsService;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Monitors a feed
 * 
 * @author thefake
 * 
 */
public class FeedReaderThread
        implements Runnable {

    private Logger log = Logger.getLogger( FeedReaderThread.class );

    private Feed feedInfo = null;
    @Getter
    private String feedUrl = null;
    private Integer displayCount;
    private TrayNotifier trayNotifier;


    public FeedReaderThread( Feed feedInfo, Integer displayCount, TrayNotifier trayNotifier ) {

        this.feedInfo = feedInfo;
        this.feedUrl = feedInfo.getUrl();
        this.displayCount = displayCount;
        this.trayNotifier = trayNotifier;
    }


    public void run() {

        TimeValidation timeValidation = new TimeValidationImpl();

        while ( isFeedValid() ) {

            if ( timeValidation.isAllowed( ConfigurationControllerImpl.getInstance().getConfigurationModel() ) ) {

                boolean ok = false;

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed;
                List<SyndEntryImpl> content = null;
                try {
                    feed = input.build( new XmlReader( new URL( feedInfo.getUrl() ) ) );
                    content = (List<SyndEntryImpl>)feed.getEntries();
                } catch ( IllegalArgumentException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch ( MalformedURLException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch ( FeedException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch ( IOException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if ( content != null ) {
                    for ( SyndEntryImpl node : content ) {

                        News news = prepareNode( node );

                        ApplicationContext context = new ClassPathXmlApplicationContext( "SpringBeans.xml" );
                        NewsRepository newsRepository = context.getBean( "newsRepository", NewsRepository.class );
                        NewsService newsService = context.getBean( "newsService", NewsService.class );

                        News test = newsRepository.retrieveNews( news.getUri() );

                        if ( test != null && test.equals( news ) ) {
                            news = test;
                            newsService.increaseReadCount( news, 1 );
                            log.debug( "Feed " + getFeedUrl() + ": News Eintrag " + news.getTitle() + " von "
                                    + node.getUri() + " wurden aktualisiert!" );
                        } else {

                            log.debug( "Feed " + getFeedUrl() + ": Neuer Newseintrag " + news.getTitle() + " von "
                                    + node.getUri() );
                        }

                        newsRepository.saveOrUpdate( news );
                        if ( news.getReadCount() < displayCount ) {
                            this.trayNotifier.addToNotify( news, feedInfo );
                            news.setLastRead( new Date() );
                        }

                    }
                }

                ok = true;
                if ( !ok ) {
                    log.debug( "FeedReader reads and prints any RSS/Atom feed type." );
                    log.debug( "The first parameter must be the URL of the feed to read." );
                }
            } else {
                log.debug( "Not within an allowded Time!" );
            }

            deleteOldNews();

            try {
                Thread.sleep( feedInfo.getIntervall() * 1000 * 60 );
            } catch ( InterruptedException e ) {
                log.debug( "FeedReaderThread interrupted!" );
            }
        }
    }


    private boolean isFeedValid() {

        ApplicationContext context = new ClassPathXmlApplicationContext( "SpringBeans.xml" );
        FeedRepository feedRepository = context.getBean( "feedRepository", FeedRepository.class );
        if ( feedRepository.retrieveFeed( this.feedInfo.getUrl() ) == null ) {
            return false;
        } else {
            return true;
        }
    }


    private void deleteOldNews() {

        ApplicationContext context = new ClassPathXmlApplicationContext( "SpringBeans.xml" );
        NewsRepository newsRepository = context.getBean( "newsRepository", NewsRepository.class );

        newsRepository.deleteOlderThan( feedInfo.getUrl(), 60 );

    }


    private News prepareNode( SyndEntryImpl node ) {

        News news = new News();
        news.setAuthor( node.getAuthor() );
        news.setTitle( node.getTitle() );
        news.setPublishedDate( node.getPublishedDate() );
        news.setUpdatedDate( node.getUpdatedDate() );
        news.setUri( node.getUri() );
        news.setFeedUrl( feedInfo.getUrl() );

        news.setUpdatedDate( new Date() );
        return news;
    }

}
