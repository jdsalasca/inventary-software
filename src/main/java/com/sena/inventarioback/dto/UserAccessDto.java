package com.sena.inventarioback.dto;

import com.sena.inventarioback.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessDto {
	String fistName;
	String lastName;
	String email;
	Integer idUser;
	String userName;
	String token;
	String userArea;
	Boolean validCredencials;
	
	public UserAccessDto(User user, boolean valid, String userArea){
		this.fistName = user.getFirstName();
		this.lastName = user.getLastName();
		this.idUser = user.getId();
		this.email = user.getEmail();
		this.userArea = userArea;
		this.userName = user.getUserName();
		this.validCredencials = valid;
	}

}
