package com.setu.payment.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {

	String refId;
	Transaction transaction;
	@Getter
	@Setter
	static class Transaction
	{
		String amountPaid;
		String date;
		String id;
		
	}
	public String getAmountPaid()
	{
		return this.getTransaction().getAmountPaid();
	}
	public String getDate()
	{
		return this.getTransaction().getDate();
	}
	public String getId()
	{
		return this.getTransaction().getId();
	}
	

}
