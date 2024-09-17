package com.ecommerce.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.userservice.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);

}
