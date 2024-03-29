package sn.ucad.productservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.ucad.productservice.dto.ProductDTO;
import sn.ucad.productservice.model.Product;
import sn.ucad.productservice.service.ProductService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO save( @RequestBody ProductDTO productDTO) {
        log.debug("REST request to save product : {}", productDTO);
        return productService.save(productDTO);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    Product create( @RequestBody Product product) {
        log.debug("REST request to save product : {}", product);
        return productService.create(product);
    }


    @Operation(
            summary = "Retrieve a Product by Id",
            description = "Get a product object by specifying its id. The response is Product object with id, name, description and price.",
            tags = { "products", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProductDTO> findAll(){
        log.debug("REST request to get all product");

       return productService.findAll();
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    List<Product> fetchAll(){
        log.debug("REST request to get all product");
        return productService.fetchAll();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable("id") String id){
        log.debug("REST request to deleting the {} product", id);
        productService.delete(id);

    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Product findById(@PathVariable("id") String id){
        log.debug("REST request to find a  product with id: {}", id);
        return productService.findById(id);
    }



    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO update(ProductDTO productDTO){
        return  null;
    }


    @GetMapping("/byName/{name}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO findByName(@PathVariable("name")  String name){
        log.debug("REST request to find a  product with name: {}", name);
        return productService.findByName(name);
    }

}
