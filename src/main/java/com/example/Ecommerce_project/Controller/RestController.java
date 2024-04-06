package com.example.Ecommerce_project.Controller;
import com.example.Ecommerce_project.Models.Categories;
import com.example.Ecommerce_project.Models.Products;
import com.example.Ecommerce_project.Services.CategoryService;
import com.example.Ecommerce_project.Services.ProductService;
import com.example.Ecommerce_project.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;import java.util.List;
import java.util.Optional;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    public RestController() {
        // No need to do anything here
    }

    public RestController(ProductService productService) {
        this.productService = productService;
    }

    public RestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //http://localhost:8092/rest/
    @GetMapping("/")
    public String test(){
        return"this is restfull";
    }


    //http://localhost:8092/rest/saveProducts
    @PostMapping("/saveProducts")
    public void saveProduct(@ModelAttribute("Products") Products products , ModelMap modelMap)
    {
        String msg="Added success";
        modelMap.addAttribute("msg", msg);
        productService.saveProduct(products);
    }






    //http://localhost:8092/rest/getAll
//    @GetMapping("/getAll")
//    public List<ProductDto> getAllProducts(){
//        return productService.getAllProducts();
//    }


  //  http://localhost:8092/rest/getallp   // not found because FK
//    @GetMapping("/getallp")
//    public List<Products> getAllp(){
//        return productService.getAll();
//    }







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


    // http://localhost:8092/rest/delete/2
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.delete(id);
    }




//    http://localhost:8092/rest/getallc
    @GetMapping("/getallc")
    public List<Categories> getAll(){
        return categoryService.getAllc();
    }










}
