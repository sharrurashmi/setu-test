package com.setu.payment.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.setu.payment.dto.model.ErrorResponse;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class AuthFilter implements Filter {
	
	String apikey="545bbcfb-13aa-4f18-a2b0-506d9aa4fe92";
	    @Override
	    public void doFilter(
	      ServletRequest request, 
	      ServletResponse response, 
	      FilterChain chain) throws IOException, ServletException {
	 
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        log.info("api key "+apikey);
	        log.info(
	          "Logging Request  {} : {}", req.getMethod(), 
	          req.getRequestURI());
	        if(req.getHeader("X-API-KEY")!=null &&req.getHeader("X-API-KEY").equals(apikey))
	        {
	        	chain.doFilter(request, response);
	        }
	        else 
	        {
	        	ObjectMapper mapper = new ObjectMapper();
	        	log.error("No header present"+apikey);
	        	ErrorResponse err = new ErrorResponse(ErrorResponse.authError);
	        	log.error(mapper.writeValueAsString(err));
	        	res.sendError(403,mapper.writeValueAsString(err));
	        	
	        }
	        log.info(
	          "Logging Response :{}", 
	          res.getContentType());
	    }
	 
	    // other methods
	}


