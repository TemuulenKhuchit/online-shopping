package edu.miu.cs.cs544.temuulen.springboot.project.order.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

    @JmsListener(destination = "orders.queue")
    public void receiveOrderCreated(String message) {
        System.out.println("Received message: " + message);
    }
}