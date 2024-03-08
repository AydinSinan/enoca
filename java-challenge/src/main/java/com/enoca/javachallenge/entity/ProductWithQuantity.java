package com.enoca.javachallenge.entity;

import com.enoca.javachallenge.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithQuantity {
    private ProductDTO product;
    private int quantity; // Sepetteki ürün miktarı
}
