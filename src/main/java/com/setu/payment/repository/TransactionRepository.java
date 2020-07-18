package com.setu.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.setu.payment.dao.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {

	@Query(value = "SELECT * from transaction where referenceid=?1" , nativeQuery = true)
	Optional<Transaction> findTransactionByReferenceID(String refId);
}
