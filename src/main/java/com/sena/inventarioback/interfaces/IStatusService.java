package com.sena.inventarioback.interfaces;
import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.StatusDTO;
import com.sena.inventarioback.models.Status;
import com.sena.inventarioback.utils.response.DefaultResponse;

public interface IStatusService {
    ResponseEntity<DefaultResponse<StatusDTO>> createStatus(StatusDTO statusDTO);
    ResponseEntity<DefaultResponse<Status>> getAllStatus();
    ResponseEntity<DefaultResponse<StatusDTO>> getStatusById(Long id);
    ResponseEntity<DefaultResponse<StatusDTO>> updateStatus(Long id, StatusDTO statusDTO);
    void deleteStatus(Long id);
    // Add other CRUD operations if needed
}
