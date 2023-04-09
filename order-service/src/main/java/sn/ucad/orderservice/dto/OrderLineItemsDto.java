package sn.ucad.orderservice.dto;


import lombok.*;
import sn.ucad.orderservice.model.Order;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderLineItemsDto {

    private String skuCode;

    private BigDecimal price;
    private Integer quantity;

}
