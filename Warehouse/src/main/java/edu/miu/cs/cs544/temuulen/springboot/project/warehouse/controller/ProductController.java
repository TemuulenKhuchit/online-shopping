package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.controller;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Category;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Product;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Stock;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/low-stock")
    public ResponseEntity<List<Product>> getLowStockItems(@RequestParam int qty) {
        List<Product> products = productService.getLowStockProducts(qty);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam("category") String category) {
        Category enumCategory = Category.valueOf(category.toUpperCase());
        List<Product> products = productService.getProductsByCategory(enumCategory);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam int minPrice,
            @RequestParam int maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

}
