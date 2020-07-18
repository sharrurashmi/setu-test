package com.setu.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.setu.payment.dao.model.Bill;

public interface BillRepository extends CrudRepository<Bill,Long> {

	@Query(value = "SELECT * from bill where customerid=?1" , nativeQuery = true)
	Optional<Bill> findBillForCustomer(int customerId);
	@Query(value = "SELECT * from bill where referenceid=?1" , nativeQuery = true)
	Optional<Bill> findBillByReferenceId(String referenceId);
}
