package hu.unideb.inf.domain;


import javax.persistence.*;

@Entity
@Table
public class Shop {
    @Id
    @GeneratedValue
    @Column
    protected int id;
    @Column
    protected String name;
    @Column
    protected double lat;
    @Column
    protected double lng;
    @Column
    protected String description;

    public Shop(String name, double lat, double lng, String description) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.description = description;
    }

    public Shop() {}

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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>
}
