package sn.ucad.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import sn.ucad.productservice.dto.ProductDTO;
import sn.ucad.productservice.model.Product;
import sn.ucad.productservice.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer=  new MongoDBContainer("mongo:4.4");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper  objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static  void  setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        // Given
        ProductDTO productDto = getProductRequest();

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andReturn();

        // Then
        Product createdProduct = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);
        Assertions.assertNotNull(createdProduct.getId());
        Assertions.assertEquals(productDto.getName(), createdProduct.getName());
        Assertions.assertEquals(productDto.getDescription(), createdProduct.getDescription());
        Assertions.assertEquals(productDto.getPrice(), createdProduct.getPrice());

        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(1, products.size());
        Product storedProduct = products.get(0);
        Assertions.assertEquals(createdProduct.getId(), storedProduct.getId());
        Assertions.assertEquals(productDto.getName(), storedProduct.getName());
        Assertions.assertEquals(productDto.getDescription(), storedProduct.getDescription());
    }

    private ProductDTO getProductRequest() {
        return  ProductDTO.builder()
                .name("Iphone 14")
                .description("64 Go")
                .price(BigDecimal.valueOf(1200))
                .build();
    }


}
