package com.uep.wap.dto;

import java.util.List;

public class WarehouseDTO {
    private Long warehouseId;
    private List<Long> productIds;
    private String location;
    private String availability;
    private List<Long> shipmentIds;

    // Getters and Setters
    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public List<Long> getShipmentIds() {
        return shipmentIds;
    }

    public void setShipmentIds(List<Long> shipmentIds) {
        this.shipmentIds = shipmentIds;
    }
}
