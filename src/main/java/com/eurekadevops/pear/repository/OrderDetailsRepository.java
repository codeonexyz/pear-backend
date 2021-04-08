package com.eurekadevops.pear.repository;

import com.eurekadevops.pear.domain.OrderDetails;
import com.eurekadevops.pear.domain.pk.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {

}
