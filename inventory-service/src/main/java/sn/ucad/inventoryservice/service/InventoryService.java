package sn.ucad.inventoryservice.service;

import org.springframework.web.bind.annotation.PathVariable;
import sn.ucad.inventoryservice.dto.InventoryResponse;
import sn.ucad.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryService {


    List<InventoryResponse> isInStock(List<String> skuCode);

    /**
     * Save a inventory.
     *
     * @param inventory the entity to save.
     * @return the persisted entity.
     */
    Inventory save(Inventory inventory);


     /**
      * Get all Inventory.
      * @return the list of entities.
      *
      */
    List<Inventory> findAll();



    /* *//**
     * Updates a Order.
     *
     * @param inventory the entity to update.
     * @return the persisted entity.
     *//*
    Inventory update(Inventory inventory);



    *//**
     * Get the "id" Inventory.
     *
     * @param id the id of the entity.
     * @return the entity.
     *//*
    Inventory findById(Long id);



    *//**
     * Delete the "id" Inventory.
     *
     * @param id the id of the entity.
     *//*
    void delete(Long id);*/
}
