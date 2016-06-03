package hu.unideb.inf.domain;

import javax.persistence.*;

@Entity
@Table
public class ShoppingItem {
    @Id @GeneratedValue
    @Column
    private int id;

    @Column
    private String productName;
    @Column
    private float quantity;

    /**
     * @param productName The name of the needed product
     * @param quantity Quantity of the given product that needs
     */
    public ShoppingItem(String productName, float quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public ShoppingItem() {}

    //<editor-fold desc="Setter & Getter methods">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    //</editor-fold>
}
