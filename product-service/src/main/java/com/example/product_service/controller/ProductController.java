package com.example.product_service.controller;

import com.example.product_service.model.Product;
import com.example.product_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PostMapping("/batch")
    public List<Product> saveAll(@RequestBody List<Product> products) {
        return productService.saveAllProducts(products);
    }

    @DeleteMapping("/{product_id}")
    public void deleteProduct(@PathVariable Long product_id){
        productService.deleteProduct(product_id);
    }

    @GetMapping("/{product_id}")
    public Product getProductById(@PathVariable Long product_id){
        return productService.findProductById(product_id);
    }





}
