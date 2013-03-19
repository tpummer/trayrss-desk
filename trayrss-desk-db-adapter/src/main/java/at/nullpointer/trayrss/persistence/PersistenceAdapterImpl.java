package at.nullpointer.trayrss.persistence;

import org.apache.log4j.Logger;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @see PersistenceAdapter
 * 
 * @author Thomas Pummer
 * 
 */
public class PersistenceAdapterImpl
        implements PersistenceAdapter {

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


    /**
     * @see PersistenceAdapter#start()
     */
    public boolean start() {

        boolean result = false;

        try {
            initHibernate();
            result = true;

        } catch ( Exception e ) {
            LOG.error( "Error loading Database: " + e.getMessage(), e );
        }

        return result;
    }


    /**
     * @see PersistenceAdapter#shutdown()
     */
    public boolean shutdown() {

        sessionFactoryRepository.getSessionFactory().close();
        return true;
    }


    private void initHibernate()
            throws Exception {

        // TODO HibernateConfig Manual

        // final AnnotationConfiguration cfg = new AnnotationConfiguration();
        // cfg.setProperty( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
        // cfg.setProperty( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
        // cfg.setProperty( "hibernate.connection.url", "" ); // e.g."jdbc:mysql://localhost:3306/mydatabase"
        // cfg.setProperty( "hibernate.connection.username", "" );
        // cfg.setProperty( "hibernate.connection.password", "" );
        // cfg.setProperty( "hibernate.connection.pool_size", "5" );
        // cfg.setProperty( "hibernate.connection.autocommit", "false" );
        // cfg.setProperty( "hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider" );
        // // cfg.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        // cfg.setProperty( "hibernate.show_sql", "true" );
        // cfg.setProperty( "hibernate.format_sql", "true" );
        // cfg.setProperty( "hibernate.use_sql_comments", "true" );
        // cfg.setProperty( "hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory" );
        // cfg.setProperty( "hibernate.current_session_context_class", "thread" );
        // cfg.setProperty( "org.hibernate.flushMode", "COMMIT" );
        // cfg.setProperty( "hibernate.generate_statistics", "true" );
        //
        // // Hibernate entities
        // cfg.addAnnotatedClass( FeedEntity.class );
        // cfg.addAnnotatedClass( NewsEntity.class );

        // sessionFactoryRepository.setSessionFactory( cfg.buildSessionFactory() );

        sessionFactoryRepository.setSessionFactory( new AnnotationConfiguration().configure().buildSessionFactory() );

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

}
