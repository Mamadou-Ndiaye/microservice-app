package sn.ucad.productservice.service;


import sn.ucad.productservice.dto.ProductDTO;
import sn.ucad.productservice.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Save a product.
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    ProductDTO save(ProductDTO productDTO);

    Product create(Product product);

    /**
     * Updates a product.
     *
     * @param productDTO the entity to update.
     * @return the persisted entity.
     */
    ProductDTO update(ProductDTO productDTO);

    /**
     * Get all product.
     *
     * @return the list of entities.
     */
    List<ProductDTO> findAll();

    List<Product> fetchAll();

    /**
     * Get the "id" product.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
     Product findById(String id);

    /**
     * Get the "name" product.
     *
     * @param name the name field of the entity.
     * @return the entity.
     */
    ProductDTO findByName(String name);


    /**
     * Delete the "id" product.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
