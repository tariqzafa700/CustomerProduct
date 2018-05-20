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
@Table(name="customer")
public class Customer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5547801089920366084L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="customer_id")
    Long id;
    
    @Column(name="customer_name")
    String name;
    
    @Column(name="customer_city")
    String city;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy ="jointKey.customer", cascade=javax.persistence.CascadeType.MERGE)
    @JsonIgnore
    List<CustomerProduct> customerProducts = new ArrayList<>();
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    public void setCustomerProducts(List<CustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }
    
    @Override
    public String toString() {
        return "Customer " + this.getId() + " name: " + this.getName() + " city: " + this.getCity();
    }
    
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof Customer) {
            Customer that = (Customer)other;
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
        val += 31*val+city.hashCode();
        return val;
    }
}
