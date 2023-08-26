package com.example.manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VisitserviceRepository extends CrudRepository<VisitService, Long> {
    Set<VisitService> findByTruckId(Long truckId);
    Set<VisitService> findByTrailerId(Long trailerId);
}