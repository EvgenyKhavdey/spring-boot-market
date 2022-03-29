package ru.gb.springbootdemoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.model.AppUser;
import ru.gb.springbootdemoapp.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrderByCustomer(AppUser customer);

    List<Order> getOrderByManager(AppUser manager);

    @Query("FROM Order o WHERE o.manager = null")
    List<Order> getNewOrder();
}
