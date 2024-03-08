package com.enoca.javachallenge.controller;

import com.enoca.javachallenge.dto.CartDTO;
import com.enoca.javachallenge.entity.Cart;
import com.enoca.javachallenge.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {


    private final CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestParam Long customerId) {
        Cart cart = cartService.createCart(customerId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    // Bir kullanıcının sepetini getirme
    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTO> getCartByCustomerId(@PathVariable Long customerId) {
        CartDTO cartDTO = cartService.getCartByCustomerId(customerId);
        if (cartDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDTO);
    }

    // Bir kullanıcının sepetini güncelleme
    @PutMapping("/{customerId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long customerId, @RequestBody Cart updatedCart) {
        Cart cart = cartService.updateCart(customerId, updatedCart);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // Bir kullanıcının sepetini boşaltma
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
        return ResponseEntity.ok().build();
    }
}
