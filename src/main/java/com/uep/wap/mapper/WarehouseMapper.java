package com.uep.wap.mapper;

import com.uep.wap.dto.WarehouseDTO;
import com.uep.wap.model.Logistics;
import com.uep.wap.model.Product;
import com.uep.wap.model.Warehouse;

import java.util.List;
import java.util.stream.Collectors;

public class WarehouseMapper {

    public static WarehouseDTO toDTO(Warehouse warehouse) {
        if (warehouse == null) {
            return null;
        }

        WarehouseDTO dto = new WarehouseDTO();
        dto.setWarehouseId(warehouse.getWarehouseId());
        dto.setProductIds(warehouse.getProducts().stream().map(Product::getProductId).collect(Collectors.toList()));
        dto.setLocation(warehouse.getLocation());
        dto.setAvailability(warehouse.getAvailability());
        dto.setShipmentIds(warehouse.getShipments().stream().map(Logistics::getShipmentId).collect(Collectors.toList()));

        return dto;
    }

    public static Warehouse toEntity(WarehouseDTO dto, List<Product> products, List<Logistics> shipments) {
        if (dto == null) {
            return null;
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(dto.getWarehouseId());
        warehouse.setProducts(products);
        warehouse.setLocation(dto.getLocation());
        warehouse.setAvailability(dto.getAvailability());
        warehouse.setShipments(shipments);

        return warehouse;
    }
}
