package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
