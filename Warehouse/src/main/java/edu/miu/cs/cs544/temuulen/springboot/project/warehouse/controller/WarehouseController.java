package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.controller;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Warehouse;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse createdWarehouse = warehouseService.createWarehouse(warehouse);
        return new ResponseEntity<>(createdWarehouse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouse();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, warehouse);
        return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteWarehouseById(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }
}
