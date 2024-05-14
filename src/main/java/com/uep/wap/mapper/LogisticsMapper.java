package com.uep.wap.mapper;

import com.uep.wap.dto.LogisticsDTO;
import com.uep.wap.model.Logistics;
import com.uep.wap.model.Order;
import com.uep.wap.model.Warehouse;

public class LogisticsMapper {

    public static LogisticsDTO toDTO(Logistics logistics) {
        if (logistics == null) {
            return null;
        }

        LogisticsDTO logisticsDTO = new LogisticsDTO();
        logisticsDTO.setShipmentId(logistics.getShipmentId());
        logisticsDTO.setWarehouseId(logistics.getWarehouse() != null ? logistics.getWarehouse().getWarehouseId() : null);
        logisticsDTO.setOrderId(logistics.getOrder() != null ? logistics.getOrder().getOrderID() : null);
        logisticsDTO.setShippingDate(logistics.getShippingDate());
        logisticsDTO.setShippingAddress(logistics.getShippingAddress());

        return logisticsDTO;
    }

    public static Logistics toEntity(LogisticsDTO logisticsDTO, Warehouse warehouse, Order order) {
        if (logisticsDTO == null) {
            return null;
        }

        Logistics logistics = new Logistics();
        logistics.setShipmentId(logisticsDTO.getShipmentId());
        logistics.setWarehouse(warehouse);
        logistics.setOrder(order);
        logistics.setShippingDate(logisticsDTO.getShippingDate());
        logistics.setShippingAddress(logisticsDTO.getShippingAddress());

        return logistics;
    }
}
