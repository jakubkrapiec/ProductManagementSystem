package com.uep.wap.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    // Many-to-Many relationship with Product
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "warehouse_product",
            joinColumns = @JoinColumn(name = "warehouse_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String location;

    @Column(nullable = false)
    private String availability;

    // OneToMany relationship with Logistics
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Logistics> shipments = new ArrayList<>();

    //getters and setters
    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public List<Logistics> getShipments() {
        return shipments;
    }

    public void setShipments(List<Logistics> shipments) {
        this.shipments = shipments;
    }
}


