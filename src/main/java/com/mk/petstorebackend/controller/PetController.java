package com.mk.petstorebackend.controller;

import com.mk.petstorebackend.dto.PetRequest;
import com.mk.petstorebackend.dto.PetResponse;
import com.mk.petstorebackend.exception.ResourceNotFoundException;
import com.mk.petstorebackend.mapper.PetMapper;
import com.mk.petstorebackend.model.Pet;
import com.mk.petstorebackend.model.PetStatus;
import com.mk.petstorebackend.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class PetController {

    private final PetService service;
    private final PetMapper mapper;

    public PetController(PetService service, PetMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/pets")
    public List<PetResponse> listPets(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) PetStatus status) {
        return service.search(search, status).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/pets/{id}")
    public PetResponse getPet(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found: " + id)));
    }

    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse createPet(@Valid @RequestBody PetRequest request) {
        Pet saved = service.save(mapper.toEntity(request));
        return mapper.toResponse(saved);
    }

    @PutMapping("/pets/{id}")
    public PetResponse updatePet(@PathVariable Long id, @Valid @RequestBody PetRequest request) {
        Pet pet = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found: " + id));
        mapper.updateEntity(pet, request);
        return mapper.toResponse(service.save(pet));
    }

    @DeleteMapping("/pets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Pet not found: " + id);
        }
        service.deleteById(id);
    }

    @GetMapping("/pets/categories")
    public List<String> getCategories() {
        return service.findCategories();
    }
}
