package ru.gb.springbootdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.service.OrderService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/myOrder")
public class MyOrderController {

    private final OrderService orderService;

    public MyOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Principal principal, Model model) {
        List<Order> orders = orderService.findAll(principal);
        model.addAttribute("orders", orders);
        return "order-my";
    }
}
