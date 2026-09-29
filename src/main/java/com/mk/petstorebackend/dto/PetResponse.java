package com.mk.petstorebackend.dto;

import com.mk.petstorebackend.model.PetStatus;

import java.math.BigDecimal;

public record PetResponse(
        Long id,
        String name,
        String category,
        PetStatus status,
        BigDecimal price,
        String description
) {
}
