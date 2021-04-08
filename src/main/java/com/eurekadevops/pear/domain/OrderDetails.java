package com.eurekadevops.pear.domain;

import com.eurekadevops.pear.domain.pk.OrderDetailsId;
import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details_tbl")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.order", joinColumns = @JoinColumn(name = "order_id")),
    @AssociationOverride(name = "primaryKey.product", joinColumns = @JoinColumn(name = "product_id"))
})
public class OrderDetails implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private OrderDetailsId primaryKey = new OrderDetailsId();
    
    private Double unitPrice;
    private Integer size;
    private Integer quantity;
    private Integer discount;
    private Double total;
    
    @EmbeddedId
    public OrderDetailsId getPrimaryKey() {
        return this.primaryKey;
    }
    
    @Transient
    public Order getOrder() {
        return this.getPrimaryKey().getOrder();
    }
    
    public void setOrder(Order order) {
        this.getPrimaryKey().setOrder(order);
    }
    
    @Transient
    public Product getProduct() {
        return this.getPrimaryKey().getProduct();
    }
    
    public void setProduct(Product product) {
        this.getPrimaryKey().setProduct(product);
    }

}
