package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.jms;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class DistributionMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendDistributionOrderMessage(OrderDTO order) {
        jmsTemplate.convertAndSend("distributionOrder.queue", order);
        System.out.println("Order message sent to Distribution system: " + order.getOrderId());
    }

    public void sendDistributionReturnMessage(Long orderId) {
        jmsTemplate.convertAndSend("distributionReturn.queue", orderId);
        System.out.println("Return message sent to Distribution system: " + orderId);
    }
}