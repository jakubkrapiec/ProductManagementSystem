package com.uep.wap.dto;

import java.util.List;

public class ClientDTO {
    private long clientID;
    private List<Long> orderIds;
    private List<Long> invoiceIds;
    private List<Long> customerHistoryIds;
    private String firstName;
    private String lastName;
    private String address;

    // Getters and Setters
    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public List<Long> getInvoiceIds() {
        return invoiceIds;
    }

    public void setInvoiceIds(List<Long> invoiceIds) {
        this.invoiceIds = invoiceIds;
    }

    public List<Long> getCustomerHistoryIds() {
        return customerHistoryIds;
    }

    public void setCustomerHistoryIds(List<Long> customerHistoryIds) {
        this.customerHistoryIds = customerHistoryIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
