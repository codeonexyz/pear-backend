package com.eurekadevops.pear.web.rest;

import com.eurekadevops.pear.service.OrderService;
import com.eurekadevops.pear.service.dto.OrderDto;
import com.eurekadevops.pear.web.rest.exceptions.BadRequestAlertException;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import com.eurekadevops.pear.web.rest.exceptions.ValidationException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderResource {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDTO, BindingResult bindingResult, Principal principal) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (orderDTO.getId() != null) throw new BadRequestAlertException("id already exists");
        OrderDto result = orderService.save(orderDTO, principal.getName());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
    @PutMapping("/orders")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody @Valid OrderDto orderDTO, BindingResult bindingResult, Principal principal) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (orderDTO.getId() == null) throw new BadRequestAlertException("invalid id");
        OrderDto result = orderService.save(orderDTO, principal.getName());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> result = orderService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable(name = "id") Long id) {
        Optional<OrderDto> result = orderService.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot fetch object: Order with id '{id}' not found", id));
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }
    
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
