package com.example.sale_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private Long sale_id;
    private Long cart_id;
    private LocalDate date;
    private double total;
    private List<ProductDTO> products;
}
