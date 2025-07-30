package com.example.sale_service.service;

import com.example.sale_service.dto.CartDTO;
import com.example.sale_service.dto.ProductDTO;
import com.example.sale_service.dto.SaleDTO;
import com.example.sale_service.model.Sale;
import com.example.sale_service.repository.ICartAPI;
import com.example.sale_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private ICartAPI cartAPI;

    @Override
    public Sale createSale(Long cart_id) {
        CartDTO cartDTO = safeGetCartById(cart_id);

        Sale sale = new Sale();
        sale.setCart_id(cart_id);
        sale.setDate(LocalDate.now());
        sale.setTotal(cartDTO.getTotal());

        return saleRepository.save(sale);
    }

    @Override
    public Sale getSaleById(Long sale_id) {
        return saleRepository.findById(sale_id).orElse(null);
    }

    @Override
    public SaleDTO getSaleWithDetails(Long sale_id) {
        Sale sale = saleRepository.findById(sale_id).orElse(null);
        if (sale == null) return null;

        CartDTO cartDTO = safeGetCartById(sale.getCart_id());

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSale_id(sale.getSale_id());
        saleDTO.setCart_id(sale.getCart_id());
        saleDTO.setDate(sale.getDate());
        saleDTO.setTotal(sale.getTotal());
        saleDTO.setProducts(cartDTO.getProductDTOList());

        return saleDTO;
    }

    // Método protegido con Circuit Breaker y Retry
    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackCart")
    @Retry(name = "cart-service")
    public CartDTO safeGetCartById(Long cart_id) {
        return cartAPI.getCartById(cart_id);
    }

    // Fallback en caso de error
    public CartDTO fallbackCart(Long cart_id, Throwable t) {
        System.out.println("⚠️ Fallback activado para cart-service. Motivo: " + t.getClass().getSimpleName() + " - " + t.getMessage());

        CartDTO fallback = new CartDTO();
        fallback.setCart_id(cart_id);
        fallback.setTotal(0.0);
        fallback.setProductDTOList(Collections.singletonList(fallbackProduct()));
        return fallback;
    }

    private ProductDTO fallbackProduct() {
        ProductDTO product = new ProductDTO();
        product.setProduct_id(null);
        product.setCode("N/A");
        product.setName("Producto no disponible");
        product.setBrand("Desconocido");
        product.setPrice(0.0);
        return product;
    }
}
