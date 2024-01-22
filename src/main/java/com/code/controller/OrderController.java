package com.code.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.Entity.Order;
import com.code.Service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contoller")
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/saveorder")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
		Order createOrder = orderService.createOrder(order);
		return new ResponseEntity<Order>(createOrder, HttpStatus.CREATED);
	}

	@PutMapping("/update/{orderId}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long orderId) {
		orderService.updateOrder(orderId, order);
		return new ResponseEntity<Order>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/Fetching/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	

}
