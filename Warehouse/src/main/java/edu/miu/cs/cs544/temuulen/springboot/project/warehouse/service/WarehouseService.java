package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Warehouse;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Warehouse createWarehouse(Warehouse warehouse) {
        warehouse.setCreatedAt(new Date());
        return warehouseRepository.save(warehouse);
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) {
        Warehouse warehouse = getWarehouseById(id);
        warehouse.setName(warehouseDetails.getName());
        warehouse.setArea(warehouseDetails.getArea());
        warehouse.setAddress(warehouseDetails.getAddress());
        warehouse.setUpdatedAt(new Date());
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        Warehouse warehouse = getWarehouseById(id);
        warehouseRepository.delete(warehouse);
    }
}
