package com.example.demo.bankrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.BankDetails;

public interface Repository extends CrudRepository<BankDetails, Integer>{
	
	//BankDetails findByAccountNumber(String accountNumber);

}
