package hu.unideb.inf.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

abstract class DAO {
    /**
     * Logging.
     */
    private static final Logger logger = LoggerFactory.getLogger(DAO.class);
    /**
     * Hibernate session holder.
     */
    private Session session;
    /**
     * Hibernate SessionFactory holder.
     */
    private final SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param sessionFactory {@link #sessionFactory}
     */
    public DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create or update the given object in the storage.
     *
     * @param obj Object from the hu.unideb.inf.domain package.
     */
    protected void createOrUpdate(Object obj) {
        startTransaction();
        logger.debug("Object: " + obj.toString());
        try {
            session.saveOrUpdate(obj);
        } catch (HibernateException ex) {
            errorHandling(ex);
        } finally {
            closeTransaction();
        }
    }

    /**
     * Delete the object.
     *
     * @param obj Object from the hu.unideb.inf.domain package.
     */
    protected void delete(Object obj) {
        startTransaction();
        logger.debug("Object: " + obj.toString());
        try {
            session.delete(obj);
        } catch (HibernateException ex) {
            errorHandling(ex);
        } finally {
            closeTransaction();
        }
    }

    /**
     * Looking for all of the entities that types are same as the given class.
     *
     * @param c Object from the hu.unideb.inf.domain package.
     * @return List of the given object.
     */
    protected List findAll(Class c) {
        logger.debug("Class: " + c.getSimpleName());
        List result = null;
        try {
            startTransaction();
            result = session.createQuery("FROM " + c.getSimpleName()).list();
        } catch (HibernateException ex) {
            errorHandling(ex);
        } finally {
            closeTransaction();
        }
        return result;
    }

    /**
     * Starts an Hibernate transaction.
     */
    private void startTransaction() {
        logger.info("Transaction started");
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    /**
     * Close Hibernate transaction.
     */
    private void closeTransaction() {
        session.getTransaction().commit();
        session.close();
        logger.info("Transaction closed");
    }

    /**
     * Hibernate error handler
     *
     * @param ex Error object.
     */
    private void errorHandling(Exception ex) {
        session.getTransaction().rollback();
        logger.error("FAIL", ex);
    }
}
