package com.sena.inventarioback.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sena.inventarioback.dto.BoxDTO;
import com.sena.inventarioback.interfaces.IBoxService;
import com.sena.inventarioback.models.Box;
import com.sena.inventarioback.repositories.BoxRepository;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoxService implements IBoxService {

    private final BoxRepository boxRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<DefaultResponse<BoxDTO>> createBox(BoxDTO boxDTO) {
        var box = modelMapper.map(boxDTO, Box.class);
        var savedBox = boxRepository.save(box);
        return DefaultResponse.onThrow200Response(modelMapper.map(savedBox, BoxDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<BoxDTO>> getBoxById(Long id) {
        var box = boxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Box not found with id: " + id));
        return DefaultResponse.onThrow200Response(modelMapper.map(box, BoxDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<BoxDTO>> getAllBoxes() {
        var boxes = boxRepository.findAll();
        List<BoxDTO> boxDTOs = boxes.stream()
                .map(box -> modelMapper.map(box, BoxDTO.class))
                .collect(Collectors.toList());
        return DefaultResponse.onThrow200Response(boxDTOs);
    }

    @Override
    public ResponseEntity<DefaultResponse<BoxDTO>> updateBox(Long id, BoxDTO boxDTO) {
        var existingBox = boxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Box not found with id: " + id));
        modelMapper.map(boxDTO, existingBox);
        var updatedBox = boxRepository.save(existingBox);
        return DefaultResponse.onThrow200Response(modelMapper.map(updatedBox, BoxDTO.class));
    }

    @Override
    public void deleteBox(Long id) {
        var existingBox = boxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Box not found with id: " + id));
        boxRepository.delete(existingBox);
    }
}