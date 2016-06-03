package hu.unideb.inf.dao;

import hu.unideb.inf.domain.ProductToShop;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductToShopDAO extends DAO {
    /**
     * Constructor. {@link DAO#DAO(org.hibernate.SessionFactory)}
     *
     * @param sessionFactory Hibernate SessionFactory.
     */
    public ProductToShopDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     * @param obj A {@link ProductToShop} object.
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public void createOrUpdate(ProductToShop obj) {
        super.createOrUpdate(obj);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     * @param obj A {@link ProductToShop} object.
     */
    public void delete(ProductToShop obj) {
        super.delete(obj);
    }

    /**
     * {@link DAO#createOrUpdate(java.lang.Object)}.
     * @throws org.hibernate.TransactionException {@link org.hibernate.TransactionException#TransactionException(java.lang.String)}.
     */
    public List<ProductToShop> findAll() {
        return super.findAll(ProductToShop.class);
    }
}
