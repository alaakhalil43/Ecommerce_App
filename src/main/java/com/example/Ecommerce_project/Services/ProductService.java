package com.example.Ecommerce_project.Services;

import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.dto.ProductDto;
import com.example.Ecommerce_project.repos.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    public ProductRepo productRepo;

    @Autowired
    public ModelMapper modelMapper;
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void saveProduct(Products products){productRepo.save(products);}

    public List<ProductDto> getAllProducts(){
        List<Products> products = productRepo.findAll();
        List<ProductDto> productDTOs = new ArrayList<>();

        for (Products i : products) {
            ProductDto prod = convertToDto(i);
            productDTOs.add(prod);
        }

        return productDTOs;
    }

    private ProductDto convertToDto(Products products) {
        return modelMapper.map(products, ProductDto.class);
    }


   public ProductDto getOne(int id){
        Products products=productRepo.findById(id).get();
        ProductDto productDto=convertToDto(products);
        return productDto;
   }

}
