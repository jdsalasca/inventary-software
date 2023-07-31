package com.sena.inventarioback.services;


import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sena.inventarioback.dto.StatusDTO;
import com.sena.inventarioback.interfaces.IStatusService;
import com.sena.inventarioback.models.Status;
import com.sena.inventarioback.repositories.StatusRepository;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusServiceImpl implements IStatusService {

    private final StatusRepository statusRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<DefaultResponse<StatusDTO>> createStatus(StatusDTO statusDTO) {
        var status = modelMapper.map(statusDTO, Status.class);
        var savedStatus = statusRepository.save(status);
        return DefaultResponse.onThrow200Response(modelMapper.map(savedStatus, StatusDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<StatusDTO>> getStatusById(Long id) {
        var status = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + id));
        return DefaultResponse.onThrow200Response(modelMapper.map(status, StatusDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<StatusDTO>> updateStatus(Long id, StatusDTO statusDTO) {
        var existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + id));

        modelMapper.map(statusDTO, existingStatus);
        var updatedStatus = statusRepository.save(existingStatus);
        return DefaultResponse.onThrow200Response(modelMapper.map(updatedStatus, StatusDTO.class));
    }

    @Override
    public void deleteStatus(Long id) {
        var existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + id));
        statusRepository.delete(existingStatus);
    }

	@Override
	@Cacheable("status") // Enable caching for this method
	public ResponseEntity<DefaultResponse<Status>> getAllStatus() {
		log.info("consuming status");
		return DefaultResponse.onHandlerFindJpa(statusRepository.findAll());
	}
}