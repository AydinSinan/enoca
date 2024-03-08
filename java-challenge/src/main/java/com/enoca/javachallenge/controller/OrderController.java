package com.enoca.javachallenge.controller;

import com.enoca.javachallenge.entity.Order;
import com.enoca.javachallenge.entity.Product;
import com.enoca.javachallenge.request.OrderRequest;
import com.enoca.javachallenge.service.CustomerService;
import com.enoca.javachallenge.service.OrderService;
import com.enoca.javachallenge.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        // Veritabanından ürünü al
        Product product = productService.getProductById(orderRequest.getProductId());

        if (product == null || orderRequest.getQuantity() > product.getQuantity()) {
            return ResponseEntity.badRequest().body("Ürün bulunamadı veya stok yetersiz.");
        }

        // Sipariş oluştur ve veritabanına kaydet
        Order order = new Order();
        order.setCustomer(customerService.getCustomerById(orderRequest.getCustomerId())); // veya setCustomerId(customerId)

        order.setProduct(product);
        order.setQuantity(orderRequest.getQuantity());
        order.setTotalPrice(product.getPrice() * orderRequest.getQuantity());

        // Siparişi kaydet
        orderService.placeOrder(orderRequest);

        // Siparişin başarıyla oluşturulduğuna dair bir mesaj döndür
        return ResponseEntity.ok("Sipariş başarıyla oluşturuldu.");
    }

    // Sipariş koduna göre siparişi getirme
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderForCode(@PathVariable Long orderId) {
        Order order = orderService.getOrderForCode(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    // Bir müşterinin tüm siparişlerini getirme
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
}

