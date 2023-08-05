package com.sena.inventarioback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.inventarioback.dto.SpecialRequirementDTO;
import com.sena.inventarioback.repositories.ISpecialRequirementService;
import com.sena.inventarioback.utils.response.DefaultResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/special-requirements")
@Tag(name = "Special Requirements", description = "Endpoints related to managing special requirements")
@RequiredArgsConstructor
public class SpecialRequirementController {

    private final ISpecialRequirementService specialRequirementService;



    @PostMapping("")
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> createSpecialRequirement(
            @Valid @RequestBody SpecialRequirementDTO specialRequirementDTO) {
        return specialRequirementService.createSpecialRequirement(specialRequirementDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getSpecialRequirementById(@PathVariable Long id) {
        return specialRequirementService.getSpecialRequirementById(id);
    }

    @GetMapping("/pageable")
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getAllSpecialRequirements(Pageable pageable) {
        return specialRequirementService.getAllSpecialRequirements(pageable);
    }
    @GetMapping("/")
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getAllSpecialRequirements() {
        return specialRequirementService.getAllSpecialRequirements();
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> updateSpecialRequirement(@PathVariable Long id,
            @Valid @RequestBody SpecialRequirementDTO specialRequirementDTO) {
        return specialRequirementService.updateSpecialRequirement(id, specialRequirementDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialRequirement(@PathVariable Long id) {
        specialRequirementService.deleteSpecialRequirement(id);
        return ResponseEntity.noContent().build();
    }
}
