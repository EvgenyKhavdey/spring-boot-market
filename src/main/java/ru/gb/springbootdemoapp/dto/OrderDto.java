package ru.gb.springbootdemoapp.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Float price;
    private String address;
    private String contactEmail;
    private Long orderItems;
}
