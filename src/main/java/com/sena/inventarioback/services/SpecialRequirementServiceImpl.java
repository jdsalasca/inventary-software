package com.sena.inventarioback.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sena.inventarioback.dto.PhysicalDocumentDTO;
import com.sena.inventarioback.dto.SpecialRequirementDTO;
import com.sena.inventarioback.models.SpecialRequirement;
import com.sena.inventarioback.repositories.ISpecialRequirementService;
import com.sena.inventarioback.repositories.SpecialRequirementRepository;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialRequirementServiceImpl implements ISpecialRequirementService {

    private final SpecialRequirementRepository specialRequirementRepository;
    private final ModelMapper modelMapper;



    @Override
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> createSpecialRequirement(SpecialRequirementDTO specialRequirementDTO) {
        SpecialRequirement specialRequirement = modelMapper.map(specialRequirementDTO, SpecialRequirement.class);
        specialRequirement.setCreatedAt(LocalDateTime.now());
        specialRequirement.setUpdatedAt(LocalDateTime.now());
        SpecialRequirement savedRequirement = specialRequirementRepository.save(specialRequirement);
        SpecialRequirementDTO savedRequirementDTO = modelMapper.map(savedRequirement, SpecialRequirementDTO.class);
        return DefaultResponse.onThrow200Response(savedRequirementDTO);
    }

    @Override
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getSpecialRequirementById(Long id) {
        SpecialRequirement specialRequirement = specialRequirementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Special requirement not found with id: " + id));
        SpecialRequirementDTO specialRequirementDTO = modelMapper.map(specialRequirement, SpecialRequirementDTO.class);
        return DefaultResponse.onThrow200Response(specialRequirementDTO);
    }

    @Override
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getAllSpecialRequirements(Pageable pageable) {
        Page<SpecialRequirement> specialRequirements = specialRequirementRepository.findAll(pageable);
        
        Page<SpecialRequirementDTO> physicalDocumentDTOPage = specialRequirements.map(doc -> modelMapper.map(doc, SpecialRequirementDTO.class));
        return DefaultResponse.onHandlerFindJpa(physicalDocumentDTOPage);
    }

    @Override
    public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> updateSpecialRequirement(Long id, SpecialRequirementDTO specialRequirementDTO) {
        SpecialRequirement existingRequirement = specialRequirementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Special requirement not found with id: " + id));

        modelMapper.map(specialRequirementDTO, existingRequirement);
        existingRequirement.setUpdatedAt(LocalDateTime.now());
        SpecialRequirement updatedRequirement = specialRequirementRepository.save(existingRequirement);
        SpecialRequirementDTO updatedRequirementDTO = modelMapper.map(updatedRequirement, SpecialRequirementDTO.class);
        return DefaultResponse.onThrow200Response(updatedRequirementDTO);
    }

    @Override
    public void deleteSpecialRequirement(Long id) {
        SpecialRequirement existingRequirement = specialRequirementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Special requirement not found with id: " + id));
        specialRequirementRepository.delete(existingRequirement);
    }

	@Override
	public ResponseEntity<DefaultResponse<SpecialRequirementDTO>> getAllSpecialRequirements() {
		List<SpecialRequirement> specialRequirements = specialRequirementRepository.findAll();
		return DefaultResponse.onHandlerFindJpa(specialRequirements.stream().map(SpecialRequirementDTO::new).collect(Collectors.toList()));
	}
    
}