package com.uep.wap.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CustomerHistory")
public class CustomerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerHistoryID;

    //ManyToOne relationship with Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientID", nullable = false)
    private Client client;

    //ManyToMany relationship with Orders
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_history_orders",
            joinColumns = @JoinColumn(name = "customer_history_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders = new ArrayList<>();

    //ManyToMany relationship with Product
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_history_products",
            joinColumns = @JoinColumn(name = "customer_history_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    //getters and setters
    public Long getCustomerHistoryID() {
        return customerHistoryID;
    }

    public void setCustomerHistoryID(Long customerHistoryID) {
        this.customerHistoryID = customerHistoryID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
