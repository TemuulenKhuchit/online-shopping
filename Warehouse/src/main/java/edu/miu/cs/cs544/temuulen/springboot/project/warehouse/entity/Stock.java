package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("product-stock")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonBackReference("warehouse-stock")
    private Warehouse warehouse;

    private int qty;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InventoryLog> logs;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Stock() {}

    public Stock(Product product, Warehouse warehouse, int qty, Date updatedAt) {
        this.product = product;
        this.warehouse = warehouse;
        this.qty = qty;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", product=" + product + ", warehouse=" + warehouse + ", qty=" + qty + ", updatedAt=" + updatedAt + '}';
    }
}
