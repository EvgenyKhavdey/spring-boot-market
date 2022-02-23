package ru.gb.springbootdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.service.OrderService;

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
        model.addAttribute("orders", orderService.getById(id));
        return "manager/new_status_form";
    }

    @PostMapping("/update")
    public String updateStatusOrders(@PathVariable Order order, Model model) {
        orderService.save(order);
        return "manager/myOrder-manager";
    }
}
