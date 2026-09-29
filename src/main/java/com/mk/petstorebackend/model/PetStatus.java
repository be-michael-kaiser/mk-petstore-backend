package com.mk.petstorebackend.model;

public enum PetStatus {
    AVAILABLE("Available"),
    PENDING("Pending"),
    SOLD("Sold");

    private final String label;

    PetStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
