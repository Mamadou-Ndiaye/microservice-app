package sn.ucad.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import sn.ucad.orderservice.dto.InventoryResponse;
import sn.ucad.orderservice.dto.OrderLineItemsDto;
import sn.ucad.orderservice.dto.OrderRequest;
import sn.ucad.orderservice.model.Order;
import sn.ucad.orderservice.model.OrderLineItems;
import sn.ucad.orderservice.repository.OrderLineItemsRepository;
import sn.ucad.orderservice.repository.OrderRepository;
import sn.ucad.orderservice.service.OrderService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    private  final OrderLineItemsRepository orderLineItemsRepository;
    @Override
    public Order save(OrderRequest orderRequest) {
        log.info("OrderServiceImpl:save  new order execution started ");
        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems>  orderLineItems = orderRequest.getOrderLineItemsList().stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItems(orderLineItems.stream().collect(Collectors.toSet()));


      List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());

      log.info(" ======  List skuCodes of Order to save ==== {} ", skuCodes);

        // Call Inventory Service, and place order if product is in stock
        InventoryResponse[] inventoryResponseArray = webClient
                .get()
                .uri(uriBuilder->uriBuilder.queryParam("skuCode", skuCodes).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        log.info(" ************* get  inventoryResponseArray  {}  *************", inventoryResponseArray);


        boolean allProductInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        log.info("============= allProductInStock  {} ===============", allProductInStock);

        // Normalement , on doit le ckecker .
      /*  if (inventoryResponseArray.length == 0) {
            allProductInStock = false;
        }*/

        Order   orderCreated =new Order() ;
        if(allProductInStock){
            orderCreated = orderRepository.save(order);
            log.info(" ************* Order saved  {}  *************", orderCreated);

           /* Order finalOrderCreated = orderCreated;
            orderLineItems.forEach(orderLineItems1 -> {
                orderLineItems1.setOrder(finalOrderCreated);
                orderLineItemsRepository.save(orderLineItems1);
            });*/

        }
        else {
            throw new IllegalArgumentException("Product is not in stock, please try against");
        }


        return  orderCreated;
    }

    private OrderLineItems  mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }



    @Override
    public Order update(Order Order) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        log.info("****** OrderServiceImpl:findAll execution started ******* ");
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        log.info("OrderServiceImpl:findById execution started ");
        if( id == null)
        {
            log.error("Order not found");
            throw  new RuntimeException(" ID  order is null ");
        }
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not existe in DB"));
        return  order ;
    }

    @Override
    public List<Order> findByOrderNumber(String orderNumer) {
        return orderRepository.findByOrderNumberIgnoreCase(orderNumer);
    }

    @Override
    public void delete(Long id) {
        log.info("OrderServiceImpl:deleteById execution started ");
        if( id == null)
        {
            log.error("Order not found");
            throw  new RuntimeException("  ID  order is null ");
        }
        orderRepository.deleteById(id);
        log.info("OrderServiceImpl:deleteById execution ended, the order deleting  ID = {} ", id);
    }
}
