package hu.unideb.inf.domain;

import javax.persistence.*;

@Table
@Entity
public class Setting {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String value;

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Setting() {}

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
