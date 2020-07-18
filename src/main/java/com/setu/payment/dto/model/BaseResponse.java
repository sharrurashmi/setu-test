package com.setu.payment.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	public BaseResponse(String string) {
		this.status=string;
	}

	protected String status;

}
