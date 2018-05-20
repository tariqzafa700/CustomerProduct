package com.example.shoppingapp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
public class CustomerProductId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7184569047845824151L;

    @ManyToOne
    //@JsonIgnoreProperties("customer")
    Customer customer;
    
    @ManyToOne
    //@JsonIgnoreProperties("product")
    Product product;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof CustomerProductId) {
            CustomerProductId that = (CustomerProductId)other;
            if(this.getProduct().equals(that.getProduct()) && this.getCustomer().equals(that.getCustomer())) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int val = getProduct().hashCode();
        val += 31*val+getCustomer().hashCode();
        return val;
    }
}
