package org.devc_ray;

import jakarta.persistence.*;

@Entity
@Table (name = "Laptops")
public class Laptop {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int laptopId;
   private String brand;
   private int ram;
    @ManyToOne
    @JoinColumn(name = "student_id")
   private Student student;

   // getters and setters

    public int getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(int laptopId) {
        this.laptopId = laptopId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // toString

    @Override
    public String toString() {
        return "Laptop{" +
                "laptopId=" + laptopId +
                ", brand='" + brand + '\'' +
                ", ram=" + ram +
                '}'; // Removed students reference
    }
}
