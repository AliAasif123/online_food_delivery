package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.Entity.Delivery;

public interface DeliveryRepo extends JpaRepository<Delivery, Long> {

}
