package com.someshvemula.orderservice.repository;

import com.someshvemula.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByRestaurantId(long restaurantId);

    List<Order> findByUserId(long userId);


}
