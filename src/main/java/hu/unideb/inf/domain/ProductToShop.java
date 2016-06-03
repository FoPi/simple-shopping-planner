package hu.unideb.inf.domain;

import javax.persistence.*;

@Entity
@Table
public class ProductToShop {
    @Id @GeneratedValue
    @Column
    protected int id;

    @OneToOne
    @PrimaryKeyJoinColumn
    protected Product product;

    @OneToOne
    @PrimaryKeyJoinColumn
    protected Shop shop;

    @Column
    protected double price;

    public ProductToShop(Product product, Shop shop, double price) {
        this.product = product;
        this.shop = shop;
        this.price = price;
    }

    public ProductToShop() {}

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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
