package com.example.shoppingapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapp.model.Customer;
import com.example.shoppingapp.model.CustomerProduct;
import com.example.shoppingapp.model.CustomerProductId;
import com.example.shoppingapp.model.CustomerProductMapping;
import com.example.shoppingapp.repository.CustomerRepository;
import com.example.shoppingapp.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    CustomerService customerService;
    
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer c = customerRepository.saveAndFlush(customer);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
    
    @ResponseBody
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomersById(@RequestParam String ids) {
        List<String> custIds = new ArrayList<String>(Arrays.asList(ids.split(",")));
        List<Long> lIds = custIds.stream().map(id -> Long.parseLong(id)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.getCustomersByIds(lIds));
    }
    
    @ResponseBody
    @RequestMapping(value="product/{productId}/{customerId}",method=RequestMethod.PUT)
    public ResponseEntity<Customer> addProductToCustomer(@PathVariable ("productId") Long productId, 
                                                 @PathVariable ("customerId") Long customerId){
        Customer c = customerService.addProductToCustomer(productId, customerId);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
    
    @ResponseBody
    @RequestMapping(value="product",method=RequestMethod.PUT)
    public ResponseEntity<Customer> addMultipleProductsToCustomer(@RequestBody List<CustomerProductMapping> custProds) {
        Customer c = customerService.addMultipleProductsToCustomer(custProds);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
}
