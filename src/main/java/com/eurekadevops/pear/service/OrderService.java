package com.eurekadevops.pear.service;

import com.eurekadevops.pear.domain.Order;
import com.eurekadevops.pear.domain.OrderDetails;
import com.eurekadevops.pear.domain.Product;
import com.eurekadevops.pear.repository.CustomerRepository;
import com.eurekadevops.pear.repository.OrderDetailsRepository;
import com.eurekadevops.pear.repository.OrderRepository;
import com.eurekadevops.pear.repository.ProductRepository;
import com.eurekadevops.pear.repository.UserRepository;
import com.eurekadevops.pear.service.dto.OrderDto;
import com.eurekadevops.pear.service.mapper.OrderDetailsMapper;
import com.eurekadevops.pear.service.mapper.OrderMapper;
import com.eurekadevops.pear.web.rest.exceptions.BadRequestAlertException;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    private final OrderMapper orderMapper;
    
    private final OrderDetailsMapper orderDetailsMapper;
    
    private final ProductRepository productRepository;
    
    private final UserRepository userRepository;
    
    private final CustomerRepository customerRepository;

    private final OrderDetailsRepository orderDetailsRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class.getName());
    
    public OrderDto save(OrderDto orderDto, String username) {
        Order order = orderMapper.toEntity(orderDto);
        order.setUser(userRepository.findByEmail(username));
        
        if (orderDto.getCustomerId() != null)
            order.setCustomer(customerRepository.getOne(orderDto.getCustomerId()));
        
        orderRepository.save(order);
        
        orderDto.getOrderDetails().forEach(orderDetailsDto -> {
            Product product = productRepository.getOne(orderDetailsDto.getProductId());
            OrderDetails orderDetails = orderDetailsMapper.toEntity(orderDetailsDto);
            
            if (order.getOrderType().equalsIgnoreCase("sell")) {
                if (product.getQuantity() - orderDetails.getQuantity() < 0) throw new BadRequestAlertException("Not enough quantity of product: " + product.getName());
                product.setQuantity(product.getQuantity() - orderDetails.getQuantity());
            } else if (order.getOrderType().equalsIgnoreCase("purchase")) {
                product.setQuantity(product.getQuantity() + orderDetails.getQuantity());
            }
            
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            
            orderDetailsRepository.save(orderDetails);
        });
        
        return orderMapper.toDTO(order);
    }
    
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }
    
    public Optional<OrderDto> findById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toDTO);
    }
    
    public void delete(Long id) {
        Optional<Order> result = orderRepository.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot delete: Order with id '{}' not found", id));
        orderRepository.delete(result.get());
    }

}
