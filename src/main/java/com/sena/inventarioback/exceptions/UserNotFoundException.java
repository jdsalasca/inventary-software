
package com.sena.inventarioback.exceptions;

import java.io.IOException;

import lombok.*;
/**
Exception thrown when a user is not found in the People API.
*/
@RequiredArgsConstructor
@Getter
@ToString
public class UserNotFoundException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	private final String message;
	@Override
	public String getLocalizedMessage() {
		return this.message;
	}
	
	

}