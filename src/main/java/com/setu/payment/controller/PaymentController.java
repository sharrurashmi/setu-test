package com.setu.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.setu.payment.dto.model.BaseResponse;
import com.setu.payment.dto.model.BillRequest;
import com.setu.payment.dto.model.BillResponse;
import com.setu.payment.dto.model.ErrorResponse;
import com.setu.payment.dto.model.UpdateRequest;
import com.setu.payment.service.PaymentService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PaymentController {
	
	@Autowired 
	PaymentService paymentService;
	@PostMapping("/fetch-bill")
	public ResponseEntity<?> greeting(@RequestBody BillRequest mobileNumber) {
		
		BaseResponse bill =paymentService.getBill(mobileNumber.getMobileNumber());
		if (bill instanceof  ErrorResponse )
		{
			int status=paymentService.getHttpStatus(((ErrorResponse) bill).getErrorCode());
			return ResponseEntity.status(status).body(bill);
		}
		else 
			return ResponseEntity.ok(bill);
	}
	@PostMapping("/payment-update")
	public ResponseEntity<?> updatePaymnet(@RequestBody UpdateRequest updateRequest) {
		
		log.info("update ref"+updateRequest.getRefId());
		BaseResponse update= paymentService.updateResponse(updateRequest);
		if (update instanceof  ErrorResponse )
		{
			int status=paymentService.getHttpStatus(((ErrorResponse) update).getErrorCode());
			return ResponseEntity.status(status).body(update);
		}
		else 
			return ResponseEntity.ok(update);
		
		
		
		
	}
}


