package com.code.Service;

import com.code.Entity.Order;
import com.code.Exception.OrderException;

public interface OrderService {
	Order getOrderById(Long orderId) throws OrderException;

	// List<Order> getAllOrders();

	Order createOrder(Order order) throws OrderException;

	void updateOrder(Long orderId, Order order) throws OrderException;
	// void deleteOrder(Long orderId);
}
