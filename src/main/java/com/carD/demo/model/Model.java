package com.carD.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table (name="models")
public class Model {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Float miles; // length

    @Column
    private Float price; // releaseDate;

    @ManyToOne
    @JoinColumn(name="make_id")
    @JsonIgnore
    private Make make;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Model() {
    }

    public Model(Long id, String name, Float miles, Float price) {
        this.id = id;
        this.name = name;
        this.miles = miles;
        this.price = price;
    }

    // the following are the getters and setters for the variables in the songs class
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMiles() {
        return miles;
    }

    public void setMiles(Float miles) {
        this.miles = miles;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "model {" +
                "id =" + id +
                ", name ='" + name + '\'' +
                ", miles =" + miles +
                ", price ='" + price + '\'' +
                '}';
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
