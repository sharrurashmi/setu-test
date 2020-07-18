package com.setu.payment.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@Column(name="id")
	String id;
	@Column(name="amount")
	double amount;
	@Column(name="date")
	Date date;
	@Column(name="ack_id")
	String ackId;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="referenceid")
	private Bill bill;
	

	
}
