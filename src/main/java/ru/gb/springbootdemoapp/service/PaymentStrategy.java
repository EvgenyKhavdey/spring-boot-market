package ru.gb.springbootdemoapp.service;

import ru.gb.springbootdemoapp.model.Order;

public interface PaymentStrategy {
    void pay(Order order);
}
