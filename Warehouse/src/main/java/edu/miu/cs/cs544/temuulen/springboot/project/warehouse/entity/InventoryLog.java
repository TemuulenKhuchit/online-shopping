package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class InventoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Stock stock;

    @Enumerated(EnumType.STRING)
    private StockChangeType changeType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date logTimestamp;

    private String description;

    public InventoryLog() {}

    public InventoryLog(Stock stock, StockChangeType changeType, Date logTimestamp, String description) {
        this.stock = stock;
        this.changeType = changeType;
        this.logTimestamp = logTimestamp;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StockChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(StockChangeType changeType) {
        this.changeType = changeType;
    }

    public Date getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(Date logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "InventoryLog{" + "id=" + id + ", stock=" + stock + ", changeType=" + changeType + ", logTimestamp=" + logTimestamp + ", description='" + description + '\'' + '}';
    }
}
