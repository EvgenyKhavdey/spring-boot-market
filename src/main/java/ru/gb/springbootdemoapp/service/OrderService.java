package ru.gb.springbootdemoapp.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springbootdemoapp.dto.Cart;
import ru.gb.springbootdemoapp.model.*;
import ru.gb.springbootdemoapp.repository.OrderRepository;
import ru.gb.springbootdemoapp.repository.ProductRepository;
import ru.gb.springbootdemoapp.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.gb.springbootdemoapp.model.EmailType.MANAGER_ORDER_CREATED;
import static ru.gb.springbootdemoapp.model.EmailType.USER_ORDER_CREATED;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserRepository userRepository,
                        UserService userService,
                        ProductRepository productRepository,
                        EmailService emailService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    @Transactional
    public Order placeOrder(String address, String email, Principal principal) {
        Cart cart = cartService.getCartForCurrentUser();
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Корзина пуста");
        }

        AppUser appUser = principal != null ? userRepository.findByEmail(principal.getName()).orElse(null) : null;

        Order order = new Order();
        order.setCustomer(appUser);
        order.setPrice(cart.getPrice());
        order.setOrderStatus(OrderStatus.NEW);
        order.setShippingMethod(ShippingMethod.DELIVERY);
        order.setAddress(address);
        order.setContactEmail(email);
        order.setCreationTime(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getCount());
                    orderItem.setPrice(cartItem.getPrice());
                    orderItem.setProduct(productRepository.getById(cartItem.getProductId()));
                    return orderItem;
                }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        orderRepository.save(order);
        cartService.init();

        List<String> managerEmails = userService.getActiveManagers().stream().map(AppUser::getEmail).collect(Collectors.toList());

        emailService.sendMail(USER_ORDER_CREATED, Map.of("orderId", order.getId(), "price", order.getPrice()), List.of(email));
        emailService.sendMail(MANAGER_ORDER_CREATED, Map.of("orderId", order.getId()), managerEmails);

        return order;
    }

    public List<Order> findAll(Principal principal){
        AppUser appUser = principal != null ? userRepository.findByEmail(principal.getName()).orElse(null) : null;
        return orderRepository.getOrderByCustomer(appUser);
    }

    public List<Order> getOrderNew(){
        return orderRepository.getNewOrder();
    }

    public List<Order> getOrdersById(Principal principal){
        AppUser appUser = principal != null ? userRepository.findByEmail(principal.getName()).orElse(null) : null;
        return orderRepository.getOrderByManager(appUser);
    }

    public void saveManagerOrders(Long id, Principal principal){
        AppUser appUser = principal != null ? userRepository.findByEmail(principal.getName()).orElse(null) : null;
        Order order = orderRepository.getById(id);
        order.setManager(appUser);
        orderRepository.save(order);
    }

    public Order getById(Long id){
        return orderRepository.getById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }
}

