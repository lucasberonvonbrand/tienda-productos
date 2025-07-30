package com.example.product_service.service;

import com.example.product_service.model.Product;
import com.example.product_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public void deleteProduct(Long product_id) {
        productRepository.deleteById(product_id);
    }

    @Override
    public Product findProductById(Long product_id) {
        return productRepository.findById(product_id).orElse(null);
    }
}
