package com.sena.inventarioback.controllers;

 
 import lombok.extern.slf4j.Slf4j;
 
 import java.util.Objects;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessResourceFailureException;
 import org.springframework.dao.DataIntegrityViolationException;
 import org.springframework.http.ResponseEntity;
 import org.springframework.http.converter.HttpMessageNotReadableException;
 import org.springframework.web.HttpRequestMethodNotSupportedException;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.RestControllerAdvice;
 import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
 
 @RestControllerAdvice
 @Slf4j
 public class ControllerAdvicer {
 
 	private static final String FORAIGN_KEY_ERROR_LABEL = "Error en la llave foranea";
 
 	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
 	public ResponseEntity<DefaultResponse<String>> handleMethodArgumentTypeMismatch(
 			MethodArgumentTypeMismatchException ex) {
 		String paramName = ex.getParameter().getParameterName();
 		String value = Objects.requireNonNullElseGet(ex.getValue(), Object::new).toString();
 		String targetType = Objects.requireNonNullElse(ex.getRequiredType(), Object.class).getSimpleName();
 
 		String errorMessage = "Failed to convert value '" + value + "' to required type '" + targetType
 				+ "' for parameter '" + paramName + "'";
 
 		return DefaultResponse.onThrow400ResponseTypeInfo(errorMessage);
 	}
 
 	@ExceptionHandler(HttpMessageNotReadableException.class)
 	public ResponseEntity<DefaultResponse<String>> handleHttpMessageNotReadableException(
 			HttpMessageNotReadableException ex) {
 		return DefaultResponse.onThrow400ResponseTypeInfo(ex.getMessage());
 	}
 
 	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
 	public ResponseEntity<DefaultResponse<String>> handleHttpRequestMethodNotSupportedException(
 			HttpRequestMethodNotSupportedException ex) {
 		return DefaultResponse.onThrow400ResponseTypeInfo(ex.getMessage());
 	}
 
 
 	@ExceptionHandler(DataAccessResourceFailureException.class)
 	public ResponseEntity<DefaultResponse<String>> handleDataAccessResourceFailureException(
 			DataAccessResourceFailureException ex) {
 		return DefaultResponse.onThrow400ResponseTypeInfo(ex.getMostSpecificCause().getLocalizedMessage());
 	}
 
 	@ExceptionHandler(DataIntegrityViolationException.class)
 	public ResponseEntity<DefaultResponse<String>> handleDataIntegrityViolationException(
 			DataIntegrityViolationException ex) {
 		String errorMessage = getForeignKeyConstraintErrorMessage(ex);
 		log.info("error creating entity because of {} at line {}, at class {}", ex.getLocalizedMessage(),
 				getClassTrace(ex).getLineNumber(), getClassTrace(ex).getClassName());
 		return DefaultResponse.onThrow400ResponseTypeInfo(errorMessage);
 	}
 
 	@ExceptionHandler(ConstraintViolationException.class)
 	public ResponseEntity<DefaultResponse<String>> handleConstraintViolationException(ConstraintViolationException ex) {
 		String errorMessage = getConstraintViolationErrorMessage(ex);
 		return DefaultResponse.onThrow400ResponseTypeError(errorMessage);
 	}
 
 	@ExceptionHandler(EntityNotFoundException.class)
 	public ResponseEntity<DefaultResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
 		return DefaultResponse.onThrow400ResponseTypeInfo(ex.getLocalizedMessage());
 	}
 
 	@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
     public ResponseEntity<DefaultResponse<String>> handleEntityNotFoundException(javax.persistence.EntityNotFoundException ex) {
 		return DefaultResponse.onThrow400ResponseTypeInfo(ex.getLocalizedMessage());
 	}
  
 	@ExceptionHandler(Exception.class)
 	public ResponseEntity<DefaultResponse<String>> handleException(Exception ex) {
 		log.info("IMPORTANT unhandled exception {}", ex.getLocalizedMessage());
 		return DefaultResponse.onThrow500ErrorResponse(ex.getLocalizedMessage());
 	}
 
 	private String getConstraintViolationErrorMessage(ConstraintViolationException e) {
 		String errorMessage = e.getCause().getMessage();
 		int startIndex = errorMessage.indexOf("FOREIGN KEY");
 		int endIndex = errorMessage.indexOf("REFERENCES");
 		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
 			String constraintInfo = errorMessage.substring(startIndex, endIndex);
 			return FORAIGN_KEY_ERROR_LABEL + " : " + constraintInfo;
 		}
 		return FORAIGN_KEY_ERROR_LABEL;
 	}
 	
 
//     @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
//     public ResponseEntity<DefaultResponse<String>> handleConstraintViolationExceptionSpring(ConstraintViolationException ex) {
//         return DefaultResponse.onThrow400ResponseTypeError(ex.getLocalizedMessage());
//     }
// 
 	private String getForeignKeyConstraintErrorMessage(DataIntegrityViolationException e) {
 		String errorMessage = e.getMostSpecificCause().getMessage();
 		int startIndex = errorMessage.indexOf("FOREIGN KEY");
 		int endIndex = errorMessage.indexOf("REFERENCES");
 		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
 			String constraintInfo = errorMessage.substring(startIndex, endIndex);
 			return FORAIGN_KEY_ERROR_LABEL + " : " + constraintInfo;
 		}
 		return getForeignKeyConstraintErrorMessageII(e);
 	}
 
 	private String getForeignKeyConstraintErrorMessageII(DataIntegrityViolationException e) {
 		String errorMessage = e.getMostSpecificCause().getMessage();
 		int startIndex = errorMessage.indexOf("Duplicate entry");
 		int removeLabel = "Duplicate entry".length();
 		int endIndex = errorMessage.indexOf("for key");
 		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
 			String duplicateEntryInfo = errorMessage.substring(startIndex + removeLabel, endIndex);
 			return "Duplicidad en llave: " + duplicateEntryInfo;
 		}
 
 		return "Hemos detectado duplicidad";
 	}
 
 	private StackTraceElement getClassTrace(Exception e) {
 		StackTraceElement[] stackTrace = e.getStackTrace();
 		return stackTrace[0];
 
 	}
  
 }