package edu.miu.cs.cs544.temuulen.springboot.project.order.entity;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment(){}

    public Payment(PaymentType paymentType, double amount, Order order) {
        this.paymentType = paymentType;
        this.amount = amount;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
