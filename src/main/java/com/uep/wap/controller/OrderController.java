package com.uep.wap.controller;

import com.uep.wap.dto.OrderDTO;
import com.uep.wap.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        var orders = StreamSupport.stream(orderService.getAllOrders().spliterator(), false).collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order added!");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable long id) {
        OrderDTO orderDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order deleted!");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok("Order updated!");
    }
}
