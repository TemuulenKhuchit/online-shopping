package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {

    @Query("SELECT l FROM InventoryLog l WHERE l.orderId = :orderId")
    List<InventoryLog> findByOrderId(@Param("orderId") Long orderId);

}
