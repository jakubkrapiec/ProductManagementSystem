package com.uep.wap.model;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table()
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;

    private String invoiceNumber;

    //ManyToOne relationship with Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID")
    private Order order;

    //ManyToOne relationship with Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientID")
    private Client client;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private double price;

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}




