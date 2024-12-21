package edu.miu.cs.cs544.temuulen.springboot.project.order.entity;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User {

    private String loyaltyNumber;

    public Customer() {}

    public Customer(String username, String password, String email, String loyaltyNumber) {
        super(username, password, email);
        this.loyaltyNumber = loyaltyNumber;
    }

    public String getLoyaltyNumber() {
        return loyaltyNumber;
    }

    public void setLoyaltyNumber(String loyaltyNumber) {
        this.loyaltyNumber = loyaltyNumber;
    }
}
