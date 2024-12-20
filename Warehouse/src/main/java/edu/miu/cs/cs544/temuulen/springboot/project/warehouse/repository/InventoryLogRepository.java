package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {

    @Query("SELECT l FROM InventoryLog l " +
            "WHERE l.orderId = :orderId AND l.changeType = 'SALE' AND l.orderId NOT IN (" +
            "SELECT k.orderId FROM InventoryLog k WHERE k.orderId = :orderId AND k.changeType = 'RETURN')")
    Optional<List<InventoryLog>> findByOrderId(@Param("orderId") Long orderId);

}
