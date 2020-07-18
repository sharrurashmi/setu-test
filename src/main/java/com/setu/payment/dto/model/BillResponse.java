package com.setu.payment.dto.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillResponse extends BaseResponse{

	/*
	 * {
    "status": "SUCCESS",
    "data": {
        
        "customerName": "Ashok Kumar",
        "dueAmount": "2000",
        "dueDate": "2020-06-05",
        "refID": "AX0812878"
    }
}
	 */
	
	
	Data data;
	public BillResponse()
	{
		super("SUCCESS");
	}
	@Getter
	@Setter
	class Data
	{
		String customerName;
		String dueAmount;
		String dueData;
		String refId;
	}
	
	public void setBill(String customerName,double due,Date date,String refId)
	{
		this.data=new Data();
		this.data.setCustomerName(customerName);
		this.data.setDueAmount(String.valueOf(due));
		this.data.setDueData(date.toString());
		this.data.setRefId(refId);
	}
}
