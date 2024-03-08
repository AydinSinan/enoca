package com.enoca.javachallenge.service;

import com.enoca.javachallenge.entity.Order;
import com.enoca.javachallenge.entity.Product;
import com.enoca.javachallenge.exception.InsufficientStockException;
import com.enoca.javachallenge.exception.ProductNotFoundException;
import com.enoca.javachallenge.repository.OrderRepository;
import com.enoca.javachallenge.request.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public Order placeOrder(OrderRequest orderRequest) {
        // Siparişi oluştur
        Order order = new Order();

        // Ürünü al
        Product product = productService.getProductById(orderRequest.getProductId());

        // Ürün kontrolü
        if (product != null) {
            int currentStock = product.getQuantity();
            int orderedQuantity = orderRequest.getQuantity();
            if (currentStock >= orderedQuantity) {
                // Stoktan düşüm yap
                product.setQuantity(currentStock - orderedQuantity);
                productService.updateProduct(product.getId(), product);
            } else {
                throw new InsufficientStockException("Stok miktarı yetersiz.");
            }
        } else {
            throw new ProductNotFoundException("Ürün bulunamadı.");
        }

        // Siparişi kaydet
        order.setCustomer(customerService.getCustomerById(orderRequest.getCustomerId()));
        order.setProduct(product);
        order.setQuantity(orderRequest.getQuantity());
        order.setTotalPrice(product.getPrice() * orderRequest.getQuantity());
        return orderRepository.save(order);
    }

    // Sipariş koduna göre siparişi getirme işlemi
    public Order getOrderForCode(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // Bir müşterinin tüm siparişlerini getirme işlemi
    public List<Order> getAllOrdersForCustomer(Long customerId) {
        // Örneğin:
        // return orderRepository.findByCustomerId(customerId);
        return null; // Burada örnek olarak null döndürüldü, gerçek implementasyon yapılmalı
    }
}

