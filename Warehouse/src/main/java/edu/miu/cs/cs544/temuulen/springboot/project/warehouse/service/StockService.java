package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto.OrderDTO;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto.OrderDetailDTO;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.*;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.InventoryLogRepository;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.ProductRepository;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.StockRepository;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    public Stock restock(Long productId, Long warehouseId, int qty, String description) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Stock stock = stockRepository.findByProductAndWarehouse(product, warehouse)
                .orElseGet(() -> new Stock(product, warehouse, 0, null));

        stock.setQty(stock.getQty() + qty);
        stock.setUpdatedAt(new Date());
        stock = stockRepository.save(stock);

        InventoryLog log = new InventoryLog(stock, StockChangeType.RESTOCK, qty, null, new Date(),
                description != null ? description : "Restock operation");
        inventoryLogRepository.save(log);

        return stock;
    }

    public int getTotalStockByProduct(Long productId) {
        return stockRepository.findTotalStockByProductId((productId)).orElse(0);
    }

}
