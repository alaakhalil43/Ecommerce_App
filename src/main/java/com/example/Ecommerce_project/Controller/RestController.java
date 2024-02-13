package com.example.Ecommerce_project.Controller;


import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.Services.ProductService;
import com.example.Ecommerce_project.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    ProductService productService;

    public RestController(ProductService productService) {
        this.productService = productService;
    }


    //http://localhost:8092/saveProducts
    @PostMapping("/saveProducts")
    public void saveProduct(@ModelAttribute("Products") Products products , ModelMap modelMap)
    {
        String msg="Added success";
        modelMap.addAttribute("msg", msg);
        productService.saveProduct(products);
    }


    //http://localhost:8092/rest/getAll
    @GetMapping("/getAll")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

   // http://localhost:8092/rest/getone
   @GetMapping("/getone/{id}")                    // use ModelMapper
   public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
       ProductDto productDto = productService.getOne(id);
       if (productDto != null) {
           return ResponseEntity.ok(productDto);
       } else {
           return ResponseEntity.notFound().build();
       }
   }





}
