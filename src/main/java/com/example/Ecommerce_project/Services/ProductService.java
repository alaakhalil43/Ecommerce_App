package com.example.Ecommerce_project.Services;

import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.dto.ProductDto;
import com.example.Ecommerce_project.repos.ProductRepo;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    public ProductRepo productRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Value("${products.image.directory}")
    private String imageDirectory;


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




    public byte[] downloadProductPhoto(Integer id) throws IOException {
        Products products = productRepo.findById(id).get();
        File photoFile = new File(products.getImageData());

        byte[] photoData = Files.readAllBytes(photoFile.toPath());

        return  photoData;

    }

}
