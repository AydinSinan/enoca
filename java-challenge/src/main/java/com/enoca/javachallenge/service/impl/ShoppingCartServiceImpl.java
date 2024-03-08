package com.enoca.javachallenge.service.impl;

import com.enoca.javachallenge.entity.Cart;
import com.enoca.javachallenge.entity.Customer;
import com.enoca.javachallenge.entity.Product;
import com.enoca.javachallenge.exception.CartNotFoundException;
import com.enoca.javachallenge.exception.CustomerNotFoundException;
import com.enoca.javachallenge.exception.ProductNotFoundException;
import com.enoca.javachallenge.repository.CartRepository;
import com.enoca.javachallenge.repository.CustomerRepository;
import com.enoca.javachallenge.repository.ProductRepository;
import com.enoca.javachallenge.service.ShoppingCartService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProductToCart(Long customerId, Long productId, int quantity) {
        // Müşteri ve ürün var mı kontrol et
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Müşteri bulunamadı."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Ürün bulunamadı."));

        // Sepeti al
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartNotFoundException("Sepet bulunamadı."));

        // Ürünü sepete ekle
        cart.addProduct(product, quantity);
        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Long customerId, Long productId, int quantity) {
        // Müşteri ve ürün var mı kontrol et
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Müşteri bulunamadı."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Ürün bulunamadı."));

        // Sepeti al
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartNotFoundException("Sepet bulunamadı."));

        // Ürünü sepetten kaldır
        cart.removeProduct(product, quantity);
        cartRepository.save(cart);
    }
}