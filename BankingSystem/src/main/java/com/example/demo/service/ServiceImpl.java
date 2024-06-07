package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.bankrepository.Repository;
import com.example.demo.model.BankDetails;


@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

	@Autowired
	private Repository repo;
	
	@Override
	public BankDetails createAccount(BankDetails acc) {
		
		repo.save(acc);
		
		return null;
	}
	
	@Override
	public List<BankDetails> fetchAllAccounts() {
		
		List<BankDetails> bank = (List<BankDetails>) repo.findAll();
		return bank;
	}

	@Override
	public BankDetails getAccountByID(int id) {
		
		Optional<BankDetails> bank =  repo.findById(id);
		if(bank.isPresent() )
		{
			return bank.get();
		}
		return null;
		
		
	}

	@Override
	public String deleteAccount(int id) {
		
		if(repo.findById(id).isPresent())
		{
			repo.deleteById(id);
			return "Successfully deleted " ;
		}
		
		else
			return "No such Account in the database";
		
		
	}

	@Override
	public BankDetails deposit(int id, double amount) {
		
		Optional<BankDetails> bank =	repo.findById(id);
		
			if(bank.isEmpty())
			{
				throw new RuntimeException("Account with this ID is not present.");
			}
				BankDetails bankPresent = bank.get();
				
			double totalBalance = bankPresent.getBalance() + amount ;
			
			bankPresent.setBalance(totalBalance);
			
			repo.save(bankPresent);

		return bankPresent;
	}

	@Override
	public BankDetails withdrawl(int id, double amount) {
		
		Optional<BankDetails> bank =	repo.findById(id);
		
		if(bank.isEmpty())
		{
			throw new RuntimeException("Account with this ID is not present.");
		}
			BankDetails bankPresent = bank.get();
			
		double newBalance = bankPresent.getBalance() - amount ;
		
		if(newBalance < 0)
		{
		  throw new RuntimeException("Not enough balance"); 
			
		}
		else {
		bankPresent.setBalance(newBalance);
		
		repo.save(bankPresent);
		}
		
		
		return bankPresent;
	}


}
