package com.sena.inventarioback.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadDocumentDTO {

    private Long id;

//    @NotNull(message = "startDate is required")
//    private LocalDate startDate;
//
//    @NotNull(message = "dueDate is required")
//    @FutureOrPresent(message = "dueDate must be a future or present date")
//    private LocalDate dueDate;

    @NotNull(message = "physicalDocumentId is required")
    private Long physicalDocumentId;

    @NotNull(message = "idUser is required")
    private Long idUser;

    @NotNull(message = "idStatus is required")
    private Long idStatus;
}
