package com.enoca.javachallenge.dto;

import com.enoca.javachallenge.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private CustomerDTO customer;
    private List<ProductDTO> products;

    public CartDTO() {
        this.products = new ArrayList<>();
    }

    public void addProduct(ProductDTO product) {
        this.products.add(product);
    }
}
