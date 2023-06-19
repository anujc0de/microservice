package com.inventory_service.mapper;

import com.common.response.BlockInventoryResponse;
import com.inventory_service.entities.BlockInventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlockInventoryMapper {
    BlockInventoryMapper INSTANCE = Mappers.getMapper(BlockInventoryMapper.class);
    List<BlockInventoryResponse> blockinventoriesToBlockInventoriesResponse(List<BlockInventory> blockInventories);
}
