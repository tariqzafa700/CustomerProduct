package com.example.shoppingapp.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.shoppingapp.model.Customer;
import com.example.shoppingapp.model.CustomerProduct;
import com.example.shoppingapp.model.CustomerProductMapping;
import com.example.shoppingapp.repository.CustomerCustomRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getCustomersByIds(List<Long> custIds) {
        Query query = entityManager.
                createNativeQuery("select cust.* from customer cust where cust.customer_id in (?1)", Customer.class);
        query.setParameter(1, custIds);
        List<Customer> res = (List<Customer>) query.getResultList().stream().map(c -> (Customer)c).collect(Collectors.toList());
        return res;
    }

    @Override
    public Boolean addMultipleProductsCustomers(List<CustomerProductMapping> custProducts) {
        Query query = entityManager.
                createNativeQuery("select cust.* from customer cust where cust.customer_id in (?1)", Customer.class);
        return false;
    }
}
