package com.setu.payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.setu.payment.dto.model.ErrorResponse;

public class AuthenticationHandler  extends SimpleUrlAuthenticationFailureHandler  {
	 
	   ObjectMapper mapper= new ObjectMapper();
	    @Override
	    public void onAuthenticationFailure(
	      HttpServletRequest request,
	      HttpServletResponse response,
	      AuthenticationException exception) 
	      throws IOException, ServletException {
	 
	       response.setStatus(403);
	       ErrorResponse error =new ErrorResponse(ErrorResponse.authError);
	 
	        response.getOutputStream()
	          .println(mapper.writeValueAsString(error));
	    }
	}

