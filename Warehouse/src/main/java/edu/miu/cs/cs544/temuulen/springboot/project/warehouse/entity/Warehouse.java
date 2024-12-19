package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double size;

    @Embedded
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany
    private List<Stock> stocks;

    public Warehouse() {}

    public Warehouse(String name, double size, Address address, Date createdAt, Date updatedAt, List<Stock> stocks) {
        this.name = name;
        this.size = size;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stocks = stocks;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "Warehouse{" + "id=" + id + ", name='" + name + '\'' + ", size=" + size + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", stocks=" + stocks + '}';
    }
}
