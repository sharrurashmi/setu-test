package com.setu.payment.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends BaseResponse {
	
		public static final String authError="auth-error";
		public static final String invalidParam="invalid-api-parameters";
		public static final String customerNotFound="customer-not-found";
		public static final String invalidRef="invalid-ref-id";
		public static final String amountMismatch="amount-mismatch";
		public static final String pathNotFound="path-not-found";
		public static final String unHandledError="unhandled-error";
	
	String errorCode;
	public ErrorResponse (String errorCode)
	{
		super("ERROR");
		this.errorCode=errorCode;
		
		
	}

}
