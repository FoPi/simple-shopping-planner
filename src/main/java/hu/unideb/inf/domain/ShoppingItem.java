package hu.unideb.inf.domain;

import javax.persistence.*;

@Entity
@Table
public class ShoppingItem {
    @Id @GeneratedValue
    @Column
    private int id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Product product;
    @Column
    private int quantity;

    /**
     * @param product {@link Product} that is needed to buy
     * @param quantity Quantity of the given product that needs
     */
    public ShoppingItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ShoppingItem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
