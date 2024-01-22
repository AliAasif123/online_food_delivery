package com.code.Service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.Entity.Order;
import com.code.Entity.User;
import com.code.Exception.OrderException;
import com.code.Exception.ResourceNotFoundException;
import com.code.Service.OrderService;
import com.code.repositories.OrderRepository;
import com.code.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderImpl implements OrderService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	private static final Logger logger = LoggerFactory.getLogger(OrderImpl.class);

	public Order createOrder(Order orderRequest) {
		try {
			logger.info("Creating order for user ID: {}", orderRequest.getUser().getId());

			Order order = new Order();
			User user = new User();
			if (orderRequest != null) {
				User existingUser = userRepository.findById(orderRequest.getUser().getId())
						.orElseThrow(() -> new OrderException("order_id  not found"));

				if (existingUser == null) {
					User newUser = new User();
					newUser.setId(orderRequest.getUser().getId());
					newUser.setUsername(orderRequest.getUser().getUsername());
					newUser.setPassword(orderRequest.getUser().getPassword());
					newUser.setRole(orderRequest.getUser().getRole());
					user = userRepository.save(newUser);
				} else {
					user = existingUser;
				}

				order.setUser(user);
			}

			order.setOrderDate(orderRequest.getOrderDate());
			order.setTotalAmount(orderRequest.getTotalAmount());

			Order savedOrder = orderRepository.save(order);
			logger.info("Order created successfully with ID: {}", savedOrder.getId());
			return savedOrder;

		} catch (Exception e) {
			logger.error("Error creating order: {}", e.getMessage());
			throw new OrderException("Error creating order");
		}
	}

	@Override
	public void updateOrder(Long orderId, Order updatedOrderRequest) {
		try {
			if (orderId == null || updatedOrderRequest == null) {
				throw new OrderException("Invalid input for order update.");
			}
			logger.info("Updating order with ID: {}", orderId);

			Order existingOrder = orderRepository.findById(orderId)
					.orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));

			// Update the existing order with new values from the updatedOrderRequest
			existingOrder.setOrderDate(updatedOrderRequest.getOrderDate());
			existingOrder.setTotalAmount(updatedOrderRequest.getTotalAmount());

			// Handle user information
			User existingUser = existingOrder.getUser();
			User updatedUser = updatedOrderRequest.getUser();

			if (existingUser == null) {
				// If there was no user associated with the existing order, create a new user
				User newUser = new User();
				newUser.setUsername(updatedUser.getUsername());
				newUser.setPassword(updatedUser.getPassword());
				newUser.setRole(updatedUser.getRole());
				existingOrder.setUser(userRepository.save(newUser));
			} else {
				// If there was an existing user, update its information
				existingUser.setUsername(updatedUser.getUsername());
				existingUser.setPassword(updatedUser.getPassword());
				existingUser.setRole(updatedUser.getRole());
				existingOrder.setUser(existingUser);
			}

			orderRepository.save(existingOrder);
			logger.info("Order with ID {} updated successfully", orderId);

		} catch (Exception e) {
			logger.error("Error updating order with ID {}: {}", orderId, e.getMessage());
			throw new OrderException("Error updating order");
		}
	}

	@Override
	public Order getOrderById(Long orderId) {
		try {
			logger.info("Fetching order details for ID: {}", orderId);

			Order order = orderRepository.findById(orderId).orElseThrow(() -> {
				logger.error("Order not found for ID: {}", orderId);
				return new ResourceNotFoundException("Order", "orderId", orderId);
			});

			logger.info("Order details fetched successfully for ID: {}", orderId);

			return order;
		} catch (Exception e) {
			logger.error("Error while fetching order details: {}", e.getMessage());
			throw e;
		}
	}
}
