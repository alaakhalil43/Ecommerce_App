package com.example.Ecommerce_project.Controller;



import com.example.Ecommerce_project.Models.Categories;
import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.Services.CategoryService;
import com.example.Ecommerce_project.Services.ProductService;
import com.example.Ecommerce_project.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


@GetMapping("/deleteCategory")
public String deleteCategory(@RequestParam("id") int id , ModelMap modelMap){
        Categories categories=categoryService.getById(id);
         categoryService.deleteCategory(categories);
         List<Categories>cate=categoryService.getAllCategories();
         modelMap.addAttribute("categories",cate);
         return "CategoriesPage";
}


@GetMapping("/mangeProducts")
public String mangeProducts(ModelMap modelMap,ModelMap modelMap2){
        List<ProductDto>productsList=productService.getAllProducts();
        modelMap.addAttribute("productsList",productsList);
        return "productspage";
}

    @GetMapping ("/addProduct")
    public String addProduct(){
        return "AddProducts";
    }



    @PostMapping("/saveProducts")
    public String saveProduct(@ModelAttribute("Products")Products products,ModelMap modelMap){
        productService.saveProduct(products);
        List<ProductDto>productsList=productService.getAllProducts();
        modelMap.addAttribute("productsList",productsList);
        return "productspage";
    }






}
