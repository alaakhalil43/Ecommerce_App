package com.example.Ecommerce_project.dto;


import com.example.Ecommerce_project.Models.Categories;
import com.example.Ecommerce_project.Models.Products;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private int price;
    private String productDescription;
    private String imageData;
    private Categories category;
    public static ProductDto toDto(Products products){
        return ProductDto.builder()
                .id(products.getId())
                .name(products.getName())
                .price(products.getPrice())
                .productDescription(products.getProductDescription())
                .imageData(products.getImageData())
                .category(products.getCategory())
                .build();
    }

}
