package com.mk.petstorebackend.mapper;

import com.mk.petstorebackend.dto.PetRequest;
import com.mk.petstorebackend.dto.PetResponse;
import com.mk.petstorebackend.model.Pet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PetMapper {

    public PetResponse toResponse(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getCategory(),
                pet.getStatus(),
                pet.getPrice() == null ? BigDecimal.ZERO : pet.getPrice(),
                pet.getDescription()
        );
    }

    public Pet toEntity(PetRequest request) {
        Pet pet = new Pet();
        pet.setName(request.name());
        pet.setCategory(request.category());
        pet.setStatus(request.status());
        pet.setPrice(request.price() == null ? BigDecimal.ZERO : request.price());
        pet.setDescription(request.description());
        return pet;
    }

    public void updateEntity(Pet pet, PetRequest request) {
        pet.setName(request.name());
        pet.setCategory(request.category());
        pet.setStatus(request.status());
        pet.setPrice(request.price() == null ? BigDecimal.ZERO : request.price());
        pet.setDescription(request.description());
    }
}
