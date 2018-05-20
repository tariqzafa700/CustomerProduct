package com.example.shoppingapp.service;

import java.util.List;

import com.example.shoppingapp.model.Customer;
import com.example.shoppingapp.model.CustomerProduct;
import com.example.shoppingapp.model.CustomerProductMapping;

public interface CustomerService {
    public Customer addProductToCustomer(Long productId, Long custId);

    public Customer addMultipleProductsToCustomer(List<CustomerProductMapping> custProds);
}
