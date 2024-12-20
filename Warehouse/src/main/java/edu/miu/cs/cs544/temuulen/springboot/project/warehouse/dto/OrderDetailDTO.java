package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long productId;
    private int qty;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
