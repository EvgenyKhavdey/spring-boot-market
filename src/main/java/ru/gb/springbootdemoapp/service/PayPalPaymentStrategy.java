package ru.gb.springbootdemoapp.service;

import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.Payment;
import ru.gb.springbootdemoapp.repository.OrderRepository;

public final class PayPalPaymentStrategy implements PaymentStrategy {
    private final OrderRepository orderRepository;
    private final String email;
    private final String token;

    public PayPalPaymentStrategy(OrderRepository orderRepository, String email, String token) {
        this.orderRepository = orderRepository;
        this.email = email;
        this.token = token;
    }

    @Override
    public void pay(Order order) {
        order.setPayment(Payment.PAID);
        orderRepository.save(order);
    }
}
