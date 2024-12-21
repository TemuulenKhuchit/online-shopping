package edu.miu.cs.cs544.temuulen.springboot.project.order.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.OrderStatus;
import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.Order;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    List<Order> findByOrderNumber(String orderNumber);

    @Query(name = "Order.findByStatus")
    List<Order> findAllByStatus(@Param("status") OrderStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Order findByIdForUpdate(@Param("id") Long id);

}
