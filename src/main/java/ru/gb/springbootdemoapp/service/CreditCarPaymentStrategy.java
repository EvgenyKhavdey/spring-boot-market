package ru.gb.springbootdemoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.Payment;
import ru.gb.springbootdemoapp.repository.OrderRepository;

public final class CreditCarPaymentStrategy implements PaymentStrategy{
    @Autowired
    private OrderRepository orderRepository;
    private final String card;

    public CreditCarPaymentStrategy( String card) {
        this.card = card;
    }

    @Override
    public void pay(Order order) {
       order.setPayment(Payment.PAID);
       orderRepository.save(order);
    }
}
