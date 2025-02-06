package com.example.ABD.Application.Model;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Entity
@Component
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(generator = "yoo",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "fdf",sequenceName = "yoo",initialValue = 1,allocationSize = 1)
    private int id;

    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    public Person() {
    }

    public Person(int id, String name, String place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }

    private String place;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
