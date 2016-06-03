package hu.unideb.inf.dao;

import hu.unideb.inf.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;

import java.util.List;

public class ProductDAO extends DAO {
    /**
     * Constructor. {@link DAO#DAO(org.hibernate.SessionFactory)}
     *
     * @param sessionFactory Hibernate SessionFactory.
     */
    public ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     * @param obj A {@link Product} object.
     * @throws TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public void createOrUpdate(Product obj) {
        super.createOrUpdate(obj);
    }

    /**
     * {@link DAO#delete(java.lang.Object)}.
     * @param obj A {@link Product} object.
     */
    public void delete(Product obj) {
        super.delete(obj);
    }

    /**
     * {@link DAO#findAll(Class)} (java.lang.Object)}.
     * @throws TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public List<Product> findAll() {
        return super.findAll(Product.class);
    }
}
