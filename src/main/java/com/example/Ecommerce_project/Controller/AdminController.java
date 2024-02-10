package com.example.Ecommerce_project.Controller;


import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class AdminController {

    @Autowired
    ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }


    // new repo success
    //http://localhost:8092/admin
    @GetMapping("/admin")
    public String showAdminPage(){
        return "AdminPage";
    }


    //http://localhost:8092/addproduct
    @GetMapping("/addproduct")
    public String addProducts(){
        return "AddProducts";
    }



    @GetMapping("/manageCategories")
    public String manageCategories(){
        return "ManageCategories";
    }


    @PostMapping("/saveProducts")
    public String saveProduct(@ModelAttribute("Products")Products products, ModelMap modelMap){
        productService.saveProduct(products);
        return "AddProducts";
    }






}
