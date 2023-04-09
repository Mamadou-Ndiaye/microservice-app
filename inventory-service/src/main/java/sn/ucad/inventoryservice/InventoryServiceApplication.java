package sn.ucad.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sn.ucad.inventoryservice.model.Inventory;
import sn.ucad.inventoryservice.service.InventoryService;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication  implements CommandLineRunner {

    private  final InventoryService inventoryService;

    public InventoryServiceApplication(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

      /* inventoryService.save(new Inventory(null,"AA01",13));
       inventoryService.save(new Inventory(null,"AA02",10));
       inventoryService.save(new Inventory(null,"AA03",5));
       inventoryService.save(new Inventory(null,"AA04",3));*/
       /* inventoryService.save(new Inventory(null,"iphone-13",7));
        inventoryService.save(new Inventory(null,"iphone-13-red",2));*/
    }
}
