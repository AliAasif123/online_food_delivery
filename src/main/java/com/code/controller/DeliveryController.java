package com.code.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.Entity.Delivery;
import com.code.Service.DeliveryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery/contoller")
public class DeliveryController {

	private final DeliveryService deliveryService;

	@PostMapping("/savedelivery")
	public ResponseEntity<Delivery> saveOrder(@RequestBody Delivery delivery) {
		Delivery createDelivery = deliveryService.createDelivery(delivery);
		return new ResponseEntity<>(createDelivery, HttpStatus.CREATED);
	}

	@GetMapping("/Getting/{id}")
	public ResponseEntity<Delivery> getById(@PathVariable Long id) {
		Delivery deliveryById = this.deliveryService.getDeliveryById(id);
		if (deliveryById != null) {
			return ResponseEntity.ok(deliveryById);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
