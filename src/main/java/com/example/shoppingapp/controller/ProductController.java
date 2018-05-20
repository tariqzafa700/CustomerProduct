package com.example.shoppingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapp.model.Product;
import com.example.shoppingapp.repository.ProductRepository;
import com.example.shoppingapp.service.ProductService;

@RequestMapping("/products")
@RestController
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    //ProductService productService;
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        System.out.println("product received" + product.getName() + " " + product.getQuantity());
        Product p = productRepository.saveAndFlush(product);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
}
