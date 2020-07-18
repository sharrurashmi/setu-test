package com.setu.payment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifmif.common.regex.Generex;
import com.setu.payment.dao.model.Bill;
import com.setu.payment.dao.model.Customer;
import com.setu.payment.dao.model.Transaction;
import com.setu.payment.dto.model.BaseResponse;
import com.setu.payment.dto.model.BillResponse;
import com.setu.payment.dto.model.ErrorResponse;
import com.setu.payment.dto.model.UpdateRequest;
import com.setu.payment.dto.model.UpdateResponse;
import com.setu.payment.repository.BillRepository;
import com.setu.payment.repository.CustomerRepository;
import com.setu.payment.repository.TransactionRepository;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class PaymentService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	BillRepository billRepo;
	public int getHttpStatus(String errorCode)
	{
	
		switch (errorCode) {
			case ErrorResponse.authError:
			 return 403;
			case ErrorResponse.invalidParam:
			 return 400;
			case ErrorResponse.unHandledError:
				 return 500;
			case ErrorResponse.amountMismatch:
				return 401;
		
			default:
			    return 404;
		}
	}
	public BaseResponse getBill(String mobileNumber) {
		log.info("Mobile number requested:"+mobileNumber);
		Optional<Customer> customerOptional= customerRepository.findCustomerByMobileNumber(mobileNumber);
	    if(!customerOptional.isPresent())
	    	return new ErrorResponse(ErrorResponse.customerNotFound);
	    Customer customer=customerOptional.get();
		Bill bill=billRepo.findBillForCustomer(customer.getId()).get();
		BillResponse response = new BillResponse();
		response.setBill(customer.getCustomerName(), bill.getDueAmount(), bill.getDate(), bill.getReferenceId());
		return response;
	}
	@SuppressWarnings("deprecation")
	public BaseResponse updateResponse(UpdateRequest updateRequest) 
	{
		log.info("Reference id requested in update payemnt "+updateRequest.getRefId());
		Optional<Transaction> transact=transactionRepository.findTransactionByReferenceID(updateRequest.getRefId());
		Transaction transaction;
		if(transact.isPresent())
		{
			if (transact.get().getId().equals(updateRequest.getId())) {
				    log.info("Transaction present in database for transactionId"+updateRequest.getId()+" And refid"+updateRequest.getRefId());
					transaction=transact.get();
					
			}
			else 
				return new ErrorResponse(ErrorResponse.invalidRef);
			
		}
		else {
			log.info("Creating new transaction with id "+updateRequest.getId()+" and refId"+ updateRequest.getRefId());
		transaction=new Transaction();
		transaction.setAmount(Double.parseDouble(updateRequest.getAmountPaid()));
		transaction.setId(updateRequest.getId());
		Optional<Bill> bill=billRepo.findBillByReferenceId(updateRequest.getRefId());
		
		if(bill.isPresent()) {
			Bill billValue=bill.get();
			if(billValue.getDueAmount()!=transaction.getAmount())
				return new ErrorResponse(ErrorResponse.amountMismatch);
			transaction.setBill(billValue);
			
		}
		Generex generex = new Generex("[A-Z]{2}[0-9]{6}");
		transaction.setAckId( generex.random());
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
		try {
			transaction.setDate(df.parse(updateRequest.getDate()));
		} catch (ParseException e) {
			log.error("Error in parsing input date");
		}
		}
		UpdateResponse up = new UpdateResponse();
		if(transaction!=null) {
		   transactionRepository.save(transaction);
		   up.setAckId(transaction.getAckId());
		}
		
		return up;
		
	}
}
