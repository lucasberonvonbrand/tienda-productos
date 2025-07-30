package com.example.cart_service.repository;

import com.example.cart_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="product-service")
public interface IProductAPI {

    @GetMapping("products/{product_id}")
    ProductDTO getProductById(@PathVariable("product_id") Long product_id);

}
