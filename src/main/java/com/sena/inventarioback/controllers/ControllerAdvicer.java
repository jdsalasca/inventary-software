package com.sena.inventarioback.controllers;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sena.inventarioback.utils.response.DefaultResponse;

 @ControllerAdvice
 public class ControllerAdvicer {


	    @ExceptionHandler(AccountNotFoundException.class)
	    @ResponseBody
	    public ResponseEntity<DefaultResponse<String>> handleAccountNotFoundException(AccountNotFoundException e) {
	        var message = e.getLocalizedMessage();
	        return DefaultResponse.onThrow400ResponseTypeInfo(message);
	    }
  }
