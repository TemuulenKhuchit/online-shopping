package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.jms;

import edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageConsumer {

    @Autowired
    private DistributionMessageProducer distributionMessageProducer;

    @JmsListener(destination = "orders.queue")
    public void receiveOrderMessage(OrderDTO order) {
        System.out.println("Received order message from Order system: " + order.getOrderId());
        distributionMessageProducer.sendDistributionOrderMessage(order);
    }

    @JmsListener(destination = "returns.queue")
    public void receiveReturnMessage(Long orderId) {
        System.out.println("Received return message from Order system: " + orderId);
        distributionMessageProducer.sendDistributionReturnMessage(orderId);
    }

}
