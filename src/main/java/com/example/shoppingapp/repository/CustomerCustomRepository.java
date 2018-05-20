package com.example.shoppingapp.repository;

import java.util.List;


import com.example.shoppingapp.model.Customer;
import com.example.shoppingapp.model.CustomerProduct;
import com.example.shoppingapp.model.CustomerProductMapping;

public interface CustomerCustomRepository {
    List<Customer> getCustomersByIds(List<Long> custIds);
    
    Boolean addMultipleProductsCustomers(List<CustomerProductMapping> custProducts);
}
