package ru.gb.springbootdemoapp.dto;

import lombok.Data;
import ru.gb.springbootdemoapp.aspect.LoggingAspect;

@Data
public class ControllerTime {

    long product;
    long order;
    long cart;

    public ControllerTime() {
        this.product = LoggingAspect.productTime;
        this.order = LoggingAspect.orderTime;
        this.cart = LoggingAspect.cartTime;
    }
}
