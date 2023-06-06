package com.inventory_service.mapper;


import com.inventory_service.entities.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);
    List<Inventory>  inventoriesToInventoriesResponse(List<Inventory> inventories);

}

