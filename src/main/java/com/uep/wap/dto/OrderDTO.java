package com.uep.wap.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private long orderID;
    private Long clientID;
    private List<Long> productIDs;
    private Long paymentID;
    private Long shipmentID;
    private Long orderReturnID;
    private String orderNumber;
    private Date orderDate;
    private String status;

    // Getters and Setters
    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public List<Long> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<Long> productIDs) {
        this.productIDs = productIDs;
    }

    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public Long getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(Long shipmentID) {
        this.shipmentID = shipmentID;
    }

    public Long getOrderReturnID() {
        return orderReturnID;
    }

    public void setOrderReturnID(Long orderReturnID) {
        this.orderReturnID = orderReturnID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
