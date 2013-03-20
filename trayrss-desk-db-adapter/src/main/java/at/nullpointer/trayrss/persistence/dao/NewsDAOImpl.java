/*
 * TrayRSS - simply notification of feed information (c) 2009-2011 TrayRSS Developement Team visit the project at
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
package at.nullpointer.trayrss.persistence.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.nullpointer.trayrss.persistence.SessionFactoryRepository;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * @see NewsDAO
 * @author Thomas Pummer
 * 
 */
public class NewsDAOImpl
        implements NewsDAO {

    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger( NewsDAOImpl.class );


    /**
     * @see NewsDAO
     */
    public void deleteById( Long id ) {

        Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.delete( session.load( NewsEntity.class, id ) );

        tx.commit();
        session.close();

    }


    /**
     * @see NewsDAO
     */
    public NewsEntity findNewsById( Long id ) {

        Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        NewsEntity news = (NewsEntity)session.get( NewsEntity.class, id );

        tx.commit();
        session.close();

        return news;
    }


    /**
     * @see NewsDAO
     */
    public Collection<NewsEntity> getNews() {

        Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery( "select n from NewsEntity n" );
        List<NewsEntity> news = (List<NewsEntity>)query.list();

        tx.commit();
        session.close();

        return news;
    }


    /**
     * @see NewsDAO
     */
    public void save( NewsEntity news )
            throws SQLException {

        Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        FeedDAO feedDao = new FeedDAOImpl();
        if ( news.getId() != null && session.load( NewsEntity.class, news.getId() ) != null ) {
            if ( feedDao.findFeedById( news.getFeed().getId() ) != null )
                session.update( news );
        } else {
            if ( feedDao.findFeedById( news.getFeed().getId() ) != null )
                session.save( news );

        }

        tx.commit();
        session.close();

    }


    public NewsEntity getNewsByData( NewsEntity news ) {

        Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery( "select n from NewsEntity n where n.uri = :uri" ).setParameter( "uri",
                news.getUri() );
        NewsEntity erg = null;
        try {
            erg = (NewsEntity)query.uniqueResult();
        } catch ( HibernateException e ) {
            LOG.error( news.getTitle() + " at " + news.getUri() + " has a duplicated entry!" );
            LOG.debug( query.getQueryString() );
            List<NewsEntity> list = query.list();
            for ( NewsEntity dupe : list ) {
                if ( erg == null ) {
                    erg = dupe;
                } else {
                    if ( dupe.getLastRead().after( erg.getLastRead() ) ) {
                        deleteById( erg.getId() );
                        erg = dupe;
                        LOG.debug( "removed duplicated news with id" + erg.getId() );
                    } else {
                        deleteById( dupe.getId() );
                        LOG.debug( "removed duplicated news with id" + news.getId() );
                    }
                }
            }
        }

        tx.commit();
        session.close();

        return erg;

    }


    /**
     * @see NewsDAO
     */
    public void deleteOlderThanTwoMonth( Long id ) {

        Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Calendar now = GregorianCalendar.getInstance();
        now.add( Calendar.MONTH, -2 );

        String hqlN = "delete from NewsEntity n where feed_id = :id and UPDATEDDATE < :date";

        Query queryN = session.createQuery( hqlN ).setLong( "id", id ).setDate( "date", now.getTime() );

        queryN.executeUpdate();

        tx.commit();
        session.close();
    }

}
