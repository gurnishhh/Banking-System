package com.example.demo.service;
import java.util.List;

import com.example.demo.model.BankDetails;

@org.springframework.stereotype.Service
public interface Service {
	
	BankDetails createAccount(BankDetails acc);
	
	List<BankDetails> fetchAllAccounts();
	
	BankDetails getAccountByID (int id);

	String deleteAccount (int id);
	
	BankDetails deposit(int id , double amount);
	
	BankDetails withdrawl(int id, double amount);
}
