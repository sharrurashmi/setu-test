package com.setu.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.setu.payment.dto.model.ErrorResponse;
@ControllerAdvice
@RestController
public class ExceptionController  extends ResponseEntityExceptionHandler {
	
	
	  @ExceptionHandler(AuthenticationException.class)
	  public final ResponseEntity<ErrorResponse> handleNotFoundException(AuthenticationException ex, WebRequest request) {
	    ErrorResponse exceptionResponse = new ErrorResponse(ErrorResponse.authError);
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	  
	}
}
