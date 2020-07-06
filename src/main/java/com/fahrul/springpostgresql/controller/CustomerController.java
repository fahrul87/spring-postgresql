package com.fahrul.springpostgresql.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fahrul.springpostgresql.exception.ResourceNotFoundException;
import com.fahrul.springpostgresql.model.Customer;
import com.fahrul.springpostgresql.model.CustomerUI;
import com.fahrul.springpostgresql.repository.CustomerRepository;



@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	

	
	@PostMapping("/create")
	public String create(@RequestBody Customer customer) {
		repository.save(customer);
		return "Custumer is create";
	}
	

	
	@GetMapping("/getAll")
    public List<Customer> getAllCustomer() {
		
        return repository.findAll();
    }
	
	

	
	@GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Belajar", "id", id));
    }
	
	@RequestMapping("/searchbyfirstname/{firstname}")
	public List<CustomerUI> fetchDataByLastName(@PathVariable String firstname){
		List<Customer> customers= repository.findByFirstName(firstname);
		List<CustomerUI> customerUI = new ArrayList<>();
		
		for(Customer customer:customers) {
			customerUI.add(new CustomerUI(customer.getFirstName(), customer.getLastName()));
		}
		
		return customerUI;
	}
	
	@PutMapping("/update/{id}")
	public Customer  updateCustomer(@PathVariable(value = "id") int id, @Valid @RequestBody Customer customerDetails){
		
		Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

		customer.setFirstName(customerDetails.getFirstName());
		customer.setLastName(customerDetails.getLastName());
		
		
    	

		Customer updatedCustomer= repository.save(customer);
        return updatedCustomer;
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		 repository.deleteById(id);
		return "Customer removed..." + id;
	}
	
}
