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
}
