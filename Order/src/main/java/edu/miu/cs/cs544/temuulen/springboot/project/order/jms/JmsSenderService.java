package edu.miu.cs.cs544.temuulen.springboot.project.order.jms;

import edu.miu.cs.cs544.temuulen.springboot.project.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JmsSenderService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("orders.queue")
    private String orderQueue;

    public void sendOrderCreated(Order order) {
        jmsTemplate.convertAndSend(orderQueue, "OrderCreated:" + order.getId());
    }
}