package com.sena.inventarioback.interfaces;
import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.BoxDTO;
import com.sena.inventarioback.utils.response.DefaultResponse;

public interface IBoxService {
    ResponseEntity<DefaultResponse<BoxDTO>> createBox(BoxDTO boxDTO);
    ResponseEntity<DefaultResponse<BoxDTO>> getBoxById(Long id);
    ResponseEntity<DefaultResponse<BoxDTO>> getAllBoxes();
    ResponseEntity<DefaultResponse<BoxDTO>> updateBox(Long id, BoxDTO boxDTO);
    void deleteBox(Long id);
}
