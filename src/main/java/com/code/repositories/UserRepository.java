package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
