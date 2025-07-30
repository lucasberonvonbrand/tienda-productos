package com.example.cart_service.service;

import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.model.Cart;

import java.util.List;

public interface ICartService {

    CartDTO createCartWithProducts(List<Long> productIds);

    CartDTO getCartDTOById(Long cartId);

    void deleteCart(Long cartId);

}
