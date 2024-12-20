package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {

}
