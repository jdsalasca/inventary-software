package com.sena.inventarioback.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.SpecialRequirementDTO;
import com.sena.inventarioback.utils.response.DefaultResponse;

public interface ISpecialRequirementService {
    ResponseEntity<DefaultResponse<SpecialRequirementDTO>> createSpecialRequirement(SpecialRequirementDTO specialRequirementDTO);
    ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getSpecialRequirementById(Long id);
    ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getAllSpecialRequirements(Pageable pageable);
    ResponseEntity<DefaultResponse<SpecialRequirementDTO>> updateSpecialRequirement(Long id, SpecialRequirementDTO specialRequirementDTO);
    void deleteSpecialRequirement(Long id);
	ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getAllSpecialRequirements();
}
