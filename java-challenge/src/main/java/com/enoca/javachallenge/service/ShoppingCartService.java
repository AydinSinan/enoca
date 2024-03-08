package com.enoca.javachallenge.service;

public interface  ShoppingCartService {
    void addProductToCart(Long customerId, Long productId, int quantity);
    void removeProductFromCart(Long customerId, Long productId, int quantity);
}
