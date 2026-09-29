package com.mk.petstorebackend.service;

import com.mk.petstorebackend.model.Pet;
import com.mk.petstorebackend.model.PetStatus;
import com.mk.petstorebackend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SampleDataLoader implements ApplicationRunner {

    private final PetRepository repository;

    @Value("${petstore.sample-data:true}")
    private boolean sampleDataEnabled;

    public SampleDataLoader(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!sampleDataEnabled || repository.count() > 0) {
            return;
        }

        repository.saveAll(List.of(
                new Pet("Luna", "Dog", PetStatus.AVAILABLE, new BigDecimal("450.00"), "Border collie"),
                new Pet("Milo", "Cat", PetStatus.AVAILABLE, new BigDecimal("180.00"), "Tabby kitten"),
                new Pet("Kiwi", "Bird", PetStatus.PENDING, new BigDecimal("95.50"), "Budgie"),
                new Pet("Shelly", "Reptile", PetStatus.AVAILABLE, new BigDecimal("220.00"), "Corn snake"),
                new Pet("Nibbles", "Small pet", PetStatus.SOLD, new BigDecimal("35.00"), "Dwarf hamster, sold last week but awaiting pickup."),
                new Pet("Bubbles", "Fish", PetStatus.AVAILABLE, new BigDecimal("12.75"), "Goldfish"),
                new Pet("Rocky", "Dog", PetStatus.PENDING, new BigDecimal("620.00"), "German shepherd"),
                new Pet("Sesame", "Cat", PetStatus.AVAILABLE, new BigDecimal("210.00"), "Tabby")
        ));
    }
}
