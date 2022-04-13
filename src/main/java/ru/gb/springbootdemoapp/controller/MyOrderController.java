package ru.gb.springbootdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootdemoapp.converter.OrderMapper;
import ru.gb.springbootdemoapp.model.OrderView;
import ru.gb.springbootdemoapp.service.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/myOrder")
public class MyOrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public MyOrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public String getAllOrders(Principal principal, Model model) {
        List<OrderView> orders = orderService.findAll(principal).stream()
                .map(orderMapper::mapDto).collect(Collectors.toList());
        model.addAttribute("orders", orders);
        return "order-my";
    }
}
