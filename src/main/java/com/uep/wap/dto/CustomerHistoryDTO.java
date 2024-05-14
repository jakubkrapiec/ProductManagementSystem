package com.uep.wap.dto;

import java.util.List;

public class CustomerHistoryDTO {
    private Long customerHistoryID;
    private Long clientId;
    private List<Long> orderIds;
    private List<Long> productIds;

    // Getters and Setters
    public Long getCustomerHistoryID() {
        return customerHistoryID;
    }

    public void setCustomerHistoryID(Long customerHistoryID) {
        this.customerHistoryID = customerHistoryID;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
