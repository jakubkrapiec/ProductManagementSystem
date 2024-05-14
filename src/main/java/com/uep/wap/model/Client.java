package com.uep.wap.model;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientID;

    //OneToMany relationship with Order
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders= new ArrayList<>();

    //OneToMany relationship with Invoice
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices = new ArrayList<>();

    //OneToMany relationship with CustomerHistory
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<CustomerHistory> customerHistories = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "VARCHAR(55)")
    private String firstName;

    @Column(nullable = false, columnDefinition = "VARCHAR(55)")
    private String lastName;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String address;


    //getters and setters
    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<CustomerHistory> getCustomerHistories() {
        return customerHistories;
    }

    public void setCustomerHistories(List<CustomerHistory> customerHistories) {
        this.customerHistories = customerHistories;
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


