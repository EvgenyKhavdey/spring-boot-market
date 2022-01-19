package ru.gb.springbootdemoapp.service;

import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.dto.Cart;
import ru.gb.springbootdemoapp.dto.CartItem;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.OrderItem;
import ru.gb.springbootdemoapp.model.Product;
import ru.gb.springbootdemoapp.repository.OrderItemRepository;
import ru.gb.springbootdemoapp.repository.OrderRepository;
import ru.gb.springbootdemoapp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository, OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public void createOrder(Cart cart){
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        List<CartItem> items = cart.getItems();
        List<Product> products = new ArrayList<>();
        for (CartItem cI : items) {
           products.add(productRepository.findById(cI.getProductId()).get());
        }
        orderItem.setProductId(products);
        orderItem.setPrice(cart.getPrice());
        order.addOrderItem(orderItem);
        orderRepository.save(order);
        orderItemRepository.save(orderItem);
    }
}
