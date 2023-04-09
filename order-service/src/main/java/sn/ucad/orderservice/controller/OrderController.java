package sn.ucad.orderservice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.orderservice.dto.OrderRequest;
import sn.ucad.orderservice.model.Order;
import sn.ucad.orderservice.service.OrderService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService  orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
  /*  @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "inventory")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")*/
    CompletableFuture<ResponseEntity<?>>  save(@RequestBody OrderRequest order){
        log.debug("REST request to save order : {}", order);
        Order created= orderService.save(order);
        return CompletableFuture.supplyAsync(()->ResponseEntity.ok().body(created));

    }

    public CompletableFuture<ResponseEntity<?>>  fallbackMethod(OrderRequest orderRequest, Exception e) {
        return  CompletableFuture.supplyAsync(()->new ResponseEntity<>("Ops,  Something  went wrong, save order   after some time", HttpStatus.TOO_MANY_REQUESTS)) ;
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
