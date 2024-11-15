package com.journey_back.repository;

import com.journey_back.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel, Integer> {
    boolean existsByEmail(String email);
}
