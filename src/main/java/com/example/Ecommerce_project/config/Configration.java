package com.example.Ecommerce_project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configration {

    @Bean
     public ModelMapper modelMapper(){
         return new ModelMapper();
     }

}
