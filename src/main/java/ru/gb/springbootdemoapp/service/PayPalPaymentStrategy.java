package ru.gb.springbootdemoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.Payment;
import ru.gb.springbootdemoapp.repository.OrderRepository;

public final class PayPalPaymentStrategy implements PaymentStrategy {
    @Autowired
    private OrderRepository orderRepository;
    private final String email;
    private final String token;

    public PayPalPaymentStrategy( String email, String token) {
        this.email = email;
        this.token = token;
    }

    @Override
    public void pay(Order order) {
        order.setPayment(Payment.PAID);
        orderRepository.save(order);
    }
}
