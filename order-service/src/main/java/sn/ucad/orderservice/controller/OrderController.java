package sn.ucad.orderservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.ucad.orderservice.dto.OrderRequest;
import sn.ucad.orderservice.model.Order;
import sn.ucad.orderservice.service.OrderService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService  orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Order save(@RequestBody OrderRequest order){
        log.debug("REST request to save order : {}", order);
        return orderService.save(order);
    }


    @GetMapping("/fetchAll")
    @ResponseStatus(HttpStatus.OK)
    List<Order> fetchAll(){
        log.debug("REST request to get all order");
        return orderService.findAll();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable("id") Long id){
        log.debug("REST request to deleting the {} order", id);
        orderService.delete(id);

    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Order findById(@PathVariable("id") Long id){
        log.debug("REST request to find a  order with id: {}", id);
        return orderService.findById(id);
    }



    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Order update(Order order){
        return  null;
    }


    @GetMapping("/byName/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    List<Order> findByOrderNumber(@PathVariable("orderNumber")  String orderNumber){
        log.debug("REST request to find a  order with orderNumber: {}", orderNumber);
        return orderService.findByOrderNumber(orderNumber);
    }
}
