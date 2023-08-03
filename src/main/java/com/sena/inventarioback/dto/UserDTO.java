package com.sena.inventarioback.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "cellphone cannot be blank")
    private String cellphone;
    @NotNull(message = "dateOfBirthh cannot be null")
    @Past(message = "dateOfBirth must be in the past")
    private LocalDate dateOfBirth;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
    @NotNull(message = "gender cannot be null")
    private Integer gender;
    @NotNull(message = "documentType cannot be null")
    private Integer documentType;
    @NotNull(message = "Status cannot be null")
    private Integer status;
    @NotBlank(message = "documentNumber cannot be blank")
    private String documentNumber;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "userName cannot be blank")
    private String userName;
}
