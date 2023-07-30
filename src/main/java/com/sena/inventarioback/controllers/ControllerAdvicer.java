package com.sena.inventarioback.controllers;

import javax.security.auth.login.AccountNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.sena.inventarioback.utils.response.DefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

 @ControllerAdvice 
 public class ControllerAdvicer {
  

	    @ExceptionHandler(AccountNotFoundException.class)
	    @ResponseBody
	    public ResponseEntity<DefaultResponse<String>> handleAccountNotFoundException(AccountNotFoundException e) {
	        String message = e.getLocalizedMessage();
	        return DefaultResponse.onThrow400ResponseTypeInfo(message);
	    }
  }
 