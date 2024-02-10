package com.example.Ecommerce_project.repos;

import com.example.Ecommerce_project.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ProductRepo extends JpaRepository<Products,Integer> {


}
