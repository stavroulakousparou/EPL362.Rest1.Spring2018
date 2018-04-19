package com.javasampleapproach.restdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasampleapproach.restdata.model.Customer;
import com.javasampleapproach.restdata.model.User;
import com.javasampleapproach.restdata.repository.CustomerRepository;
import com.javasampleapproach.restdata.repository.UserRepository;

@SpringBootApplication
public class SpringRestDataMySqlApplication implements CommandLineRunner{

	@Autowired
	CustomerRepository customerRepository;
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestDataMySqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer("Jack", "Smith"));
		//userRepository.save(new User("123456"));
		
	}
}
