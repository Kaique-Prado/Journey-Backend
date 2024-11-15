package com.journey_back.repository;

import com.journey_back.model.LinkModel;
import com.journey_back.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkRepository extends JpaRepository<LinkModel, Integer> {

}
