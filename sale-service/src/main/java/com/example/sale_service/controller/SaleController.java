package com.example.sale_service.controller;

import com.example.sale_service.dto.SaleDTO;
import com.example.sale_service.model.Sale;
import com.example.sale_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    // Crear una nueva venta a partir de un cart_id
    @PostMapping("/create/{cart_id}")
    public ResponseEntity<Sale> createSale(@PathVariable Long cart_id) {
        Sale sale = saleService.createSale(cart_id);
        return ResponseEntity.ok(sale);
    }

    // Obtener una venta por ID
    @GetMapping("/{sale_id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long sale_id) {
        Sale sale = saleService.getSaleById(sale_id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/details/{sale_id}")
    public ResponseEntity<SaleDTO> getSaleDetails(@PathVariable Long sale_id) {
        SaleDTO saleDTO = saleService.getSaleWithDetails(sale_id);
        if (saleDTO != null) {
            return ResponseEntity.ok(saleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
