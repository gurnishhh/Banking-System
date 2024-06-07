package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bankrepository.Repository;
import com.example.demo.model.BankDetails;
import com.example.demo.service.Service;

@RestController
public class MainController {
	
	
	@Autowired
	 Repository repo;
	
		/*
		 * @Autowired BankDetails model;
		 */
	
	@Autowired
	private Service service;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "Welcome to Banking System";
	}
	
	@PostMapping("/insert")
	public String insert(@RequestBody BankDetails bank) {
		
		service.createAccount(bank);
		return "Successfully inserted";
	}
	
	@GetMapping("/get")
	public List<BankDetails> getData(){
		
		System.out.println( repo.findAll() );		
		return service.fetchAllAccounts();
	}
	
	@GetMapping("/get/{id}")
	public Optional<BankDetails> getDataByID( @PathVariable("id") int id ){
		
		return Optional.ofNullable(service.getAccountByID(id));
	}

	@DeleteMapping("/delete/{id}")
	public String deleteData( @PathVariable int id) {
		
		return service.deleteAccount(id);	
	}
	
	//------------
	
	@GetMapping("/CheckBalance/{id}")
	public Double checkBalance(@PathVariable int id)
	{ 	
		
		
		Optional<BankDetails> n = repo.findById(id);
		return n.get().getBalance();
		
	}
	
	@PostMapping("/deposit/{id}/{amount}")
	public BankDetails depositBalance(@PathVariable int id, @PathVariable double amount)
	{	
		BankDetails bank = service.deposit(id, amount);
		return bank;
		
	}
	
	@PutMapping("/withdrawl/{id}/{amount}")
	public BankDetails withdrawlBalance(@PathVariable int id, @PathVariable double amount)
	{
		
		BankDetails bank = service.withdrawl(id, amount);
		
		
		return bank;
	}
	
	

}
