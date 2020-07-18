package com.setu.payment.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResponse extends BaseResponse {
 
	Data data;
	@Getter
	@Setter
	class Data
	{
		String ackId;
	}
	public void setAckId(String ackId)
	{
		data=new Data();
		data.setAckId(ackId);
	}
	
	public UpdateResponse()
	{
		super("SUCCESS");
	}
	
}
