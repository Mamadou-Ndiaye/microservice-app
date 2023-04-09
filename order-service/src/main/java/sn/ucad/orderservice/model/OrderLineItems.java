package sn.ucad.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "order_line_items")
@Entity
public class OrderLineItems  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;

    private BigDecimal price;
    private Integer quantity;
    @ManyToOne()
    private Order order;
}
