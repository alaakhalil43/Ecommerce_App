package com.example.Ecommerce_project.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "price")
    private int price;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "imageData")
    private String imageData;


    @Column(name = "categories")
    private String categories;
}
