package org.devc_ray;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "grade")
    private int grade;
    private Address Address;
//    @OneToOne // one student will have one laptop
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) // one student will have multiple laptops so we use List datatype instead
    private List<Laptop> Laptops;

   // setter and getters

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }

    public List<Laptop> getLaptops() {
        return Laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        Laptops = laptops;
    }

    // toString

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", Address=" + Address +
                ", Laptops=" + Laptops +
                '}';
    }
}