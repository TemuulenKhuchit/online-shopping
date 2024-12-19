package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Stock() {}

    public Stock(Product product, Warehouse warehouse, int quantity, Date updatedAt) {
        this.product = product;
        this.warehouse = warehouse;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", product=" + product + ", warehouse=" + warehouse + ", quantity=" + quantity + ", updatedAt=" + updatedAt + '}';
    }
}
