package com.code.Service;

import com.code.Entity.Delivery;
import com.code.Exception.DeliveryException;

public interface DeliveryService {
	Delivery getDeliveryById(Long deliveryId) throws DeliveryException;

	// List<Delivery> getAllDeliveries();

	Delivery createDelivery(Delivery delivery) throws DeliveryException;

	// void updateDelivery(Delivery delivery);

	// void deleteDelivery(Long deliveryId);
}
