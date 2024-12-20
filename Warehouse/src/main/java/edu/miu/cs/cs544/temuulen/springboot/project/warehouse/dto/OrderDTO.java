package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private String orderNumber;
    private String orderStatus;
    private Date orderDate;
    private List<OrderDetailDTO> details;

    public String getOrderId() {
        return orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailDTO> details) {
        this.details = details;
    }
}
