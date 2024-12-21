package edu.miu.cs.cs544.temuulen.springboot.project.order.repository;

import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.OrderStatus;
import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findOrdersByCriteria(LocalDateTime startDate, LocalDateTime endDate, OrderStatus status);
}
