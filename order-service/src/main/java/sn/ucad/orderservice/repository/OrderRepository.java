package sn.ucad.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.orderservice.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderNumberIgnoreCase(String orderNumber);
}