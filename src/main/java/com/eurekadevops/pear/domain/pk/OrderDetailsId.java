package com.eurekadevops.pear.domain.pk;

import com.eurekadevops.pear.domain.Order;
import com.eurekadevops.pear.domain.Product;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetailsId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Order order;
    
    private Product product;
    
    @ManyToOne(cascade = CascadeType.ALL)
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetailsId{" + "order=" + order + ", product=" + product + '}';
    }

}
