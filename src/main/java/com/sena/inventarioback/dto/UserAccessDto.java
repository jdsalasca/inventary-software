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
	String userName;
	String token;
	Boolean validCredencials;
	
	public UserAccessDto(User user, boolean valid){
		this.fistName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.userName = user.getUserName();
		this.validCredencials = valid;
	}

}
