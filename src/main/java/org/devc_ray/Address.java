package org.devc_ray;

import jakarta.persistence.Embeddable;

@Embeddable // embedding the address (columns ) to the student table - without creating a separate table
public class Address {
    private String street;
    private String city;

    // getters and setters

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // toString

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
