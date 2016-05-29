package hu.unideb.inf.dao;

import hu.unideb.inf.domain.Shop;
import hu.unideb.inf.domain.ShoppingItem;
import org.hibernate.SessionFactory;

import java.util.List;

public class ShoppingItemDAO extends DAO {
    /**
     * Constructor. {@link hu.unideb.inf.dao.DAO#DAO(org.hibernate.SessionFactory)}
     *
     * @param sessionFactory Hibernate SessionFactory.
     */
    public ShoppingItemDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * {@link hu.unideb.inf.dao.DAO#createOrUpdate(Object)}.
     * @param obj A {@link hu.unideb.inf.domain.ShoppingItem} object.
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(String)}.
     */
    public void createOrUpdate(ShoppingItem obj) {
        super.createOrUpdate(obj);
    }

    /**
     * {@link hu.unideb.inf.dao.DAO#createOrUpdate(Object)}.
     * @param obj A {@link hu.unideb.inf.domain.ShoppingItem} object.
     */
    public void delete(ShoppingItem obj) {
        super.delete(obj);
    }

    /**
     * {@link hu.unideb.inf.dao.DAO#createOrUpdate(Object)}.
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(String)}.
     */
    public List<ShoppingItem> findAll() {
        return super.findAll(ShoppingItem.class);
    }
}
