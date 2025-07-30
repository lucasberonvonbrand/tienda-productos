package com.example.sale_service.repository;

import com.example.sale_service.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface ICartAPI {

    @GetMapping("/carts/{cart_id}")
    CartDTO getCartById(@PathVariable("cart_id") Long cart_id);
}
