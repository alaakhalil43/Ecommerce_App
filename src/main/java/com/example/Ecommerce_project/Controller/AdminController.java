package com.example.Ecommerce_project.Controller;
import com.example.Ecommerce_project.Models.Categories;
import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.Services.CategoryService;
import com.example.Ecommerce_project.Services.ProductService;
import com.example.Ecommerce_project.dto.CategoryDto;
import com.example.Ecommerce_project.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@org.springframework.stereotype.Controller
public class AdminController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;


    //http://localhost:8092/admin
    @GetMapping("/admin")
    public String showAdminPage() {
        return "AdminPage";
    }


    // categories
    //http://localhost:8092/manageCategories
    @GetMapping("/manageCategories")
    public String showCategories(ModelMap modelMap) {
        List<CategoryDto> cate = categoryService.getAllCategories();
        modelMap.addAttribute("categories", cate);
        return "categories/CategoriesPage";
    }

    @GetMapping("/addCategory")
    public String addCategory() {
        return "categories/addCategory";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("categories") Categories categories, ModelMap modelMap) {
        categoryService.saveCategories(categories);
        List<CategoryDto> cate = categoryService.getAllCategories();
        modelMap.addAttribute("categories", cate);
        return "categories/CategoriesPage";
    }

    @GetMapping("/updateCategorypage")
    public String updateCategory(@RequestParam("id") int id, ModelMap modelMap) {
        Categories categories = categoryService.getById(id);
        modelMap.addAttribute("categories", categories);
        return "categories/UpdateCategorypage";
    }

    @GetMapping("/updateCategory")
    public String updateCategory(@ModelAttribute("categories") Categories categories, ModelMap modelMap) {
        categoryService.saveCategories(categories);
        List<CategoryDto> cate = categoryService.getAllCategories();
        modelMap.addAttribute("categories", cate);
        return "categories/CategoriesPage";
    }


    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("id") int id, ModelMap modelMap) {
        categoryService.deleteCategory(id);
        List<CategoryDto> cate = categoryService.getAllCategories();
        modelMap.addAttribute("categories", cate);
        return "categories/CategoriesPage";

    }



    // products
//    @GetMapping("/mangeProducts")
//    public String mangeProducts(ModelMap modelMap) throws IOException {
//        List<ProductDto> productsList = productService.getAllProducts();
//        modelMap.addAttribute("productsList", productsList);
//        return "products/productspage";
//    }

    @GetMapping("/addProduct")
    public String addProduct(ModelMap modelMap) {
        List<CategoryDto> cate = categoryService.getAllCategories();
        modelMap.addAttribute("categories", cate);
        return "products/AddProducts";
    }

    @PostMapping("/saveProducts")
    public String saveProduct(@ModelAttribute("Products") Products products, ModelMap modelMap) {
        productService.saveProduct(products);
        List<ProductDto> productsList = productService.getAllProducts();
        modelMap.addAttribute("productsList", productsList);
        return "products/productspage";
    }

    @GetMapping("/updateProductPage")
    public String updateProductPage(@RequestParam("id") int id, ModelMap modelMap) {
        ProductDto product = productService.getOne(id);
        List<CategoryDto> cate = categoryService.getAllCategories();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("categories", cate);
        return "products/updateProductPage";
    }

    @GetMapping("/updateProducts")
    public String updateProducts(@ModelAttribute("products") Products products, ModelMap modelMap) {
        productService.saveProduct(products);
        List<ProductDto> productsList = productService.getAllProducts();
        modelMap.addAttribute("productsList", productsList);
        return "products/productspage";

    }


    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") int id, ModelMap modelMap) {
        productService.delete(id);
        List<ProductDto> productsList = productService.getAllProducts();
        modelMap.addAttribute("productsList", productsList);
        return "products/productspage";
    }


    //http://localhost:8092/showPhoto
@GetMapping(value = "/showPhoto")
public String showPhoto(ModelMap modelMap) throws IOException {
    byte[] image = productService.downloadProductPhoto(1);
    String base64Image = Base64.getEncoder().encodeToString(image);
    System.out.println(image);
    modelMap.addAttribute("image" , base64Image);
    return "Photos";
}

    //http://localhost:8092/test
    @GetMapping("/mangeProducts")
    public String test(ModelMap modelMap) throws IOException {
        List<ProductDto> productsList = productService.getAllProducts();
        modelMap.addAttribute("productsList", productsList);
        return "products/productspage";
    }
    @GetMapping("/product/image/{productId}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int productId) throws IOException {
        byte[] imageBytes = productService.downloadProductPhoto(productId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Set the image content type
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
