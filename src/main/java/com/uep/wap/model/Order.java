package com.uep.wap.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    //ManyToOne relationship with Client
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private Client client;

    //ManyToMany relationship with Product
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    //OneToOne relationship with Payment
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    //ManyToMany relationship with CustomerHistory
    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<CustomerHistory> customerHistories;

    //OneToOne relationship with Logistics
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Logistics shipment;


    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Return orderReturn;

    @Column(nullable = false, columnDefinition = "VARCHAR(55)")
    private String orderNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    public enum OrderStatus {
        DELIVERED, NOT_DELIVERED, COMPLETING_ORDER
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    //getters and setters
    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<CustomerHistory> getCustomerHistories() {
        return customerHistories;
    }

    public void setCustomerHistories(List<CustomerHistory> customerHistories) {
        this.customerHistories = customerHistories;
    }

    public Logistics getShipment() {
        return shipment;
    }

    public void setShipment(Logistics shipment) {
        this.shipment = shipment;
    }

    public Return getOrderReturn() {
        return orderReturn;
    }

    public void setOrderReturn(Return orderReturn) {
        this.orderReturn = orderReturn;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}




