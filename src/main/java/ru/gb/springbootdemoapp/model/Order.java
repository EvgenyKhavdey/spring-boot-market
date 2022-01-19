package ru.gb.springbootdemoapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Float price;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "shipping_method")
    private ShippingMethod shippingMethod;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "deliver_time")
    private LocalDateTime deliverTime;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public void addOrderItem(OrderItem orderItem){
        if(orderItems == null){
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
