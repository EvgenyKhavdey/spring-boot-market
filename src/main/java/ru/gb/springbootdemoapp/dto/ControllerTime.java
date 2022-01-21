package ru.gb.springbootdemoapp.dto;

import lombok.Data;

@Data
public class ControllerTime {
    public static long productTime;
    public static long orderTime;
    public static long cartTime;

    long product;
    long order;
    long cart;

    public ControllerTime() {
        this.product = productTime;
        this.order = orderTime;
        this.cart = cartTime;
    }
}
