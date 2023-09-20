package com.someshvemula.orderservice.enums;

public enum OrderStatus {
    PENDING("Pending"),
    PREPARING("Preparing"),
    ON_ITS_WAY("On Its Way"),
    DELIVERED("Delivered"),
    CANCELED("Canceled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}

