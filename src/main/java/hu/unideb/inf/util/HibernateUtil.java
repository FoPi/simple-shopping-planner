package hu.unideb.inf.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates the the Hibernate SessionFactory
 *
 * @see
 * <a href="http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch01.html#tutorial-firstapp-helpers">Hibernate
 * documentation</a>
 */
public class HibernateUtil {
    /**
     * SessionFactory
     */
    private static final SessionFactory sessionFactory = buildSessionFactory();
    /**
     * Logging
     */
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    /**
     * Creates the sessionFactory
     * @return Returns the configured SessionFactory
     */
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(HibernateUtil.class.getClassLoader().getResource("config/hibernate.cfg.xml"));
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            logger.error("FAIL", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
