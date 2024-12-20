package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.jms;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class DistributionMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendDistributionMessage(OrderDTO order) {
        jmsTemplate.convertAndSend("distribution.queue", order);
        System.out.println("Order message sent to Distribution system: " + order.getOrderId());
    }
}