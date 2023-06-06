package com.inventory_service.apis;

import com.inventory_service.mapper.InventoryMapper;
import com.inventory_service.request.BlockInventoryRequest;
import com.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
@Transactional
public class InventoryController {

    Logger log = LoggerFactory.getLogger(InventoryController.class);
    private  final InventoryService inventoryService;
    private final InventoryMapper inventoryMapper = InventoryMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<?> getInventoriesByProductIds(@RequestParam("productIds") List<UUID> productIds){

        log.info("requested to get inventories  for products"+productIds);

        return  new ResponseEntity<>(inventoryMapper.inventoriesToInventoriesResponse(inventoryService.getInventoriesByProductIds(productIds)), HttpStatus.OK);
    }

    @PostMapping("/check")
    public  ResponseEntity<?> checkAndBlockInventories( @RequestBody BlockInventoryRequest blockInventoryRequest){

        log.info("requested check inventory for products and block inventories");
        var saved=inventoryService.checkAndBlockInventories(blockInventoryRequest);
        return  new ResponseEntity<>(saved, HttpStatus.CREATED);


    }
    @PutMapping("/unblock")
    public  ResponseEntity<?> unblockInventories(@RequestParam("blockedInventoryIds") List<UUID> blockedInventoryIds){
        log.info("requested unblock inventories"+blockedInventoryIds);
        var saved=inventoryService.unblockInventories(blockedInventoryIds);
        return  new ResponseEntity<>(saved, HttpStatus.OK);


    }
    @PutMapping("/unblock-and-update")
    public  ResponseEntity<?> unblockInventoriesAndUpdate(@RequestParam("blockedInventoryIds") List<UUID> blockedInventoryIds){
        log.info("requested unblock inventories"+blockedInventoryIds);
        var saved=inventoryService.unblockInventoriesAndUpdate(blockedInventoryIds);
        return  new ResponseEntity<>(saved, HttpStatus.OK);


    }

}
