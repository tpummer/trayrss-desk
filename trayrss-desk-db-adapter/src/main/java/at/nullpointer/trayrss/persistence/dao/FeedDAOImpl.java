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
package at.nullpointer.trayrss.persistence.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.nullpointer.trayrss.persistence.SessionFactoryRepository;
import at.nullpointer.trayrss.persistence.model.FeedEntity;

/**
 * @see FeedDAO
 * 
 * @author Thomas Pummer
 * 
 */
public class FeedDAOImpl
        implements FeedDAO {

    /**
     * @see FeedDAO
     */
    public FeedEntity findFeedById( Long id ) {

        final Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();

        final Transaction transaction = session.beginTransaction();
        final FeedEntity feed = (FeedEntity)session.get( FeedEntity.class, id );

        transaction.commit();
        session.close();

        return feed;

    }


    /**
     * @see FeedDAO
     */
    public Collection<FeedEntity> getFeeds() {

        final Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();

        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery( "select f from FeedEntity f" );
        final List<FeedEntity> feeds = (List<FeedEntity>)query.list();

        transaction.commit();
        session.close();

        return feeds;
    }


    /**
     * @see FeedDAO
     */
    public void save( final FeedEntity feed ) {

        final Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();

        final Transaction transaction = session.beginTransaction();

        if ( feed.getId() != null && session.load( FeedEntity.class, feed.getId() ) != null ) {
            session.update( feed );
        } else {
            session.save( feed );

        }

        transaction.commit();
        session.close();

    }


    /**
     * @see FeedDAO
     */
    public void deleteById( Long id ) {

        final Session session = SessionFactoryRepository.getInstance().getSessionFactory().openSession();

        final Transaction transaction = session.beginTransaction();

        final String hqlN = "delete from NewsEntity n where feed_id = " + id.longValue();
        final Query queryN = session.createQuery( hqlN );
        queryN.executeUpdate();

        final String hql = "delete from FeedEntity f where id = " + id.longValue();
        final Query query = session.createQuery( hql );
        query.executeUpdate();

        transaction.commit();
        session.close();

    }

}
