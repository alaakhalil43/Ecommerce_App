package com.example.Ecommerce_project.Controller;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class AdminController {

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




}
