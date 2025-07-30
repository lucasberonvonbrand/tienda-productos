package com.example.cart_service.controller;

import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.dto.CartRequest;
import com.example.cart_service.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ICartService cartService;

    /*
    @PostMapping("/add/{product_id}")
    public ResponseEntity<CartDTO> createAndAdd(@PathVariable Long product_id) {
        CartDTO cartDTO = cartService.createCartWithProduct(product_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDTO);
    }*/

    @PostMapping("/create")
    public ResponseEntity<CartDTO> createCartWithProducts(@RequestBody CartRequest cartRequest) {
        CartDTO cartDTO = cartService.createCartWithProducts(cartRequest.getProductIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDTO);
    }


    @GetMapping("/{cart_id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long cart_id) {
        CartDTO cartDTO = cartService.getCartDTOById(cart_id);
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/{cart_id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cart_id) {
        cartService.deleteCart(cart_id);
        return ResponseEntity.ok("Carrito eliminado con éxito.");
    }


}
