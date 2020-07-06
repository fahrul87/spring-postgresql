package com.fahrul.springpostgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fahrul.springpostgresql.repository.CustomerRepository;

@SpringBootApplication
public class SpringPostgresqlApplication {
	
	
	@Autowired
	CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringPostgresqlApplication.class, args);
	}

}
