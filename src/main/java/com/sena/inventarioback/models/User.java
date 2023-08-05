package com.sena.inventarioback.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "cellphone", nullable = false, unique = true)
	private String cellphone;
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt;
	@Column(name = "gender_id", nullable = false)
	private Integer gender;
	@Column(name = "document_type_id", nullable = false)
	private Integer documentType;
	@Column(name = "area_id", nullable = false)
	private Integer areaId;
	@Column(name = "status_id", nullable = false)
	private Integer status;
	@Column(name = "document_number", nullable = false)
	private String documentNumber;
	@Column(name = "password", nullable = false)
	//@JsonIgnore
	private String password;
	@Column(name = "user_name", nullable = false)
	private String userName;

}
