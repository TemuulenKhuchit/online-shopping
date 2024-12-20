package edu.miu.cs.cs544.temuulen.springboot.project.warehouse.dto;

public class OrderDetailDTO {
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
