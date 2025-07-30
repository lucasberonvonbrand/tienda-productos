package com.example.cart_service.service;

import com.example.cart_service.dto.CartDTO;
import com.example.cart_service.dto.ProductDTO;
import com.example.cart_service.model.Cart;
import com.example.cart_service.repository.ICartRepository;
import com.example.cart_service.repository.IProductAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductAPI productAPI;

    @Override
    public CartDTO createCartWithProducts(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            throw new IllegalArgumentException("ProductIds list cannot be null or empty");
        }

        List<ProductDTO> productDTOList = new ArrayList<>();
        double total = 0.0;

        for (Long product_id : productIds) {
            // Llamada protegida con Circuit Breaker y Retry en método separado
            ProductDTO product = safeGetProductById(product_id);
            productDTOList.add(product);
            total += product.getPrice();
        }

        Cart cart = new Cart();
        cart.setProductIds(productIds);
        cart.setTotal(total);

        Cart savedCart = cartRepository.save(cart);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_id(savedCart.getCart_id());
        cartDTO.setTotal(total);
        cartDTO.setProductDTOList(productDTOList);

        return cartDTO;
    }

    @Override
    public CartDTO getCartDTOById(Long cart_id) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Long product_id : cart.getProductIds()) {
            // Llamada protegida con Circuit Breaker y Retry en método separado
            ProductDTO product = safeGetProductById(product_id);
            productDTOList.add(product);
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_id(cart.getCart_id());
        cartDTO.setTotal(cart.getTotal());
        cartDTO.setProductDTOList(productDTOList);

        return cartDTO;
    }

    @Override
    public void deleteCart(Long cart_id) {
        if (!cartRepository.existsById(cart_id)) {
            throw new RuntimeException("El carrito con ID " + cart_id + " no existe.");
        }
        cartRepository.deleteById(cart_id);
    }

    // Método que protege la llamada Feign con CircuitBreaker y Retry
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackProduct")
    //@Retry(name = "product-service")
    public ProductDTO safeGetProductById(Long product_id) {
        return productAPI.getProductById(product_id);
    }

    // Método fallback para cuando product-service falla
    // Debe recibir los mismos parámetros que safeGetProductById + Throwable al final
    public ProductDTO fallbackProduct(Long product_id, Throwable t) {
        System.out.println("⚠️ Fallback activado. Motivo: " + t.getClass().getSimpleName() + " - " + t.getMessage());
        ProductDTO fallback = new ProductDTO();
        fallback.setProduct_id(null);
        fallback.setCode("N/A");
        fallback.setName("Producto no disponible");
        fallback.setBrand("Desconocido");
        fallback.setPrice(0.0);
        return fallback;
    }
}
