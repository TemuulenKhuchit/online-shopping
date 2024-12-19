package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany
    private List<Stock> stocks;

    public Product() {}

    public Product(String name, int price, Category category, Date createdAt, Date updatedAt, List<Stock> stocks) {
        this.name = name;
        this.price = price;
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", category=" + category + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", stocks=" + stocks + '}';
    }
}
