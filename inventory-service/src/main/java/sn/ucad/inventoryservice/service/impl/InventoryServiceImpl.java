package sn.ucad.inventoryservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.inventoryservice.dto.InventoryResponse;
import sn.ucad.inventoryservice.model.Inventory;
import sn.ucad.inventoryservice.repository.InventoryRepository;
import sn.ucad.inventoryservice.service.InventoryService;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {


    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(
                inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()

        ).toList();
    }
  @Override
    public Inventory save(Inventory inventory) {
      log.info("InventoryServiceImpl:save  new inventory execution started ");
      return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        log.info("****** InventoryServiceImpl:findAll execution started ******* ");
        return inventoryRepository.findAll();
    }

      /*
    @Override
    public Inventory update(Inventory inventory) {
        return null;
    }



    @Override
    public Inventory findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }*/
}
