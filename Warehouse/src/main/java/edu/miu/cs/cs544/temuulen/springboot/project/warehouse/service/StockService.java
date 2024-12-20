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

    public void processSale(OrderDTO order) {
        for (OrderDetailDTO detail : order.getDetails()) {
            Product product = productRepository.findById(detail.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            List<Stock> stocks = stockRepository.findByProductOrderByQtyDesc(product);

            int orderedQty = detail.getQty();

            int sum = stocks.stream().mapToInt(Stock::getQty).sum();
            if (sum < orderedQty) {
                throw new RuntimeException("Insufficient stock for product [ID, Name]: [" + product.getId() + ", " + product.getName() + "]");
            }

            for (Stock stock : stocks) {
                if (orderedQty < 0) break;

                int availableQty = stock.getQty();
                if (availableQty >= orderedQty) {
                    logInventoryChange(stock, order.getOrderId(), orderedQty, StockChangeType.SALE, "Sold from only one warehouse");
                    orderedQty = 0;
                }
                else {
                    logInventoryChange(stock, order.getOrderId(), availableQty, StockChangeType.SALE, "Sold from multiple warehouses");
                    orderedQty -= availableQty;
                }
            }

        }
    }

    public void processReturn(Long orderId) {
        List<InventoryLog> logs = inventoryLogRepository.findByOrderId(orderId);
        for (InventoryLog log : logs) {
            logInventoryChange(log.getStock(), orderId, log.getQty(), StockChangeType.RETURN, "Returned");
        }
    }

    private void logInventoryChange(Stock stock, Long orderId, int qty, StockChangeType changeType, String description) {
        InventoryLog log = new InventoryLog(stock, changeType, qty, orderId, new Date(), description);
        log.setLogTimestamp(new Date());
        inventoryLogRepository.save(log);

        int newQty = 0;
        if (changeType == StockChangeType.SALE)
            newQty = stock.getQty() - qty;
        else if (changeType == StockChangeType.RETURN)
            newQty = stock.getQty() + qty;
        else newQty = stock.getQty();

        stock.setQty(newQty);
        stock.setUpdatedAt(new Date());
        stockRepository.save(stock);
    }
}
