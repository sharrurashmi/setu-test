package com.setu.payment.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Bill")
@Getter
@Setter
public class Bill {
	/*
	 * "customerName": "Ashok Kumar",
        "dueAmount": "2000",
        "dueDate": "2020-06-05",
        "refID": "AX0812878"
        dueamount,date,customerid,referenceid
	 */
	private static final long serialVersionUID = -2343243243142432341L;
	@Column(name = "dueamount")
	private double dueAmount;
	@Column(name="date")
	private Date date;
	@Column(name="customerid")
	private int customerId;
	@Id
	@Column(name="referenceid")
	private String referenceId;
	
	
	
	

}
