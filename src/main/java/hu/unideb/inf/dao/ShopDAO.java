package hu.unideb.inf.dao;

import hu.unideb.inf.domain.Shop;
import org.hibernate.SessionFactory;

import java.util.List;

public class ShopDAO extends DAO {
    /**
     * Constructor. {@link DAO#DAO(org.hibernate.SessionFactory)}
     *
     * @param sessionFactory Hibernate SessionFactory.
     */
    public ShopDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     * @param obj A {@link Shop} object.
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public void createOrUpdate(Shop obj) {
        super.createOrUpdate(obj);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     * @param obj A {@link Shop} object.
     */
    public void delete(Shop obj) {
        super.delete(obj);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public List<Shop> findAll() {
        return super.findAll(Shop.class);
    }
}
