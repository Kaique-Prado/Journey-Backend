package com.journey_back.repository;

import com.journey_back.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TripRepository extends JpaRepository<TripModel, Integer> {
}
