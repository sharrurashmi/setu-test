package com.setu.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.setu.payment.dao.model.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
	

	@Query(value = "SELECT * from customer where mobile_number=?1" , nativeQuery = true)
	Optional<Customer> findCustomerByMobileNumber(String mobileNumber);

}
