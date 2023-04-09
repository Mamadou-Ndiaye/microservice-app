package sn.ucad.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "t_orders")
@Entity
public class Order implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;

    @OneToMany(mappedBy = "order")
    private Set<OrderLineItems> orderLineItems = new HashSet<>();

}
