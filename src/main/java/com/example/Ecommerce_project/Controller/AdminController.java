package com.example.Ecommerce_project.Controller;



import com.example.Ecommerce_project.Models.Categories;
import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.Services.CategoryService;
import com.example.Ecommerce_project.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    public AdminController(ProductService productService) {
        this.productService = productService;
    }

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


//  @PostMapping("/saveCategories")
//  public String saveCategories(@ModelAttribute("Categories")Categories categories) {
//
//  }


    //http://localhost:8092/manageCategories
@GetMapping("/manageCategories")
public String showCategories(ModelMap modelMap){
        List<Categories>cate=categoryService.getAllCategories();
        modelMap.addAttribute("categories",cate);
        return "CategoriesPage";
  }


  @GetMapping ("/addCategory")
  public String addCategory(){
        return "addCategory";
  }


@PostMapping("/saveCategory")
public String saveCategory(@ModelAttribute("categories")Categories categories,ModelMap modelMap){
        categoryService.saveCategories(categories);
    List<Categories>cate=categoryService.getAllCategories();
    modelMap.addAttribute("categories",cate);
        return "CategoriesPage";
}




    @PostMapping("/saveProducts")
    public String saveProduct(@ModelAttribute("Products")Products products){
        productService.saveProduct(products);
        return "AddProducts";
    }








}
