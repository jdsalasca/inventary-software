package com.sena.inventarioback.dto;

import java.util.List;

import com.sena.inventarioback.models.DocumentType;
import com.sena.inventarioback.models.Gender;
import com.sena.inventarioback.models.Status;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResourcesDTO {
	
	List<Gender> genders;
	List<Status> status;
	List<DocumentType> documentTypes;

}
