package com.example.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingapp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerCustomRepository {

}
