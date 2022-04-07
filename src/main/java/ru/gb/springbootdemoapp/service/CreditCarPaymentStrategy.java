package ru.gb.springbootdemoapp.service;

import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.Payment;
import ru.gb.springbootdemoapp.repository.OrderRepository;

public final class CreditCarPaymentStrategy implements PaymentStrategy{
    private final OrderRepository orderRepository;
    private final String card;

    public CreditCarPaymentStrategy(OrderRepository orderRepository, String card) {
        this.orderRepository = orderRepository;
        this.card = card;
    }

    @Override
    public void pay(Order order) {
       order.setPayment(Payment.PAID);
       orderRepository.save(order);
    }
}
