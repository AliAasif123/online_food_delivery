package com.code.Service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.code.Entity.Delivery;
import com.code.Entity.Order;
import com.code.Exception.DeliveryException;
import com.code.Exception.OrderException;
import com.code.Exception.ResourceNotFoundException;
import com.code.Service.DeliveryService;
import com.code.repositories.DeliveryRepo;
import com.code.repositories.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

	private final DeliveryRepo deliveryRepo;
	private final OrderRepository orderRepository;
	private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

	@Override
	public Delivery createDelivery(Delivery delivery) {
		logger.info("Creating delivery for order ID: {}", delivery.getOrder().getId());

		Order order = new Order();

		Order existingOrder = orderRepository.findById(delivery.getOrder().getId())
				.orElseThrow(() -> new OrderException(" order_id not found"));

		if (existingOrder != null) {
			order.setId(delivery.getOrder().getId());
			order.setOrderDate(delivery.getOrder().getOrderDate());
			order.setTotalAmount(delivery.getOrder().getTotalAmount());
			order.setUser(delivery.getOrder().getUser());
		} else {
			logger.error("Order not found for the provided ID: {}", delivery.getOrder().getId());

			throw new OrderException("Order not found for the provided ID: " + delivery.getOrder().getId());
		}
		Order save = null;
		try {
			save = orderRepository.save(order);
			delivery.setOrder(save);
			Delivery savedDelivery = deliveryRepo.save(delivery);
			logger.info("Delivery created successfully with ID: {}", savedDelivery.getId());
			return savedDelivery;

		} catch (Exception e) {
			logger.error("Unable to save delivery data: {}", e.getMessage());
			throw new DeliveryException("ubable to save the data due to :" + e.getMessage());
		}

	}

	@Override
	public Delivery getDeliveryById(Long deliveryId) {
		try {
			logger.info("Fetching delivery details for ID: {}", deliveryId);

			Delivery delivery = this.deliveryRepo.findById(deliveryId).orElseThrow(() -> {
				logger.error("Delivery not found for ID: {}", deliveryId);
				return new ResourceNotFoundException("Delivery", "deliveryId", deliveryId);
			});

			logger.info("Delivery details fetched successfully for ID: {}", deliveryId);

			return delivery;
		} catch (Exception e) {
			logger.error("Error while fetching delivery details: {}", e.getMessage());
			throw e; // Propagate the exception or handle it as needed
		}
	}

}