package com.enoca.javachallenge.service;

import com.enoca.javachallenge.dto.CartDTO;
import com.enoca.javachallenge.dto.CustomerDTO;
import com.enoca.javachallenge.dto.ProductDTO;
import com.enoca.javachallenge.entity.*;
import com.enoca.javachallenge.exception.CartNotFoundException;
import com.enoca.javachallenge.repository.CartRepository;
import com.enoca.javachallenge.repository.CustomerRepository;
import com.enoca.javachallenge.request.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@AllArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    // Bir kullanıcının sepetini getirme
    public CartDTO getCartByCustomerId(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartNotFoundException("Sepet bulunamadı."));

        Customer customer = cart.getCustomer();
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail());

        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setCustomer(customerDTO);

        if (cart.getProducts() != null) {
            for (Product product : cart.getProducts()) {
                if (product != null) {
                    ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
                    cartDTO.addProduct(productDTO);
                }
            }
        }

        return cartDTO;
    }



    // Bir kullanıcıya ait yeni bir sepet oluşturma
    public Cart createCart(Long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);

        Cart cart = new Cart();
        cart.setCustomer(customer);

        return cartRepository.save(cart);
    }

    // Bir kullanıcının sepetini güncelleme
    public Cart updateCart(Long customerId, Cart updatedCart) {
        return cartRepository.findByCustomerId(customerId)
                .map(existingCart -> {
                    existingCart.setProducts(updatedCart.getProducts());
                    return cartRepository.save(existingCart);
                })
                .orElseThrow(() -> new CartNotFoundException("Sepet bulunamadı."));
    }

    // Bir kullanıcının sepetini boşaltma
    public void emptyCart(Long customerId) {
        cartRepository.findByCustomerId(customerId)
                .ifPresent(cart -> {
                    cart.setProducts(new ArrayList<>());
                    cartRepository.save(cart);
                });
    }
}
