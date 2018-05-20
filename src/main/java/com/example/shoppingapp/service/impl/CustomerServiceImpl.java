package com.example.shoppingapp.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppingapp.model.Customer;
import com.example.shoppingapp.model.CustomerProduct;
import com.example.shoppingapp.model.CustomerProductMapping;
import com.example.shoppingapp.model.Product;
import com.example.shoppingapp.repository.CustomerRepository;
import com.example.shoppingapp.repository.ProductRepository;
import com.example.shoppingapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
    public Customer addProductToCustomer(Long productId, Long custId) {
        Product product = productRepository.findById(productId).get();
        Customer customer = customerRepository.findById(custId).get();
        Set<CustomerProduct> cps = new HashSet<>();
        CustomerProduct cp = new CustomerProduct();
        cp.setCustomer(customer);
        cp.setProduct(product);
        cps.add(cp);
        cps.addAll(customer.getCustomerProducts());
        List<CustomerProduct> custProds = new ArrayList<>(cps);
        customer.setCustomerProducts(custProds);
        product.setCustomerProducts(custProds);
        productRepository.saveAndFlush(product);
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer addMultipleProductsToCustomer(List<CustomerProductMapping> custProds) {
        return null;
    }

}
