package sn.ucad.inventoryservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.ucad.inventoryservice.dto.InventoryResponse;
import sn.ucad.inventoryservice.model.Inventory;
import sn.ucad.inventoryservice.service.InventoryService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private  final  InventoryService inventoryService;



    // http://localhost:8082/api/inventory/iphone-13,iphone13-red

    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public  List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return  inventoryService.isInStock(skuCode);
    }

    @GetMapping("/fetchAll")
    @ResponseStatus(HttpStatus.OK)
    public  List<Inventory> fetchAll(){
        log.info("Rest to get All Inventory");
        return  inventoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Inventory save( @RequestBody Inventory inventory) {
        log.debug("REST request to save product : {}", inventory);
        return inventoryService.save(inventory);
    }
}
