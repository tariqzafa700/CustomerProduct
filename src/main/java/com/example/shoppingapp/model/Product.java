package com.example.shoppingapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product")
public class Product implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4106474171899687669L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="product_id")
    Long id;

    @Column(name="product_name")
    String name;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy ="jointKey.product", cascade=javax.persistence.CascadeType.MERGE)
    @JsonIgnore
    List<CustomerProduct> customerProducts = new ArrayList<>();
    
    @Column(name="product_quantity")
    Integer quantity;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    public void setCustomerProducts(List<CustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Product " + this.getId() + " name: " + this.getName() + " city: " + this.getQuantity();
    }
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof Product) {
            Product that = (Product)other;
            if(this.name.equals(that.name) && this.id.equals(that.id)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int val = name.hashCode();
        val += 31*val+quantity;
        return val;
    }
}
