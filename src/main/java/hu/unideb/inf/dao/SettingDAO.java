package hu.unideb.inf.dao;

import hu.unideb.inf.domain.Setting;
import org.hibernate.SessionFactory;

import java.util.List;

public class SettingDAO extends DAO {
    /**
     * Constructor. {@link DAO#DAO(org.hibernate.SessionFactory)}
     *
     * @param sessionFactory Hibernate SessionFactory.
     */
    public SettingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     *
     * @param obj A {@link hu.unideb.inf.domain.Setting} object.
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public void createOrUpdate(Setting obj) {
        super.createOrUpdate(obj);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     *
     * @param obj A {@link hu.unideb.inf.domain.Setting} object.
     */
    public void delete(Setting obj) {
        super.delete(obj);
    }

    /**
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public List<Setting> findAll() {
        return super.findAll(Setting.class);
    }
}
