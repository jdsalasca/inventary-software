package com.sena.inventarioback.utils;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sena.inventarioback.utils.response.DefaultResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerController {

//    //@ExceptionHandler(Exception.class)
//    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ResponseEntity<DefaultResponse<String>> handleException(Exception ex) {
//        log.error("Unhandled exception occurred.", ex);
//        return new ErrorResponse("An unexpected error occurred.");
//    }

	@ExceptionHandler(PropertyReferenceException.class)
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<DefaultResponse<String>> handlePropertyReferenceException(PropertyReferenceException ex) {
		log.error("Invalid property reference.", ex);
		var message = String.format("Invalid property reference: %s", ex.getMessage());
		return DefaultResponse.onThrow500ErrorResponse(message);

	}
}
