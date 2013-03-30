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
package at.nullpointer.trayrss.persistence;

import org.apache.log4j.Logger;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import at.nullpointer.trayrss.persistence.model.FeedEntity;
import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * @see PersistenceAdapter
 * 
 * @author Thomas Pummer
 * 
 */
@Component
public class PersistenceAdapterImpl
        implements PersistenceAdapter, InitializingBean, DisposableBean {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger( PersistenceAdapterImpl.class );

    /**
     * Singleton Instance
     */
    private static PersistenceAdapter instance;

    /**
     * SessionFactoryRepository
     */
    private SessionFactoryRepository sessionFactoryRepository;


    /**
     * Konstruktor
     */
    public PersistenceAdapterImpl() {

        sessionFactoryRepository = SessionFactoryRepository.getInstance();
    }


    private void initHibernate() {

        final PersistenceConfiguration persistenceConfig = new PersistenceConfiguration();

        final AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.setProperty( "hibernate.dialect",
                persistenceConfig.getProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" ) );
        // cfg.setProperty( "dialect", pc.getProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" ) );
        cfg.setProperty( "hibernate.connection.driver_class",
                persistenceConfig.getProperty( "hibernate.connection.driver_class", "org.hsqldb.jdbcDriver" ) );
        cfg.setProperty( "hibernate.connection.url", persistenceConfig.getProperty( "hibernate.connection.url",
                "jdbc:hsqldb:file:${user.home}/TrayRSS/data/feeds;shutdown=true" ) ); // e.g."jdbc:mysql://localhost:3306/mydatabase"
        cfg.setProperty( "hibernate.connection.username",
                persistenceConfig.getProperty( "hibernate.connection.username", "sa" ) );
        cfg.setProperty( "hibernate.connection.password",
                persistenceConfig.getProperty( "hibernate.connection.password", "" ) );
        // cfg.setProperty( "hibernate.connection.pool_size", pc.getProperty( "hibernate.connection.pool_size", "" ) );
        // cfg.setProperty( "hibernate.connection.autocommit", pc.getProperty( "hibernate.connection.autocommit", "" )
        // );
        cfg.setProperty( "hibernate.cache.provider_class", persistenceConfig.getProperty(
                "hibernate.cache.provider_class", " org.hibernate.cache.HashtableCacheProvider" ) );
        cfg.setProperty( "hibernate.hbm2ddl.auto", persistenceConfig.getProperty( "hibernate.hbm2ddl.auto", "update" ) );
        cfg.setProperty( "hibernate.show_sql", persistenceConfig.getProperty( "hibernate.show_sql", "false" ) );
        // cfg.setProperty( "hibernate.format_sql", pc.getProperty( "hibernate.format_sql", "sa" ) );
        // cfg.setProperty( "hibernate.use_sql_comments", pc.getProperty( "hibernate.use_sql_comments", "sa" ) );
        cfg.setProperty( "hibernate.transaction.factory_class", persistenceConfig.getProperty(
                "hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory" ) );
        // cfg.setProperty( "transaction.factory_class", pc.getProperty( "hibernate.transaction.factory_class",
        // "org.hibernate.transaction.JDBCTransactionFactory" ) );
        // cfg.setProperty( "hibernate.current_session_context_class",
        // pc.getProperty( "hibernate.current_session_context_class", "sa" ) );
        // cfg.setProperty( "org.hibernate.flushMode", pc.getProperty( "org.hibernate.flushMode", "sa" ) );
        // cfg.setProperty( "hibernate.generate_statistics", pc.getProperty( "hibernate.generate_statistics", "sa" ) );

        // Hibernate entities
        cfg.addAnnotatedClass( FeedEntity.class );
        cfg.addAnnotatedClass( NewsEntity.class );

        sessionFactoryRepository.setSessionFactory( cfg.buildSessionFactory() );

        // sessionFactoryRepository.setSessionFactory( new AnnotationConfiguration().configure().buildSessionFactory()
        // );

    }


    /**
     * Returns the Singleton Instance of the {@link SessionFactoryRepository} TODO CDI
     * 
     * @return Singleton PersistenceAdapter
     */
    public static synchronized PersistenceAdapter getInstance() {

        if ( instance == null ) {
            instance = new PersistenceAdapterImpl();
        }
        return instance;
    }


    /**
     * Init the database
     */
    @Override
    public void afterPropertiesSet()
            throws Exception {

        initHibernate();
        LOG.debug( "Hibernate ready, SessionFactory ready" );

    }


    /**
     * Closes all connections
     */
    @Override
    public void destroy()
            throws Exception {

        sessionFactoryRepository.getSessionFactory().close();
        LOG.debug( "SessionFactory closed" );

    }

}
