package com.sena.inventarioback.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.inventarioback.dto.StatusDTO;
import com.sena.inventarioback.interfaces.IStatusService;
import com.sena.inventarioback.models.Status;
import com.sena.inventarioback.utils.response.DefaultResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {

    private final IStatusService statusService;

    @PostMapping
    public ResponseEntity<DefaultResponse<StatusDTO>> createStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.createStatus(statusDTO);
    }
	@GetMapping("")
	public ResponseEntity<DefaultResponse<Status>> findAll() {
		return statusService.getAllStatus();
	}

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<StatusDTO>> getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse<StatusDTO>> updateStatus(@PathVariable Long id, @RequestBody StatusDTO statusDTO) {
        return statusService.updateStatus(id, statusDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }

    // Add other CRUD endpoints if needed
}
