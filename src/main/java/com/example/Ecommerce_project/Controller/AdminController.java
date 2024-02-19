package com.example.Ecommerce_project.Controller;



import com.example.Ecommerce_project.Models.Categories;
import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.Services.CategoryService;
import com.example.Ecommerce_project.Services.ProductService;
import com.example.Ecommerce_project.dto.CategoryDto;
import com.example.Ecommerce_project.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;
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
        List<CategoryDto> cate=categoryService.getAllCategories();
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
    List<CategoryDto>cate=categoryService.getAllCategories();
    modelMap.addAttribute("categories",cate);
        return "CategoriesPage";
}


//@GetMapping("/deleteCategory")
//public String deleteCategory(@RequestParam("id") int id , ModelMap modelMap){
//        Categories categories=categoryService.getById(id);
//         categoryService.deleteCategory(categories);
//         List<CategoryDto>cate=categoryService.getAllCategories();
//         modelMap.addAttribute("categories",cate);
//         return "CategoriesPage";
//}




    @GetMapping ("/addProduct")
    public String addProduct(ModelMap modelMap){
        List<CategoryDto>cate=categoryService.getAllCategories();
        modelMap.addAttribute("categories",cate);
        return "AddProducts";
    }



    @PostMapping("/saveProducts")
    public String saveProduct(@ModelAttribute("Products")Products products,ModelMap modelMap){
        productService.saveProduct(products);
        List<ProductDto>productsList=productService.getAllProducts();
        modelMap.addAttribute("productsList",productsList);
        return "productspage";
    }

    @GetMapping("/mangeProducts")
    public String mangeProducts(ModelMap modelMap,ModelMap modelMap2) throws IOException {
        List<ProductDto> productsList = productService.getAllProducts();
        modelMap.addAttribute("productsList", productsList);
//
//        byte[] image = productService.downloadProductPhoto(6);
//        String base64Image = Base64.getEncoder().encodeToString(image);
//        System.out.println("Base64 Image: " + base64Image); // Log the Base64-encoded image data
//        modelMap.addAttribute("productImage", base64Image); // Adding image data with a unique key based on product ID
        return "productspage";
    }


//    //http://localhost:8092/showPhoto
//    @GetMapping(value = "/showPhoto")
//    public String showPhoto(ModelMap modelMap) throws IOException {
//
//
//        return "Photos";
//    }
//@GetMapping(value = "/showPhoto")
//public String showPhoto(ModelMap modelMap) throws IOException {
//    byte[] image = productService.downloadProductPhoto(6);
//    String base64Image = Base64.getEncoder().encodeToString(image);
//    modelMap.addAttribute("image" , base64Image);
//    return "Photos";
//}




    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") int id , ModelMap modelMap){
        productService.delete(id);
        List<ProductDto>productsList=productService.getAllProducts();
        modelMap.addAttribute("productsList",productsList);
        return "productspage";
    }




    @GetMapping("/deleteCategory")
    public String deleteCategory (@RequestParam("id") int id , ModelMap modelMap){
        categoryService.deleteCategory(id);
        List<CategoryDto>cate=categoryService.getAllCategories();
        modelMap.addAttribute("categories",cate);
        return "CategoriesPage";

    }

    @GetMapping("/updateCategorypage")
    public String updateCategory (@RequestParam("id") int id , ModelMap modelMap){
        Categories categories=categoryService.getById(id);
        modelMap.addAttribute("categories",categories);
        return "UpdateCategorypage";
    }

    @GetMapping("/updateCategory")
    public String update(@ModelAttribute("categories") Categories categories,ModelMap modelMap){
       categoryService.saveCategories(categories);
        List<CategoryDto>cate=categoryService.getAllCategories();
        modelMap.addAttribute("categories",cate);
        return "CategoriesPage";
    }


    @GetMapping("/updateProductPage")
    public String updateProductPage (@RequestParam("id") int id , ModelMap modelMap){
    ProductDto product=productService.getOne(id);
    modelMap.addAttribute("product",product);
        return "updateProductPage";
    }





}
