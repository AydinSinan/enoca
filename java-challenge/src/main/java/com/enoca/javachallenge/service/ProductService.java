package com.enoca.javachallenge.service;

import com.enoca.javachallenge.entity.Product;
import com.enoca.javachallenge.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product newProductData) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(newProductData.getName());
            existingProduct.setPrice(newProductData.getPrice());
            existingProduct.setQuantity(newProductData.getQuantity());
            return productRepository.save(existingProduct);
        }
        return null;
    }}
