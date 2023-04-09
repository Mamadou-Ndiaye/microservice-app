package sn.ucad.productservice.dto;

import lombok.*;
import sn.ucad.productservice.model.Product;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {
    private  String id;
    private String name;
    private  String description;
    private BigDecimal price;

    public final static Product mapToEntity(ProductDTO productDTO){
        return  Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
    }

    public final  static ProductDTO mapToDto(Product product){
        return  ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
