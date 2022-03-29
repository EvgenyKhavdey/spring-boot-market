package ru.gb.springbootdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.OrderStatus;
import ru.gb.springbootdemoapp.service.OrderService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/managerOrder")
public class ManagerOrderController {

    private final OrderService orderService;

    public ManagerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getOrderNew());
        return "manager/order-manager";
    }

    @GetMapping("/info")
    public String getOrdersById(Principal principal, Model model) {
        model.addAttribute("orders", orderService.getOrdersById(principal));
        return "manager/myOrder-manager";
    }

    @GetMapping("/{id}")
    public String saveManagerOrders(@PathVariable Long id, Principal principal, Model model) {
        orderService.saveManagerOrders(id, principal);
        model.addAttribute("orders", orderService.getOrderNew());
        return "manager/order-manager";
    }

    @GetMapping("/update/{id}")
    public String updateStatusOrders(@PathVariable Long id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("orders", order);
        model.addAttribute("status", order.getOrderStatus());
        return "manager/new_status_form";
    }

    @PostMapping("/update")
    public String updateStatusOrders(@RequestParam Long id, @Valid String status, Principal principal, Model model) {
        Order order = orderService.getById(id);
        order.setOrderStatus(OrderStatus.valueOf(status));
        orderService.save(order);
        model.addAttribute("orders", orderService.getOrdersById(principal));
        return "manager/myOrder-manager";
    }
}
