package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.controller;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto.OrderDTO;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service.OrderService;
import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private OrderService orderService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOrderToWarehouse(@RequestBody OrderDTO order) {
        orderService.processSale(order);
        jmsTemplate.convertAndSend("orders.queue", order);
        return new ResponseEntity<>("Order sent to Warehouse system: " + order.getOrderId(), HttpStatus.OK);
    }

    @PostMapping(value = "/return/{orderId}")
    public ResponseEntity<String> processReturn(@PathVariable Long orderId) {
        orderService.processReturn(orderId);
        jmsTemplate.convertAndSend("orders.queue", orderId);
        return new ResponseEntity<>("Order return request sent to Warehouse system: " + orderId, HttpStatus.OK);
    }

}