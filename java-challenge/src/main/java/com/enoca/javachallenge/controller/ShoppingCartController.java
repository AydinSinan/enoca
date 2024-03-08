package com.enoca.javachallenge.controller;

import com.enoca.javachallenge.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PutMapping("/add/{customerId}/{productId}/{quantity}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long customerId, @PathVariable Long productId, @PathVariable int quantity) {
        shoppingCartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok("Ürün sepete eklendi.");
    }

    @PutMapping("/remove/{customerId}/{productId}/{quantity}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long customerId, @PathVariable Long productId, @PathVariable int quantity) {
        shoppingCartService.removeProductFromCart(customerId, productId, quantity);
        return ResponseEntity.ok("Ürün sepetten kaldırıldı.");
    }
}
