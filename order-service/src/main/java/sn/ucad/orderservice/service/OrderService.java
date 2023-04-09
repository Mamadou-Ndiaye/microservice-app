package sn.ucad.orderservice.service;

import sn.ucad.orderservice.dto.OrderRequest;
import sn.ucad.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    /**
     * Save a order.
     *
     * @param orderRequest the entity to save.
     * @return the persisted entity.
     */
    Order save(OrderRequest orderRequest);


    /**
     * Updates a Order.
     *
     * @param Order the entity to update.
     * @return the persisted entity.
     */
    Order update(Order Order);

    /**
     * Get all Order.
     *
     * @return the list of entities.
     */
    List<Order> findAll();


    /**
     * Get the "id" order.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Order findById(Long id);

    /**
     * Get the "orderNumber" order.
     *
     * @param orderNumber the name field of the entity.
     * @return the entity.
     */
    List<Order> findByOrderNumber(String orderNumber);


    /**
     * Delete the "id" Order.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
