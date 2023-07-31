package com.sena.inventarioback.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
}
