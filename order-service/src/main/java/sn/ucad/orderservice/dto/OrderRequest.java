package sn.ucad.orderservice.dto;

import lombok.*;
import sn.ucad.orderservice.model.OrderLineItems;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsList;
}
