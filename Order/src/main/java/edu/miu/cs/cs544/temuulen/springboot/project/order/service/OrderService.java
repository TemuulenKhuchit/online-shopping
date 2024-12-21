package edu.miu.cs.cs544.temuulen.springboot.project.order.service;

import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.OrderStatus;
import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.Order;
import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.PaymentType;
import edu.miu.cs.cs544.temuulen.springboot.project.order.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager em;

    public Order createOrder(Order order) {
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setStatus(orderDetails.getStatus());
        return orderRepository.save(order);
    }

    @Transactional
    public void updateOrderStatusPessimistically(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findByIdForUpdate(orderId);
        order.setStatus(newStatus);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    public List<Order> findByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public List<Order> findAllByStatus(OrderStatus status) {
        return orderRepository.findAllByStatus(status);
    }

    public List<Order> findOrdersByCriteria(LocalDateTime start, LocalDateTime end, OrderStatus status) {
        return orderRepository.findOrdersByCriteria(start, end, status);
    }

    public List<Order> findOrdersByCustomerCityAndPayment(PaymentType paymentType) {
        String jpql = "SELECT DISTINCT o FROM Order o " +
                "JOIN o.customer c " +
                "JOIN o.payments p " +
                "WHERE c.id = o.customer.id " +
                "  AND p.paymentType = :payType";
        return em.createQuery(jpql, Order.class)
                .setParameter("payType", paymentType)
                .getResultList();
    }
}
