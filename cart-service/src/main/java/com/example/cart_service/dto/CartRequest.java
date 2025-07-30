package com.example.cart_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartRequest {

    private List<Long> productIds;
}
