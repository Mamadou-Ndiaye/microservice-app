package sn.ucad.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.orderservice.model.OrderLineItems;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long> {
}