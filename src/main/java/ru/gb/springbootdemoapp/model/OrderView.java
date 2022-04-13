package ru.gb.springbootdemoapp.model;

import lombok.Data;

@Data
public class OrderView {

    private Long id;
    private Double price;
    private OrderStatus orderStatus;
}
