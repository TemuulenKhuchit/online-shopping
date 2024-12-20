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

    public Stock restock(Long productId, Long warehouseId, int quantity, String description) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Stock stock = stockRepository.findByProductAndWarehouse(product, warehouse)
                .orElseGet(() -> new Stock(product, warehouse, 0, null));

        stock.setQty(stock.getQty() + quantity);
        stock.setUpdatedAt(new Date());
        stock = stockRepository.save(stock);

        InventoryLog log = new InventoryLog(stock, StockChangeType.RESTOCK, quantity, new Date(),
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

            List<Stock> stocks = stockRepository.findByProductOrderByQuantityDesc(product);

            int orderedQty = detail.getQty();

            int sum = stocks.stream().mapToInt(Stock::getQty).sum();
            if (sum < orderedQty) {
                throw new RuntimeException("Insufficient stock for product [ID, Name]: [" + product.getId() + ", " + product.getName() + "]");
            }

            for (Stock stock : stocks) {
                if (orderedQty < 0) break;

                int availableQty = stock.getQty();
                if (availableQty >= orderedQty) {
                    stock.setQty(availableQty - orderedQty);
                    stock.setUpdatedAt(new Date());
                    stockRepository.save(stock);
                    orderedQty = 0;
                }
                else {
                    stock.setQty(0);
                    stock.setUpdatedAt(new Date());
                    stockRepository.save(stock);
                    orderedQty -= availableQty;
                }
            }

        }
    }


    public void processReturn(OrderDTO order) {

    }
}
