package com.example.shoppingapp.model;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="customer_product")
@AssociationOverrides({@AssociationOverride(name="jointKey.product", joinColumns=@JoinColumn(name="product_id")),
    @AssociationOverride(name="jointKey.customer", joinColumns=@JoinColumn(name="customer_id"))
})
public class CustomerProduct {

    @EmbeddedId
    private CustomerProductId jointKey = new CustomerProductId();
    
    public CustomerProductId getJointKey() {
        return jointKey;
    }

    public void setJointKey(CustomerProductId jointKey) {
        this.jointKey = jointKey;
    }
    
    @Transient
    public Product getProduct() {
        return getJointKey().getProduct();
    }

    public void setProduct(Product product) {
        getJointKey().setProduct(product);
    }
    
    @Transient
    public Customer getCustomer() {
        return getJointKey().getCustomer();
    }

    public void setCustomer(Customer customer) {
        getJointKey().setCustomer(customer);
    }
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof CustomerProduct) {
            CustomerProduct that = (CustomerProduct)other;
            if(this.getJointKey().equals(that.getJointKey())) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getJointKey().hashCode();
    }
}
