package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.controller;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Stock;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping(value = "/restock")
    public ResponseEntity<Stock> restock(@RequestParam Long productId, @RequestParam Long warehouseId,
                         @RequestParam int quantity, @RequestParam(required = false) String description) {
        Stock stock = stockService.restock(productId, warehouseId, quantity, description);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @GetMapping(value = "/total-stock")
    public ResponseEntity<Integer> getTotalStockByProduct(@RequestParam Long productId) {
        int totalStock = stockService.getTotalStockByProduct(productId);
        return new ResponseEntity<>(totalStock, HttpStatus.OK);
    }

    // Stock Sale
    // Stock Return
}
