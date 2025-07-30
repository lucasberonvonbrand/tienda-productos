package com.example.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

    private Long product_id;
    private String code;
    private String name;
    private String brand;
    private double price;


}
