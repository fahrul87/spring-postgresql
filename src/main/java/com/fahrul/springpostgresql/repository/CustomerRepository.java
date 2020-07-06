package com.fahrul.springpostgresql.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.fahrul.springpostgresql.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	List<Customer> findByFirstName(String name);
	List<Customer> findAll();

}
