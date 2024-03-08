package com.enoca.javachallenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        // Sepetteki ürünleri kontrol et
        boolean found = false;
        for (Product p : products) {
            if (Long.valueOf(p.getId()).equals(Long.valueOf(product.getId()))) {
                found = true;
                break;
            }
        }

        // Eğer ürün sepette değilse, ekleyin
        if (!found) {
            products.add(product);
        }
    }

    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        if (this.products != null) {
            for (Product product : this.products) {
                CartItem cartItem = new CartItem(product, 1); // Burada 1 varsayılan olarak eklenen miktar
                cartItems.add(cartItem);
            }
        }
        return cartItems;
    }

    public void removeProduct(Product product, int quantity) {
        // Sepetteki ürünleri kontrol et
        for (Product p : products) {
            if (Long.valueOf(p.getId()).equals(Long.valueOf(product.getId()))) {
                products.remove(p);
                break;
            }
        }
    }

}
