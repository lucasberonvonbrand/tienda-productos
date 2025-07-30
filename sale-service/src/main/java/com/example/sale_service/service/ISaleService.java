package com.example.sale_service.service;

import com.example.sale_service.dto.SaleDTO;
import com.example.sale_service.model.Sale;

public interface ISaleService {

    Sale createSale(Long cart_id);
    Sale getSaleById(Long sale_id);
    SaleDTO getSaleWithDetails(Long sale_id);
}
