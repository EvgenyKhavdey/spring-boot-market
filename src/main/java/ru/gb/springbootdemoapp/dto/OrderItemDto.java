package ru.gb.springbootdemoapp.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long order;
    private Long productId;
    private Double price;
}
