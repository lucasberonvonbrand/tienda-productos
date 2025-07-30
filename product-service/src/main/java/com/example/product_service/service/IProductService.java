package com.example.product_service.service;

import com.example.product_service.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getProducts();

    public Product saveProduct(Product product);

    public List<Product> saveAllProducts(List<Product> products);

    public void deleteProduct (Long product_id);

    public Product findProductById(Long product_id);

}
