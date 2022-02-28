package ru.gb.springbootdemoapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private AppUser customer;

    @ManyToOne
    @JoinColumn(name = "manager")
    private AppUser manager;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "details")
    private String details;

    @Column(name = "address")
    private String address;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private OrderStatus orderStatus;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private ShippingMethod shippingMethod;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "deliver_time")
    private LocalDateTime deliverTime;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
