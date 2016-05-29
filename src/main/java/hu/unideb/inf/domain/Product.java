package hu.unideb.inf.domain;

import javax.persistence.*;

@Entity
@Table
public class Product {
    @Id @GeneratedValue
    @Column
    protected int id;
    @Column
    protected String name;
    @Column
    protected String sku;
    @Column
    protected String barcode;

    /**
     * @param name Name of the product.
     * @param sku SKU of the product.
     * @param barcode Barcode of the product.
     */
    public Product(String name, String sku, String barcode) {
        this.name = name;
        this.sku = sku;
        this.barcode = barcode;
    }

    public Product() {}

    //<editor-fold desc="Setter & Getter methods">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    //</editor-fold>
}
